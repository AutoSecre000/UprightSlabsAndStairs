package cn.autosec.onecore.uss.definition.custom.block.normal.glass;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class StainedGlassStairsBlock extends GlassStairsBlock implements BeaconBeamBlock {
    private final DyeColor dyeColor;
    public StainedGlassStairsBlock(DyeColor dyeColor, BlockState blockState, Block glassBlock) {
        super(blockState, Properties.ofFullCopy(glassBlock).mapColor(dyeColor));
        this.dyeColor = dyeColor;
    }

    @Override
    public @NotNull DyeColor getColor() {
        return this.dyeColor;
    }
}
