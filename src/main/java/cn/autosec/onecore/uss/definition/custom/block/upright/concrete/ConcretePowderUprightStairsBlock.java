package cn.autosec.onecore.uss.definition.custom.block.upright.concrete;

import cn.autosec.onecore.uss.definition.custom.block.normal.concrete.CustomConcretePowderBlock;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
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

public class ConcretePowderUprightStairsBlock extends StairsBlock implements CustomConcretePowderBlock {
    public static final MapCodec<ConcretePowderUprightStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_341829_ -> p_341829_.group(Registries.BLOCK.getCodec().fieldOf("concrete").forGetter(p_313163_ -> p_313163_.concrete), BlockState.CODEC.fieldOf("base_state").forGetter(p_309296_ -> p_309296_.baseBlockState), createSettingsCodec())
                    .apply(p_341829_, ConcretePowderUprightStairsBlock::new)
    );
    private final Block concrete;

    public @NotNull MapCodec<ConcretePowderUprightStairsBlock> codec() {
        return CODEC;
    }

    public ConcretePowderUprightStairsBlock(Block concrete, BlockState blockState, Settings properties) {
        super(blockState, properties);
        this.concrete = concrete;
    }

    private BlockState waterlogged(World world, BlockPos pos, BlockState state) {
        FluidState fluidstate = world.getFluidState(pos);
        return state.with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public void onLanding(@NotNull World world, @NotNull BlockPos pos, @NotNull BlockState state,
                          @NotNull BlockState otherState, @NotNull FallingBlockEntity entity) {
        if (CustomConcretePowderBlock.shouldHarden(world, pos, state)) {
            world.setBlockState(pos, waterlogged(world, pos, this.concrete.getStateWithProperties(state)), 3);
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
        return CustomConcretePowderBlock.shouldHarden(blockView, blockpos, blockstate) ?
                waterlogged(ctx.getWorld(), blockpos, this.concrete.getStateWithProperties(nextState)) : nextState;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        BlockState nextState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        if (CustomConcretePowderBlock.hardensOnAnySide(world, pos)) {
            return this.concrete.getStateWithProperties(nextState);
        }
        world.scheduleBlockTick(pos, this, this.getFallDelay());
        return nextState;
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, this.getFallDelay());
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (CustomConcretePowderBlock.canFallThrough(world.getBlockState(pos.down())) &&
                pos.getY() >= world.getBottomY()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.spawnFromBlock(world, pos, state);
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
