package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LootTableGenerators extends FabricBlockLootTableProvider {
    public LootTableGenerators(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        List<BlockLib> blockLootTables = ModBlocks.getBlockLib();
        if (blockLootTables == null) {
            return;
        }
        for (BlockLib blockLib : blockLootTables) {
            if (blockLib.isSlab) {
                if (blockLib.isGlass) {
                    this.addDrop(blockLib.modRegistry.get(), LootTable.builder()
                            .pool(LootPool.builder().conditionally(this.createSilkTouchCondition())
                                    .rolls(ConstantLootNumberProvider.create(1.0F))
                                    .with((LootPoolEntry.Builder<?>)this.applyExplosionDecay(blockLib.modRegistry.get(),
                                            ItemEntry.builder(blockLib.modRegistry.get())
                                                    .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                            .conditionally(BlockStatePropertyLootCondition.builder(blockLib.modRegistry.get())
                                                                    .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))))))));
                } else {
                    addDrop(blockLib.modRegistry.get(), slabDrops(blockLib.modRegistry.get()));
                }
            } else if (blockLib.isDropSelf) {
                if (blockLib.isGlass) {
                    addDropWithSilkTouch(blockLib.modRegistry.get());
                } else {
                    addDrop(blockLib.modRegistry.get());
                }
            } else {
                /* drop items */
                if (blockLib.applyToFortune) {
                    RegistryEntryLookup<Enchantment> lookup = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
                    RegistryEntry<Enchantment> enchantment = lookup.getOrThrow(Enchantments.FORTUNE);
                    addDrop(blockLib.modRegistry.get(), this.applyExplosionDecay(blockLib.modRegistry.get(),
                            LootTable.builder().pool(addSurvivesExplosionCondition(blockLib.dropItem.get(), LootPool.builder()
                                    .conditionally(createWithoutShearsOrSilkTouchCondition())
                                    .rolls(new UniformLootNumberProvider(new ConstantLootNumberProvider(blockLib.minDrop),
                                            new ConstantLootNumberProvider(blockLib.maxDrop)))
                                    .with(ItemEntry.builder(blockLib.dropItem.get()))
                                    .apply(ApplyBonusLootFunction.uniformBonusCount(enchantment))))));
                } else {
                    addDrop(blockLib.modRegistry.get(), this.applyExplosionDecay(blockLib.modRegistry.get(),
                            LootTable.builder().pool(addSurvivesExplosionCondition(blockLib.dropItem.get(), LootPool.builder()
                                    .conditionally(createWithoutShearsOrSilkTouchCondition())
                                    .rolls(new UniformLootNumberProvider(new ConstantLootNumberProvider(blockLib.minDrop),
                                            new ConstantLootNumberProvider(blockLib.maxDrop)))
                                    .with(ItemEntry.builder(blockLib.dropItem.get()))))));
                }
            }
        }
    }
}
