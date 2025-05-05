package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> ONECORE_BLOCKS =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(OneCore.MOD_ID, "onecore_blocks"));
    public static final TagKey<Block> ONECORE_WAXED =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(OneCore.MOD_ID, "onecore_waxed"));
    public static final TagKey<Block> ONECORE_COPPER =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(OneCore.MOD_ID, "onecore_copper"));

    public ModBlockTags() {
    }

    public static TagKey<Block> createBlockTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(OneCore.MOD_ID, name));
    }

    public static TagKey<Item> createItemTag(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(OneCore.MOD_ID, name));
    }
}
