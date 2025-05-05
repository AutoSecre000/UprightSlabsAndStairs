package cn.autosec.onecore.uss.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> ONECORE_BLOCKS = create("onecore_blocks");
    public static final TagKey<Block> ONECORE_WAXED = create("onecore_waxed");
    public static final TagKey<Block> ONECORE_COPPER = create("onecore_copper");

    private ModBlockTags() {
    }

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(name));
    }

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }
}
