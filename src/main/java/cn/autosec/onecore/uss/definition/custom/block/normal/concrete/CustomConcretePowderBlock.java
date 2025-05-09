package cn.autosec.onecore.uss.definition.custom.block.normal.concrete;

import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.LandingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public interface CustomConcretePowderBlock extends LandingBlock {
    default void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
    }

    static boolean shouldHarden(BlockView world, BlockPos pos, BlockState state) {
        return hardensIn(state) || hardensOnAnySide(world, pos);
    }

    static boolean hardensOnAnySide(BlockView world, BlockPos pos) {
        boolean bl = false;
        BlockPos.Mutable mutable = pos.mutableCopy();

        for (Direction direction : Direction.values()) {
            BlockState blockState = world.getBlockState(mutable);
            if (direction != Direction.DOWN || hardensIn(blockState)) {
                mutable.set(pos, direction);
                blockState = world.getBlockState(mutable);
                if (hardensIn(blockState) && !blockState.isSideSolidFullSquare(world, pos, direction.getOpposite())) {
                    bl = true;
                    break;
                }
            }
        }

        return bl;
    }

    private static boolean hardensIn(BlockState state) {
        return state.getFluidState().isIn(FluidTags.WATER);
    }

    default int getColor(BlockState state, BlockView world, BlockPos pos) {
        return state.getMapColor(world, pos).color;
    }

    default void configureFallingBlockEntity(FallingBlockEntity entity) {
    }

    default int getFallDelay() {
        return 2;
    }

    static boolean canFallThrough(BlockState state) {
        return ConcretePowderBlock.canFallThrough(state);
    }
}
