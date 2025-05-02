package cn.autosec.onecore.uss.definition.custom;

import cn.autosec.onecore.uss.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class CustomBlockItem extends BlockItem {
    public CustomBlockItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        // if (Screen.hasShiftDown()) {
        tooltip.add(Text.translatable(Utils.ONECORE_DESCRIPTION_DETAIL_HEAD_TAG + getTranslationKey()).formatted(Formatting.AQUA));
        // } else {
        //    tooltip.add(Text.translatable(Utils.ONECORE_DESCRIPTION_BRIEF_TAG).formatted(Formatting.YELLOW));
        // }
        super.appendTooltip(itemStack, context, tooltip, type);
    }
}
