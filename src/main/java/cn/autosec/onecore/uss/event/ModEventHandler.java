package cn.autosec.onecore.uss.event;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.custom.block.upright.copper.CustomWeatheringCopper;
import cn.autosec.onecore.uss.registry.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;

public class ModEventHandler {
    @SubscribeEvent
    public static void blockChanged(BlockEvent.BlockToolModificationEvent event) {
        UseOnContext context = event.getContext();
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        Block block = blockState.getBlock();
        OneCore.LOGGER.info("blockChanged player:{} item used:{} on block:{}",
                player, itemStack, block);
        if (itemStack.is(ItemTags.AXES) && blockState.is(ModBlockTags.ONECORE_COPPER)
                && blockState.is(ModBlockTags.ONECORE_WAXED)) {
            OneCore.LOGGER.info("wax off item used:{} on block:{}", itemStack, block);
            Optional<BlockState> optional = CustomWeatheringCopper.getWaxedOff(blockState);
            if (optional.isPresent()) {
                level.setBlock(blockPos, optional.get(), 11);
                level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3004, blockPos, 0);
                OneCore.LOGGER.info("[update] wax off block:{} to block:{}", block, optional.get().getBlock());
            }
        } else if (itemStack.is(ItemTags.AXES) && blockState.is(ModBlockTags.ONECORE_COPPER)
                && !blockState.is(ModBlockTags.ONECORE_WAXED)) {
            OneCore.LOGGER.info("scrape item used:{} on block:{}", itemStack, block);
            Optional<BlockState> optional = CustomWeatheringCopper.getPrevious(blockState);
            if (optional.isPresent()) {
                level.setBlock(blockPos, optional.get(), 11);
                level.playSound(player, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, blockPos, 0);
                OneCore.LOGGER.info("[update] scrape on block:{} to block:{}", block, optional.get().getBlock());
            }
        } else if (itemStack.is(Items.HONEYCOMB) && blockState.is(ModBlockTags.ONECORE_COPPER)
                && !blockState.is(ModBlockTags.ONECORE_WAXED)) {
            OneCore.LOGGER.info("wax on item used:{} on block:{}", itemStack, block);
            Optional<BlockState> optional = CustomWeatheringCopper.getWaxedOn(blockState);
            if (optional.isPresent()) {
                itemStack.shrink(1);
                level.setBlock(blockPos, optional.get(), 11);
                level.levelEvent(player, 3003, blockPos, 0);
                OneCore.LOGGER.info("[update] wax on block:{} to block:{}", block, optional.get().getBlock());
            }
        }
    }
}
