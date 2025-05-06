package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItem extends ModRegistry<DeferredItem<Item>, Item> {

    public ModItem(DeferredItem<Item> registryObject, TagKey<Item> itemTagKey) {
        super(registryObject, itemTagKey);
    }

    @Override
    public Item get() {
        return this.registryObject.get();
    }
}
