package cn.autosec.onecore.uss.definition.custom.block.upright;

import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModStairTypes;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class UprightStairsBlock extends Block implements Waterloggable {
    public static final EnumProperty<ModStairTypes> TYPE = ModBlocks.UPRIGHT_STAIR_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape NW_INNER = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    protected static final VoxelShape SW_INNER = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape NE_INNER = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape SE_INNER = Block.createCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape NW_OUTER = VoxelShapes.union(NW_INNER, SW_INNER, NE_INNER);
    protected static final VoxelShape SW_OUTER = VoxelShapes.union(SW_INNER, SE_INNER, NW_INNER);
    protected static final VoxelShape NE_OUTER = VoxelShapes.union(NE_INNER, NW_INNER, SE_INNER);
    protected static final VoxelShape SE_OUTER = VoxelShapes.union(SE_INNER, SW_INNER, NE_INNER);

    public UprightStairsBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(TYPE, ModStairTypes.NW).with(WATERLOGGED, false));
    }

    @Override
    protected boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        ModStairTypes slabType = state.get(TYPE);
        return switch (slabType) {
            case NW -> NW_OUTER;
            case SW -> SW_OUTER;
            case NE -> NE_OUTER;
            default -> SE_OUTER;
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        ModStairTypes newStairType = getModStairTypes(ctx);
        return this.getDefaultState().with(TYPE, newStairType).with(WATERLOGGED,
                fluidState.getFluid() == Fluids.WATER);
    }

    private @NotNull ModStairTypes getModStairTypes(ItemPlacementContext context) {
        ModStairTypes newStairType;
        boolean xDelta = context.getHitPos().x - context.getBlockPos().getX() > 0.5D;
        boolean zDelta = context.getHitPos().z - context.getBlockPos().getZ() > 0.5D;
        if (context.getHorizontalPlayerFacing() == Direction.NORTH) {
            newStairType = xDelta ? ModStairTypes.NE : ModStairTypes.NW;
        } else if (context.getHorizontalPlayerFacing() == Direction.EAST) {
            newStairType = zDelta ? ModStairTypes.SE : ModStairTypes.NE;
        } else if (context.getHorizontalPlayerFacing() == Direction.SOUTH) {
            newStairType = !xDelta ? ModStairTypes.SW : ModStairTypes.SE;
        } else {
            newStairType = !zDelta ? ModStairTypes.NW : ModStairTypes.SW;
        }
        return newStairType;
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return false;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return Waterloggable.super.canFillWithFluid(player, world, pos, state, fluid);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return switch (type) {
            case LAND -> false;
            case WATER -> state.getFluidState().isIn(FluidTags.WATER);
            case AIR -> false;
            default -> false;
        };
    }
}
