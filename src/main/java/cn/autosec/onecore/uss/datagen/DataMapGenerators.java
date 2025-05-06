package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Oxidizable;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerators extends DataMapProvider
{
    protected DataMapGenerators(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        Builder<Oxidizable, Block> oxidizable = builder(NeoForgeDataMaps.OXIDIZABLES);
        Builder<Waxable, Block> waxable = builder(NeoForgeDataMaps.WAXABLES);
        ModBlocks.BLOCKS.getEntries().forEach(deferredHolder -> {
            if (deferredHolder.getId().getPath().startsWith(Utils.ONECORE_CUT_COPPER_PREFIX)) {
                oxidizable.add(deferredHolder, new Oxidizable(BuiltInRegistries.BLOCK.get(deferredHolder.getId().withPath(s -> Utils.ONECORE_EXPOSED_COPPER_PREFIX + s))), false);
                oxidizable.add(deferredHolder.getId().withPath(s -> Utils.ONECORE_EXPOSED_COPPER_PREFIX + s), new Oxidizable(BuiltInRegistries.BLOCK.get(deferredHolder.getId().withPath(s -> Utils.ONECORE_WEATHERED_COPPER_PREFIX + s))), false);
                oxidizable.add(deferredHolder.getId().withPath(s -> Utils.ONECORE_WEATHERED_COPPER_PREFIX + s), new Oxidizable(BuiltInRegistries.BLOCK.get(deferredHolder.getId().withPath(s -> Utils.ONECORE_OXIDIZED_COPPER_PREFIX + s))), false);
                waxable.add(deferredHolder, new Waxable(BuiltInRegistries.BLOCK.get(deferredHolder.getId().withPath(s -> Utils.ONECORE_WAXED_COPPER_PREFIX + s))), false);
                waxable.add(deferredHolder.getId().withPath(s -> Utils.ONECORE_EXPOSED_COPPER_PREFIX + s), new Waxable(BuiltInRegistries.BLOCK.get(deferredHolder.getId().withPath(s -> Utils.ONECORE_WAXED_COPPER_PREFIX + Utils.ONECORE_EXPOSED_COPPER_PREFIX + s))), false);
                waxable.add(deferredHolder.getId().withPath(s -> Utils.ONECORE_WEATHERED_COPPER_PREFIX + s), new Waxable(BuiltInRegistries.BLOCK.get(deferredHolder.getId().withPath(s -> Utils.ONECORE_WAXED_COPPER_PREFIX + Utils.ONECORE_WEATHERED_COPPER_PREFIX + s))), false);
                waxable.add(deferredHolder.getId().withPath(s -> Utils.ONECORE_OXIDIZED_COPPER_PREFIX + s), new Waxable(BuiltInRegistries.BLOCK.get(deferredHolder.getId().withPath(s -> Utils.ONECORE_WAXED_COPPER_PREFIX + Utils.ONECORE_OXIDIZED_COPPER_PREFIX + s))), false);
            }
        });
    }
}
