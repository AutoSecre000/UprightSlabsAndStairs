package cn.autosec.onecore.uss.definition.custom.block.upright;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GlassUprightSlabBlock extends UprightSlabBlock {

    public GlassUprightSlabBlock(Properties properties) {
        super(properties);
    }

    public boolean skipRendering(BlockState state1, BlockState state2, Direction direction) {
        return state2.is(this) ? true : super.skipRendering(state1, state2, direction);
    }

    public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }
}
