package cn.autosec.onecore.uss.definition;

import cn.autosec.onecore.uss.definition.registries.ModItem;
import net.minecraft.world.item.Item;

public class ItemLib extends ModRegistryLib<Item> {
    public boolean isBlockItem = false;
    public enum TAG_KEY {
        NORMAL,
        FOOD,
        AXE,
        HOE,
        PICKAXE,
        SHOVEL,
        SWORD,
        ARMOR,
        HANDLE,
    }

    public TAG_KEY tagKey = TAG_KEY.NORMAL;
    public boolean customModel = false;

    public ItemLib(ModItem modItem, String name) {
        super(modItem, name);
    }

    public ItemLib(ModItem modItem, String name, Boolean isBlockItem) {
        super(modItem, name);
        this.isBlockItem = isBlockItem;
    }

    @Override
    public ItemLib locale(String localeEn, String localeCn) {
        super.locale(localeEn, localeCn);
        return this;
    }

    public ItemLib description(String descriptionEn, String descriptionCn) {
        this.descriptionLocaleEn = descriptionEn;
        this.descriptionLocaleCn = descriptionCn;
        return this;
    }

    @Override
    public ItemLib creativeTab(String tab) {
        this.creativeTab = tab;
        return this;
    }

    public ItemLib tagKey(TAG_KEY tagKey) {
        this.tagKey = tagKey;
        return this;
    }

    public ItemLib customModel() {
        this.customModel = true;
        return this;
    }

    public boolean isTool() {
        return tagKey == TAG_KEY.AXE || tagKey == TAG_KEY.HOE || tagKey == TAG_KEY.PICKAXE
                || tagKey == TAG_KEY.SHOVEL || tagKey == TAG_KEY.SWORD || tagKey == TAG_KEY.HANDLE;
    }

    public boolean isHandle() {
        return tagKey == TAG_KEY.HANDLE;
    }
}
