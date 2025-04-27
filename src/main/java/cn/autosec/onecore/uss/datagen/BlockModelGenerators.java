package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class BlockModelGenerators extends BlockStateProvider {
    public BlockModelGenerators(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OneCore.MODID, existingFileHelper);
    }

    public ResourceLocation blockTexture(Block block, String suffix) {
        ResourceLocation name = ForgeRegistries.BLOCKS.getKey(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath() + suffix);
    }

    @Override
    protected void registerStatesAndModels() {
        List<BlockLib> blockModels = ModBlocks.getBlockLib();
        slabBlock((SlabBlock) ModBlocks.GLASS_SLAB.get(), blockTexture(Blocks.GLASS), blockTexture(Blocks.GLASS));
        stairsBlock((StairBlock) ModBlocks.GLASS_STAIRS.get(), blockTexture(Blocks.GLASS), blockTexture(Blocks.GLASS),
                blockTexture(Blocks.GLASS));
        stairsBlock((StairBlock) ModBlocks.SMOOTH_STONE_STAIRS.get(), blockTexture(Blocks.SMOOTH_STONE),
                blockTexture(Blocks.SMOOTH_STONE), blockTexture(Blocks.SMOOTH_STONE));
        stairsBlock((StairBlock) ModBlocks.SMOOTH_STONE_TRANSVERSE_STAIRS.get(),
                blockTexture(Blocks.SMOOTH_STONE_SLAB, "_side"), blockTexture(Blocks.SMOOTH_STONE),
                blockTexture(Blocks.SMOOTH_STONE));
        if (blockModels != null) {
            for (BlockLib blockLib : blockModels) {
                OneCore.LOGGER.info("[BlockModelGenerators:registerStatesAndModels] blockLib.name = {}", blockLib.name);
                if (blockLib.isSimpleCube) {
                    if (blockLib.isSlab) {
                        simpleBlockItem(blockLib.modRegistry.get(), models().slab(blockLib.name,
                                blockLib.hasSideTexture ? blockLib.sideTexture : blockLib.texture,
                                blockLib.texture, blockLib.texture));
                    } else if (blockLib.isStairs) {
                        simpleBlockItem(blockLib.modRegistry.get(), models().stairs(blockLib.name,
                                blockLib.hasSideTexture ? blockLib.sideTexture : blockLib.texture,
                                blockLib.texture, blockLib.texture));
                    } else {
                        simpleBlockWithItem(blockLib.modRegistry.get(), cubeAll(blockLib.modRegistry.get()));
                    }
                }
            }
        }
    }
}
