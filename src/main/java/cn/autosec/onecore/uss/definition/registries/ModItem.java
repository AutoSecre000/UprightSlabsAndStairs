package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class ModItem extends ModRegistry<Item> {

    public ModItem(Item registryObject, TagKey<Item> itemTagKey) {
        super(registryObject, itemTagKey);
    }
}
