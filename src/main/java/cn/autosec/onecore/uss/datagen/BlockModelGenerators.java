package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;

public class BlockModelGenerators extends BlockStateProvider {
    public BlockModelGenerators(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OneCore.MODID, existingFileHelper);
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private void slabBlock(Block block, ResourceLocation doubleSlab, ResourceLocation texture, String renderType) {
        slabBlock(block, doubleSlab, texture, texture, texture, renderType);
    }

    private void slabBlock(Block block, ResourceLocation doubleSlab, ResourceLocation side,
                          ResourceLocation bottom, ResourceLocation top, String renderType) {
        ModelFile bottomFile = models().slab(name(block), side, bottom, top).renderType(renderType);
        ModelFile topFIle = models().slabTop(name(block) + "_top", side, bottom, top).renderType(renderType);
        ModelFile doubleSlabFile = models().getExistingFile(doubleSlab);
        getVariantBuilder(block)
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottomFile))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(topFIle))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleSlabFile));
    }

    private void stairsBlock(Block block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top,
                             String renderType) {
        stairsBlockInternal(block, key(block).toString(), side, bottom, top, renderType);
    }

    private void stairsBlockInternal(Block block, String baseName, ResourceLocation side, ResourceLocation bottom,
                                     ResourceLocation top, String renderType) {
        ModelFile stairs = models().stairs(baseName, side, bottom, top).renderType(renderType);
        ModelFile stairsInner = models().stairsInner(baseName + "_inner", side, bottom, top).renderType(renderType);
        ModelFile stairsOuter = models().stairsOuter(baseName + "_outer", side, bottom, top).renderType(renderType);
        stairsBlock(block, stairs, stairsInner, stairsOuter);
    }

    private void stairsBlock(Block block, ModelFile stairs, ModelFile stairsInner, ModelFile stairsOuter) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(StairBlock.FACING);
                    Half half = state.getValue(StairBlock.HALF);
                    StairsShape shape = state.getValue(StairBlock.SHAPE);
                    int yRot = (int) facing.getClockWise().toYRot();
                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
                        yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
                    }
                    if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
                        yRot += 90; // Top stairs are rotated 90 degrees clockwise
                    }
                    yRot %= 360;
                    boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
                    return ConfiguredModel.builder()
                            .modelFile(shape == StairsShape.STRAIGHT ? stairs : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter)
                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                            .rotationY(yRot)
                            .uvLock(uvlock)
                            .build();
                }, StairBlock.WATERLOGGED);
    }

    @Override
    protected void registerStatesAndModels() {
        List<BlockLib> blockModels = ModBlocks.getBlockLib();
        if (blockModels != null) {
            for (BlockLib blockLib : blockModels) {
                OneCore.LOGGER.info("[BlockModelGenerators:registerStatesAndModels] blockLib.name = {}", blockLib.name);
                if (blockLib.isSimpleCube) {
                    if (blockLib.isSlab) {
                        slabBlock(blockLib.modRegistry.get(), blockLib.texture, blockLib.texture,
                                blockLib.isGlass ? "cutout" : "solid");
                        simpleBlockItem(blockLib.modRegistry.get(), models().slab(blockLib.name,
                                blockLib.hasSideTexture ? blockLib.sideTexture : blockLib.texture,
                                blockLib.texture, blockLib.texture));
                    } else if (blockLib.isStairs) {
                        stairsBlock(blockLib.modRegistry.get(),
                                blockLib.hasSideTexture ? blockLib.sideTexture : blockLib.texture, blockLib.texture,
                                blockLib.texture, blockLib.isGlass ? "cutout" : "solid");
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
