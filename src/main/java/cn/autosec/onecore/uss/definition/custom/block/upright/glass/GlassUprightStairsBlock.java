package cn.autosec.onecore.uss.definition.custom.block.upright.glass;

import cn.autosec.onecore.uss.definition.custom.block.upright.UprightStairsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GlassUprightStairsBlock extends UprightStairsBlock {

    public GlassUprightStairsBlock(Settings properties) {
        super(properties);
    }

    @Override
    protected boolean isSideInvisible(BlockState state, BlockState stateFrom, net.minecraft.util.math.Direction direction) {
        return super.isSideInvisible(state, stateFrom, direction);
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
