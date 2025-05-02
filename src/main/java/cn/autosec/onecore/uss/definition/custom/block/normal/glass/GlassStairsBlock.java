package cn.autosec.onecore.uss.definition.custom.block.normal.glass;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GlassStairsBlock extends StairsBlock {

    public GlassStairsBlock(BlockState blockState, Settings properties) {
        super(blockState, properties);
    }

    @Override
    protected boolean isSideInvisible(BlockState state, BlockState stateFrom, net.minecraft.util.math.Direction direction) {
        return state == stateFrom ? true : super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, net.minecraft.util.math.BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, net.minecraft.util.math.BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, net.minecraft.util.math.BlockPos pos) {
        return true;
    }
}
