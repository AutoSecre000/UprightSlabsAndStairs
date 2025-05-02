package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class ModBlock extends ModRegistry<Block> {
    private final ModItem modItem;

    public ModBlock(Block registryObject, ModItem modItem, TagKey<Block> blockTagKey) {
        super(registryObject, blockTagKey);
        this.modItem = modItem;
    }

    public Item selfItem() {
        return modItem.self();
    }

    public Item getItem() {
        return modItem.get();
    }

    public ModItem selfModItem() {
        return modItem;
    }
}
