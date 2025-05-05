package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockTagsGenerators extends FabricTagProvider<Block> {
    public BlockTagsGenerators(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        List<BlockLib> blockModels = ModBlocks.getBlockLib();
        if (blockModels != null) {
            for (BlockLib blockLib : blockModels) {
                getOrCreateTagBuilder(ModBlockTags.ONECORE_BLOCKS).add(blockLib.block.get()).setReplace(true);;
                if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.AXE) {
                    getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(blockLib.block.get());
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.HOE) {
                    getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(blockLib.block.get());
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.PICKAXE) {
                    getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(blockLib.block.get());
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.SHOVEL) {
                    getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(blockLib.block.get());
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.SWORD) {
                    getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(blockLib.block.get());
                }
                if (blockLib.mineableLevel == BlockLib.LEVEL.STONE_TOOLS) {
                    getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(blockLib.block.get());
                } else if (blockLib.mineableLevel == BlockLib.LEVEL.IRON_TOOLS) {
                    getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(blockLib.block.get());
                } else if (blockLib.mineableLevel == BlockLib.LEVEL.DIAMOND_TOOLS) {
                    getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(blockLib.block.get());
                }
                if (blockLib.isSlab) {
                    if (blockLib.isWooden) {
                        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(blockLib.block.get());
                    }
                    getOrCreateTagBuilder(BlockTags.SLABS).add(blockLib.block.get());
                }
                if (blockLib.isStairs) {
                    if (blockLib.isWooden) {
                        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(blockLib.block.get());
                    }
                    getOrCreateTagBuilder(BlockTags.STAIRS).add(blockLib.block.get());
                }
                if (blockLib.isCopper) {
                    getOrCreateTagBuilder(ModBlockTags.ONECORE_COPPER).add(blockLib.block.get());
                }
                if (blockLib.isWaxed) {
                    getOrCreateTagBuilder(ModBlockTags.ONECORE_WAXED).add(blockLib.block.get());
                }
            }
        }
    }
}
