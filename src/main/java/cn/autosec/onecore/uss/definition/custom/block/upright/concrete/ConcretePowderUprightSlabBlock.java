package cn.autosec.onecore.uss.definition.custom.block.upright.concrete;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.custom.block.normal.concrete.CustomConcretePowderBlock;
import cn.autosec.onecore.uss.definition.custom.block.upright.UprightSlabBlock;
import cn.autosec.onecore.uss.registry.ModSlabTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public class ConcretePowderUprightSlabBlock extends UprightSlabBlock implements CustomConcretePowderBlock {
    public static final MapCodec<ConcretePowderUprightSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_341829_ -> p_341829_.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("concrete").forGetter(p_313163_ -> p_313163_.concrete), propertiesCodec())
                    .apply(p_341829_, ConcretePowderUprightSlabBlock::new)
    );
    private final Block concrete;

    @Override
    public @NotNull MapCodec<ConcretePowderUprightSlabBlock> codec() {
        return CODEC;
    }

    public ConcretePowderUprightSlabBlock(Block concrete, Properties properties) {
        super(properties);
        this.concrete = concrete;
    }

    private BlockState waterlogged(Level level, BlockPos pos, BlockState state) {
        FluidState fluidstate = level.getFluidState(pos);
        return state.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    private boolean isFree(BlockState selfState, BlockState state) {
        boolean isFree = CustomConcretePowderBlock.isFree(state);
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]\nselfState:{}\nstate:{}", selfState, state);
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]isFree:{}", isFree);
        return isFree;
    }

    public boolean isOppositeType(BlockState state, BlockState otherState) {
        return otherState.getValue(TYPE) == ModSlabTypes.E && state.getValue(TYPE) == ModSlabTypes.W ||
                otherState.getValue(TYPE) == ModSlabTypes.W && state.getValue(TYPE) == ModSlabTypes.E ||
                otherState.getValue(TYPE) == ModSlabTypes.N && state.getValue(TYPE) == ModSlabTypes.S ||
                otherState.getValue(TYPE) == ModSlabTypes.S && state.getValue(TYPE) == ModSlabTypes.N;
    }

    @Override
    public void onLand(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state,
                       @NotNull BlockState otherState, @NotNull FallingBlockEntity entity) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]\nstate:{} pos:{} \notherState:{}", state, pos, otherState);
        if (CustomConcretePowderBlock.shouldSolidify(level, pos, state, otherState.getFluidState())) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]shouldSolidify");
            if (otherState.is(this.concrete) && isOppositeType(state, otherState)) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]over bottom concrete slab");
                level.setBlock(pos, waterlogged(level, pos, this.concrete.withPropertiesOf(state).setValue(TYPE, ModSlabTypes.DOUBLE)), 3);
            } else {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to concrete slab");
                level.setBlock(pos, waterlogged(level, pos, this.concrete.withPropertiesOf(state)), 3);
            }
        } else {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]should not Solidify");
            if (otherState.is(state.getBlock()) && isOppositeType(state, otherState)) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]over bottom slab");
                level.setBlock(pos, waterlogged(level, pos, state.setValue(TYPE, ModSlabTypes.DOUBLE)), 3);
            } else if (state.getValue(TYPE) == ModSlabTypes.DOUBLE) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]is double slab");
                level.setBlock(pos, waterlogged(level, pos, state.setValue(TYPE, ModSlabTypes.DOUBLE)), 3);
            } else if (isFree(state, otherState)) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to bottom slab");
                level.setBlock(pos, waterlogged(level, pos, state), 3);
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockgetter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = blockgetter.getBlockState(blockpos);
        BlockState nextState = super.getStateForPlacement(context);
        if (nextState == null) {
            nextState = blockstate;
        }
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]\nblockstate:{} pos:{}\nnextState:{}", blockstate, blockpos, nextState);
        if (CustomConcretePowderBlock.shouldSolidify(blockgetter, blockpos, blockstate)) {
            nextState = waterlogged(context.getLevel(), blockpos, this.concrete.withPropertiesOf(nextState));
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]shouldSolidify->>\nnextState:{}", nextState);
        }
        return nextState;
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction dir,
                                              @NotNull BlockState newState, @NotNull LevelAccessor levelAccessor,
                                              @NotNull BlockPos pos1, @NotNull BlockPos pos2) {
        BlockState nextState = super.updateShape(state, dir, newState, levelAccessor, pos1, pos2);
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:updateShape]\nstate:{} pos1:{}\nnextState:{} pos2:{}",
                state, pos1, nextState, pos2);
        if (CustomConcretePowderBlock.touchesLiquid(levelAccessor, pos1, nextState)) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:updateShape]\ntouchesLiquid->>\nnextState:{} pos2:{}",
                    nextState, pos2);
            return this.concrete.withPropertiesOf(nextState);
        }
        levelAccessor.scheduleTick(pos1, this, this.getDelayAfterPlace());
        return nextState;
    }

    @Override
    protected void onPlace(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                           @NotNull BlockState otherState, boolean flag) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onPlace]\nstate:{} pos:{}\notherState:{}", state, pos, otherState);
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        BlockState blockstate = fallingBlockEntity.getBlockState();
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onBrokenAfterFall]\nblockstate:{} pos:{}", blockstate, pos);
        if (blockstate.getValue(TYPE) == ModSlabTypes.DOUBLE) {
            fallingBlockEntity.spawnAtLocation(this);
        }
    }

    @Override
    protected void tick(@NotNull BlockState state, ServerLevel serverLevel,
                        @NotNull BlockPos pos, @NotNull RandomSource randomSource) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:tick]\nstate:{} pos:{}", state, pos);
        if (isFree(state, serverLevel.getBlockState(pos.below())) && pos.getY() >= serverLevel.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(serverLevel, pos, state);
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:tick]falling");
            this.falling(fallingblockentity);
        }
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            BlockPos blockpos = pos.below();
            if (isFree(state, level.getBlockState(blockpos))) {
                ParticleUtils.spawnParticleBelow(level, pos, randomSource,
                        new BlockParticleOption(ParticleTypes.FALLING_DUST, state));
            }
        }
    }
}
