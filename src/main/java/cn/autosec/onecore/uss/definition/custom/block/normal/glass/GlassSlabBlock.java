package cn.autosec.onecore.uss.definition.custom.block.normal.glass;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GlassSlabBlock extends SlabBlock {

    public GlassSlabBlock(Properties properties) {
        super(properties);
    }

    public static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    public static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return false;
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
