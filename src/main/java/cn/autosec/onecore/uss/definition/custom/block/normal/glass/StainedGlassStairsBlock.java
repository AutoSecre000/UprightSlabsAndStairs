package cn.autosec.onecore.uss.definition.custom.block.normal.glass;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.NotNull;

public class StainedGlassStairsBlock extends GlassStairsBlock implements Stainable {
    private final DyeColor dyeColor;
    public StainedGlassStairsBlock(DyeColor dyeColor, BlockState blockState, Block glassBlock) {
        super(blockState, AbstractBlock.Settings.copy(glassBlock).mapColor(dyeColor));
        this.dyeColor = dyeColor;
    }

    @Override
    public @NotNull DyeColor getColor() {
        return this.dyeColor;
    }
}
