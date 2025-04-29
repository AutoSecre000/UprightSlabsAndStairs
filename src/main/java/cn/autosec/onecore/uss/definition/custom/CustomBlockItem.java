package cn.autosec.onecore.uss.definition.custom;

import cn.autosec.onecore.uss.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class CustomBlockItem extends BlockItem {
    private boolean canFitInContainers = true;

    public CustomBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public CustomBlockItem fitInsideContainerItems(boolean canFitInContainers) {
        this.canFitInContainers = canFitInContainers;
        return this;
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return canFitInContainers;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable(Utils.ONECORE_DESCRIPTION_DETAIL_HEAD_TAG + getDescriptionId()).withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.translatable(Utils.ONECORE_DESCRIPTION_BRIEF_TAG).withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
