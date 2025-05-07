package cn.autosec.onecore.uss.definition.custom.block.normal.concrete;

import cn.autosec.onecore.uss.registry.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public interface CustomConcretePowderBlock extends Fallable {
    static boolean shouldSolidify(BlockGetter getter, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.canBeHydrated(getter, pos, fluidState, pos) || touchesLiquid(getter, pos, state);
    }

    static boolean shouldSolidify(BlockGetter getter, BlockPos pos, BlockState state) {
        return shouldSolidify(getter, pos, state, getter.getFluidState(pos));
    }

    static boolean touchesLiquid(BlockGetter getter, BlockPos pos, BlockState state) {
        boolean flag = false;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
        if (canSolidify(getter.getBlockState(blockpos$mutableblockpos))) {
            // solidify immediately when the block itself contains water
            return true;
        }

        for (Direction direction : Direction.values()) {
            BlockState blockstate = getter.getBlockState(blockpos$mutableblockpos);
            if (direction != Direction.DOWN || state.canBeHydrated(getter, pos, blockstate.getFluidState(), blockpos$mutableblockpos)) {
                blockpos$mutableblockpos.setWithOffset(pos, direction);
                blockstate = getter.getBlockState(blockpos$mutableblockpos);
                if (state.canBeHydrated(getter, pos, blockstate.getFluidState(), blockpos$mutableblockpos) && !blockstate.isFaceSturdy(getter, pos, direction.getOpposite())) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    private static boolean canSolidify(BlockState state) {
        return state.getFluidState().is(FluidTags.WATER);
    }

    default int getDustColor(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getMapColor(getter, pos).col;
    }

    default void falling(FallingBlockEntity p_53206_) {
    }

    default int getDelayAfterPlace() {
        return 2;
    }

    static boolean isFree(BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.liquid() || state.canBeReplaced();
    }
}
