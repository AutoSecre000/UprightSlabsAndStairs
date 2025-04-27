package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LootTableGenerators extends VanillaBlockLoot {

    @Override
    protected void generate() {
        List<BlockLib> blockLootTables = ModBlocks.getBlockLib();
        if (blockLootTables == null) {
            return;
        }
        for (BlockLib blockLib : blockLootTables) {
            if (blockLib.isSlab) {
                add(blockLib.modRegistry.get(), createSlabItemTable(blockLib.modRegistry.get()));
            } else if (blockLib.isDropSelf) {
                dropSelf(blockLib.modRegistry.get());
            } else {
                /* drop items */
                add(blockLib.modRegistry.get(), (block) -> {
                    if (blockLib.applyToFortune) {
                        return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(blockLib.dropItem.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(blockLib.minDrop, blockLib.maxDrop)))
                                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
                    }
                    return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(blockLib.dropItem.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(blockLib.minDrop, blockLib.maxDrop)))));
                });
            }
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(OneCore.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
