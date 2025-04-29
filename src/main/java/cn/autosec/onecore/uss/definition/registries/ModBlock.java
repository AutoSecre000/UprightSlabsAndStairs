package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlock extends ModRegistry<Block> {
    private final ModItem modItem;

    public ModBlock(RegistryObject<Block> registryObject, ModItem modItem, TagKey<Block> blockTagKey) {
        super(registryObject, blockTagKey);
        this.modItem = modItem;
    }

    public RegistryObject<Item> selfItem() {
        return modItem.self();
    }

    public ModItem selfModItem() {
        return modItem;
    }

    public Item getItem() {
        return modItem.get();
    }
}
