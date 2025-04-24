package cn.autosec.onecore.uss.definition.custom.block.upright;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModSlabTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class UprightSlabBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<ModSlabTypes> TYPE = ModBlocks.UPRIGHT_SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape N_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape E_AABB = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape S_AABB = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);

    public UprightSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.defaultBlockState().setValue(TYPE, ModSlabTypes.N).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return blockState.getValue(TYPE) != ModSlabTypes.DOUBLE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    public @NotNull VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter,
                                        @NotNull BlockPos blockPos, @NotNull CollisionContext context) {
        ModSlabTypes type = blockState.getValue(TYPE);
        return switch (type) {
            case DOUBLE -> Shapes.block();
            case E -> E_AABB;
            case S -> S_AABB;
            case W -> W_AABB;
            default -> N_AABB;
        };
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        if (blockstate.is(this)) {
            return blockstate.setValue(TYPE, ModSlabTypes.DOUBLE).setValue(WATERLOGGED, Boolean.FALSE);
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(blockpos);
            ModSlabTypes newSlabType = getModSlabTypes(context);
            return this.defaultBlockState().setValue(TYPE, newSlabType).setValue(WATERLOGGED,
                    fluidstate.getType() == Fluids.WATER);
        }
    }

    private @NotNull ModSlabTypes getModSlabTypes(BlockPlaceContext context) {
        ModSlabTypes newSlabType;
        boolean xDelta = context.getClickLocation().x - context.getClickedPos().getX() > 0.5D;
        boolean zDelta = context.getClickLocation().z - context.getClickedPos().getZ() > 0.5D;
        if (context.getHorizontalDirection() == Direction.NORTH) {
            newSlabType = !zDelta ? ModSlabTypes.N : ModSlabTypes.S;
        } else if (context.getHorizontalDirection() == Direction.EAST) {
            newSlabType = xDelta ? ModSlabTypes.E : ModSlabTypes.W;
        } else if (context.getHorizontalDirection() == Direction.SOUTH) {
            newSlabType = zDelta ? ModSlabTypes.S : ModSlabTypes.N;
        } else {
            newSlabType = !xDelta ? ModSlabTypes.W : ModSlabTypes.E;
        }
        return newSlabType;
    }

    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext context) {
        ItemStack itemstack = context.getItemInHand();
        ModSlabTypes curType = blockState.getValue(TYPE);
        if (curType != ModSlabTypes.DOUBLE && itemstack.is(this.asItem())) {
            if (context.replacingClickedOnBlock()) {
                boolean xDelta = context.getClickLocation().x - context.getClickedPos().getX() > 0.5D;
                boolean zDelta = context.getClickLocation().z - context.getClickedPos().getZ() > 0.5D;
                OneCore.LOGGER.info("[UprightSlabBlock:canBeReplaced]\n" +
                        "type: {}, getClickLocation():{}, getClickedPos():{}, direction:{}", curType,
                        context.getClickLocation(), context.getClickedPos(), context.getClickedFace());
                Direction direction = context.getClickedFace();
                if (curType == ModSlabTypes.N) {
                    return direction == Direction.SOUTH || zDelta && direction.getAxis().isVertical();
                } else if (curType == ModSlabTypes.E) {
                    return direction == Direction.WEST || !xDelta && direction.getAxis().isVertical();
                } else if (curType == ModSlabTypes.S) {
                    return direction == Direction.NORTH || !zDelta && direction.getAxis().isVertical();
                } else {
                    return direction == Direction.EAST || xDelta && direction.getAxis().isVertical();
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public @NotNull FluidState getFluidState(@NotNull BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ?
                Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    public boolean placeLiquid(@NotNull LevelAccessor levelAccessor, @NotNull BlockPos pos,
                               BlockState blockState, @NotNull FluidState fluidState) {
        return blockState.getValue(TYPE) != ModSlabTypes.DOUBLE
                && SimpleWaterloggedBlock.super.placeLiquid(levelAccessor, pos, blockState, fluidState);
    }

    public boolean canPlaceLiquid(@Nullable Player player, @NotNull BlockGetter blockGetter,
                                  @NotNull BlockPos pos, BlockState state, @NotNull Fluid fluid) {
        return state.getValue(TYPE) != ModSlabTypes.DOUBLE
                && SimpleWaterloggedBlock.super.canPlaceLiquid(player, blockGetter, pos, state, fluid);
    }

    public @NotNull BlockState updateShape(@NotNull BlockState state1, @NotNull Direction direction,
                                           @NotNull BlockState state2, @NotNull LevelAccessor levelAccessor,
                                           @NotNull BlockPos pos1, @NotNull BlockPos pos2) {
        if (state1.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return super.updateShape(state1, direction, state2, levelAccessor, pos1, pos2);
    }

    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter blockGetter,
                                  @NotNull BlockPos pos, @NotNull PathComputationType type) {
        return switch (type) {
            case LAND -> false;
            case WATER -> blockGetter.getFluidState(pos).is(FluidTags.WATER);
            case AIR -> false;
            default -> false;
        };
    }
}
