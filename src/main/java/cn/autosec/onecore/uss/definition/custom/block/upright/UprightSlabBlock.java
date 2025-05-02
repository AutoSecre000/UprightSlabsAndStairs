package cn.autosec.onecore.uss.definition.custom.block.upright;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModSlabTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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

public abstract class UprightSlabBlock extends Block implements Waterloggable {
    public static final EnumProperty<ModSlabTypes> TYPE = ModBlocks.UPRIGHT_SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape N_AABB = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape E_AABB = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape S_AABB = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape W_AABB = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);

    public UprightSlabBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(TYPE, ModSlabTypes.N).with(WATERLOGGED, false));
    }

    @Override
    protected boolean hasSidedTransparency(BlockState state) {
        return state.get(TYPE) != ModSlabTypes.DOUBLE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        ModSlabTypes slabType = state.get(TYPE);
        return switch (slabType) {
            case DOUBLE -> VoxelShapes.fullCube();
            case E -> E_AABB;
            case S -> S_AABB;
            case W -> W_AABB;
            default -> N_AABB;
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return blockState.with(TYPE, ModSlabTypes.DOUBLE).with(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            ModSlabTypes newSlabType = getModSlabTypes(ctx);
            return this.getDefaultState().with(TYPE, newSlabType).with(WATERLOGGED,
                    fluidState.getFluid() == Fluids.WATER);
        }
    }

    private @NotNull ModSlabTypes getModSlabTypes(ItemPlacementContext context) {
        ModSlabTypes newSlabType;
        boolean xDelta = context.getHitPos().x - context.getBlockPos().getX() > 0.5D;
        boolean zDelta = context.getHitPos().z - context.getBlockPos().getZ() > 0.5D;
        if (context.getHorizontalPlayerFacing() == Direction.NORTH) {
            newSlabType = !zDelta ? ModSlabTypes.N : ModSlabTypes.S;
        } else if (context.getHorizontalPlayerFacing() == Direction.EAST) {
            newSlabType = xDelta ? ModSlabTypes.E : ModSlabTypes.W;
        } else if (context.getHorizontalPlayerFacing() == Direction.SOUTH) {
            newSlabType = zDelta ? ModSlabTypes.S : ModSlabTypes.N;
        } else {
            newSlabType = !xDelta ? ModSlabTypes.W : ModSlabTypes.E;
        }
        return newSlabType;
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        ModSlabTypes curType = state.get(TYPE);
        if (curType == ModSlabTypes.DOUBLE || !itemStack.isOf(this.asItem())) {
            return false;
        } else if (context.canReplaceExisting()) {
            boolean xDelta = context.getHitPos().x - context.getBlockPos().getX() > 0.5D;
            boolean zDelta = context.getHitPos().z - context.getBlockPos().getZ() > 0.5D;
            OneCore.LOGGER.info("[UprightSlabBlock:canReplace]\n" +
                            "type: {}, getHitPos():{}, getBlockPos():{}, direction:{}", curType,
                    context.getHitPos(), context.getBlockPos(), context.getSide());
            Direction direction = context.getSide();
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
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != ModSlabTypes.DOUBLE && Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != ModSlabTypes.DOUBLE && Waterloggable.super.canFillWithFluid(player, world, pos, state, fluid);
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
