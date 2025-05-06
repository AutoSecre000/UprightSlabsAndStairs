package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class LootTableGenerators extends VanillaBlockLoot {

    public LootTableGenerators(HolderLookup.Provider p_345352_) {
        super(p_345352_);
    }

    @Override
    protected void generate() {
        List<BlockLib> blockLootTables = ModBlocks.getBlockLib();
        if (blockLootTables == null) {
            return;
        }
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        for (BlockLib blockLib : blockLootTables) {
            if (blockLib.isSlab) {
                if (blockLib.isGlass) {
                    add(blockLib.modRegistry.get(), LootTable.lootTable()
                            .withPool(LootPool.lootPool().when(this.hasSilkTouch())
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add((LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(blockLib.modRegistry.get(), LootItem.lootTableItem(blockLib.modRegistry.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(blockLib.modRegistry.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
                } else {
                    add(blockLib.modRegistry.get(), createSlabItemTable(blockLib.modRegistry.get()));
                }
            } else if (blockLib.isDropSelf) {
                if (blockLib.isGlass) {
                    dropWhenSilkTouch(blockLib.modRegistry.get());
                } else {
                    dropSelf(blockLib.modRegistry.get());
                }
            } else {
                /* drop items */
                add(blockLib.modRegistry.get(), (block) -> {
                    if (blockLib.applyToFortune) {
                        return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(blockLib.dropItem.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(blockLib.minDrop, blockLib.maxDrop)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
                    }
                    return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(blockLib.dropItem.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(blockLib.minDrop, blockLib.maxDrop)))));
                });
            }
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(OneCore.MODID))
                .map(DeferredHolder::get)
                .collect(Collectors.toList());
    }
}
