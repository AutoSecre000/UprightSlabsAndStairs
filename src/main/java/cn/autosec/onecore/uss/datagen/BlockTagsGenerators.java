package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockTagsGenerators extends BlockTagsProvider {
    public BlockTagsGenerators(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OneCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        List<BlockLib> blockModels = ModBlocks.getBlockLib();
        if (blockModels != null) {
            for (BlockLib blockLib : blockModels) {
                this.tag(ModBlockTags.ONECORE_BLOCKS).add(blockLib.block);
                if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.AXE) {
                    this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockLib.block);
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.HOE) {
                    this.tag(BlockTags.MINEABLE_WITH_HOE).add(blockLib.block);
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.PICKAXE) {
                    this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockLib.block);
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.SHOVEL) {
                    this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(blockLib.block);
                } else if (blockLib.mineableType == BlockLib.MINEABLE_TYPE.SWORD) {
                    this.tag(BlockTags.SWORD_EFFICIENT).add(blockLib.block);
                }
                if (blockLib.mineableLevel == BlockLib.LEVEL.STONE_TOOLS) {
                    this.tag(BlockTags.NEEDS_STONE_TOOL).add(blockLib.block);
                } else if (blockLib.mineableLevel == BlockLib.LEVEL.IRON_TOOLS) {
                    this.tag(BlockTags.NEEDS_IRON_TOOL).add(blockLib.block);
                } else if (blockLib.mineableLevel == BlockLib.LEVEL.DIAMOND_TOOLS) {
                    this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(blockLib.block);
                }
                if (blockLib.isSlab) {
                    if (blockLib.isWooden) {
                        this.tag(BlockTags.WOODEN_SLABS).add(blockLib.block);
                    }
                    this.tag(BlockTags.SLABS).add(blockLib.block);
                }
                if (blockLib.isStairs) {
                    if (blockLib.isWooden) {
                        this.tag(BlockTags.WOODEN_STAIRS).add(blockLib.block);
                    }
                    this.tag(BlockTags.STAIRS).add(blockLib.block);
                }
            }
        }
    }
}
