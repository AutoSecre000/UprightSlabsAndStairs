package cn.autosec.onecore.uss.definition.custom.block.normal.concrete;

import cn.autosec.onecore.uss.OneCore;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;

public class ConcretePowderSlabBlock extends SlabBlock implements CustomConcretePowderBlock {
    public static final MapCodec<ConcretePowderSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_341829_ -> p_341829_.group(Registries.BLOCK.getCodec().fieldOf("concrete").forGetter(p_313163_ -> p_313163_.concrete), createSettingsCodec())
                    .apply(p_341829_, ConcretePowderSlabBlock::new)
    );
    private final Block concrete;

    public @NotNull MapCodec<ConcretePowderSlabBlock> codec() {
        return CODEC;
    }

    public ConcretePowderSlabBlock(Block concrete, Settings properties) {
        super(properties);
        this.concrete = concrete;
    }

    private BlockState waterlogged(World world, BlockPos pos, BlockState state) {
        FluidState fluidstate = world.getFluidState(pos);
        return state.with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
    }

    private boolean isFree(BlockState selfState, BlockState state) {
        boolean isFree;
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]\nselfState:{}\nstate:{}", selfState, state);
        if (selfState.get(TYPE) == SlabType.TOP) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]is top slab");
            isFree = true;
        } else if (state.isOf(this) && state.get(TYPE) == SlabType.BOTTOM) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]over bottom slab");
            isFree = true;
        } else {
            isFree = CustomConcretePowderBlock.canFallThrough(state);
        }
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]isFree:{}", isFree);
        return isFree;
    }

    @Override
    public void onLanding(@NotNull World world, @NotNull BlockPos pos, @NotNull BlockState state,
                          @NotNull BlockState otherState, @NotNull FallingBlockEntity entity) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]\nstate:{} pos:{} \notherState:{}", state, pos, otherState);
        if (CustomConcretePowderBlock.shouldHarden(world, pos, state)) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]shouldSolidify");
            if (otherState.isOf(this.concrete) && otherState.get(TYPE) == SlabType.BOTTOM) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]over bottom concrete slab");
                world.setBlockState(pos, waterlogged(world, pos, this.concrete.getStateWithProperties(state).with(TYPE, SlabType.DOUBLE)), 3);
            } else if (!otherState.isOf(this.concrete)) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to bottom concrete slab");
                world.setBlockState(pos, waterlogged(world, pos, this.concrete.getStateWithProperties(state).with(TYPE, SlabType.BOTTOM)), 3);
            } else {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to concrete slab");
                world.setBlockState(pos, waterlogged(world, pos, this.concrete.getStateWithProperties(state)), 3);
            }
        } else {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]should not Solidify");
            if (otherState.isOf(state.getBlock()) && otherState.get(TYPE) == SlabType.BOTTOM) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]over bottom slab");
                world.setBlockState(pos, waterlogged(world, pos, state.with(TYPE, SlabType.DOUBLE)), 3);
            } else if (state.get(TYPE) == SlabType.DOUBLE) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]is double slab");
                world.setBlockState(pos, waterlogged(world, pos, state.with(TYPE, SlabType.DOUBLE)), 3);
            } else if (isFree(state, otherState)) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to bottom slab");
                world.setBlockState(pos, waterlogged(world, pos, state.with(TYPE, SlabType.BOTTOM)), 3);
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView = ctx.getWorld();
        BlockPos blockpos = ctx.getBlockPos();
        BlockState blockstate = blockView.getBlockState(blockpos);
        BlockState nextState = super.getPlacementState(ctx);
        if (nextState == null) {
            nextState = blockstate;
        }
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]\nblockstate:{} pos:{}\nnextState:{}", blockstate, blockpos, nextState);
        if (CustomConcretePowderBlock.shouldHarden(blockView, blockpos, blockstate)) {
            nextState = waterlogged(ctx.getWorld(), blockpos, this.concrete.getStateWithProperties(nextState));
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]shouldSolidify->>\nnextState:{}", nextState);
        }
        return nextState;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        BlockState nextState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:updateShape]\nstate:{} pos:{}\nnextState:{} neighborPos:{}",
                state, pos, nextState, neighborPos);
        if (CustomConcretePowderBlock.hardensOnAnySide(world, pos)) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:updateShape]\ntouchesLiquid->>\nnextState:{} pos2:{}",
                    nextState, neighborPos);
            return this.concrete.getStateWithProperties(nextState);
        }
        world.scheduleBlockTick(pos, this, this.getFallDelay());
        return nextState;
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onPlace]\nstate:{} pos:{}\noldState:{}", state, pos, oldState);
        world.scheduleBlockTick(pos, this, this.getFallDelay());
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        BlockState blockstate = fallingBlockEntity.getBlockState();
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onBrokenAfterFall]\nblockstate:{} pos:{}", blockstate, pos);
        if (blockstate.get(TYPE) == SlabType.DOUBLE) {
            FallingBlockEntity.spawnFromBlock(world, pos, blockstate);
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:tick]\nstate:{} pos:{}", state, pos);
        if (isFree(state, world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.spawnFromBlock(world, pos, state);
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:tick]falling");
            this.configureFallingBlockEntity(fallingblockentity);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(16) == 0) {
            BlockPos blockPos = pos.down();
            if (CustomConcretePowderBlock.canFallThrough(world.getBlockState(blockPos))) {
                ParticleUtil.spawnParticle(world, pos, random, new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, state));
            }
        }
    }
}
