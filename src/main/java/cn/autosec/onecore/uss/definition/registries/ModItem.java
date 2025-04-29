package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItem extends ModRegistry<Item> {

    public ModItem(RegistryObject<Item> registryObject, TagKey<Item> itemTagKey) {
        super(registryObject, itemTagKey);
    }
}
