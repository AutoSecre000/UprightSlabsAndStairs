package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModBlock extends ModRegistry<DeferredBlock<Block>, Block> {
    private final ModItem modItem;

    public ModBlock(DeferredBlock<Block> registryObject, ModItem modItem, TagKey<Block> blockTagKey) {
        super(registryObject, blockTagKey);
        this.modItem = modItem;
    }

    public DeferredItem<Item> selfItem() {
        return modItem.self();
    }

    public ModItem selfModItem() {
        return modItem;
    }

    public Item getItem() {
        return modItem.get();
    }

    @Override
    public Block get() {
        return this.registryObject.get();
    }
}
