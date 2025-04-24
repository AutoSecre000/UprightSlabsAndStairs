package cn.autosec.onecore.uss.definition.custom.block.upright;

import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModStairTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
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

public abstract class UprightStairsBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<ModStairTypes> TYPE = ModBlocks.UPRIGHT_STAIR_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape NW_INNER = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    protected static final VoxelShape SW_INNER = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape NE_INNER = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape SE_INNER = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape NW_OUTER = Shapes.or(NW_INNER, SW_INNER, NE_INNER);
    protected static final VoxelShape SW_OUTER = Shapes.or(SW_INNER, SE_INNER, NW_INNER);
    protected static final VoxelShape NE_OUTER = Shapes.or(NE_INNER, NW_INNER, SE_INNER);
    protected static final VoxelShape SE_OUTER = Shapes.or(SE_INNER, SW_INNER, NE_INNER);

    public UprightStairsBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.defaultBlockState().setValue(TYPE, ModStairTypes.NW).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    public @NotNull VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter,
                                        @NotNull BlockPos blockPos, @NotNull CollisionContext context) {
        ModStairTypes type = blockState.getValue(TYPE);
        return switch (type) {
            case NW -> NW_OUTER;
            case SW -> SW_OUTER;
            case NE -> NE_OUTER;
            default -> SE_OUTER;
        };
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        ModStairTypes newStairType = getModStairTypes(context);
        return this.defaultBlockState().setValue(TYPE, newStairType).setValue(WATERLOGGED,
                fluidstate.getType() == Fluids.WATER);
    }

    private @NotNull ModStairTypes getModStairTypes(BlockPlaceContext context) {
        ModStairTypes newStairType;
        boolean xDelta = context.getClickLocation().x - context.getClickedPos().getX() > 0.5D;
        boolean zDelta = context.getClickLocation().z - context.getClickedPos().getZ() > 0.5D;
        if (context.getHorizontalDirection() == Direction.NORTH) {
            newStairType = xDelta ? ModStairTypes.NE : ModStairTypes.NW;
        } else if (context.getHorizontalDirection() == Direction.EAST) {
            newStairType = zDelta ? ModStairTypes.SE : ModStairTypes.NE;
        } else if (context.getHorizontalDirection() == Direction.SOUTH) {
            newStairType = !xDelta ? ModStairTypes.SW : ModStairTypes.SE;
        } else {
            newStairType = !zDelta ? ModStairTypes.NW : ModStairTypes.SW;
        }
        return newStairType;
    }

    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext context) {
        return false;
    }

    public @NotNull FluidState getFluidState(@NotNull BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ?
                Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    public boolean placeLiquid(@NotNull LevelAccessor levelAccessor, @NotNull BlockPos pos,
                               BlockState blockState, @NotNull FluidState fluidState) {
        return SimpleWaterloggedBlock.super.placeLiquid(levelAccessor, pos, blockState, fluidState);
    }

    public boolean canPlaceLiquid(@Nullable Player player, @NotNull BlockGetter blockGetter,
                                  @NotNull BlockPos pos, BlockState state, @NotNull Fluid fluid) {
        return SimpleWaterloggedBlock.super.canPlaceLiquid(player, blockGetter, pos, state, fluid);
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
