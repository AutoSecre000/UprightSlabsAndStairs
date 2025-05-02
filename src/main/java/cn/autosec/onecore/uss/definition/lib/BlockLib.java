package cn.autosec.onecore.uss.definition.lib;

import cn.autosec.onecore.uss.definition.registries.ModBlock;
import cn.autosec.onecore.uss.definition.registries.ModItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public class BlockLib extends ModRegistryLib<ModBlock> {
    public ItemLib itemLib;
    public ModBlock block;
    public boolean isSimpleCube = true;
    public enum MINEABLE_TYPE {
        ALL, AXE, HOE, PICKAXE, SHOVEL, SWORD
    }
    public enum LEVEL {
        WOOD, STONE_TOOLS, IRON_TOOLS, DIAMOND_TOOLS,
    }
    public MINEABLE_TYPE mineableType = MINEABLE_TYPE.ALL;
    public LEVEL mineableLevel = LEVEL.WOOD;
    public boolean isDropSelf = true;
    public boolean isSlab = false;
    public boolean isStairs = false;
    public boolean isWooden = false;
    public boolean isGlass = false;
    public ModItem dropItem;
    public float minDrop = 1f;
    public float maxDrop = 1f;
    public boolean applyToFortune = false;
    public BlockLib(ModBlock modBlock, String name) {
        super(modBlock, name);
        itemLib = new ItemLib(modBlock.selfModItem(), name, true);
        block = modBlock;
    }

    @Override
    public BlockLib locale(String localeEn, String localeCn) {
        super.locale(localeEn, localeCn);
        this.itemLib.locale(localeEn, localeCn);
        return this;
    }

    @Override
    public BlockLib creativeTab(RegistryKey<ItemGroup> tab) {
        this.creativeTab = tab;
        return this;
    }

    public BlockLib complexCube() {
        isSimpleCube = false;
        return this;
    }

    public BlockLib mineableWithAxe() {
        mineableType = MINEABLE_TYPE.AXE;
        return this;
    }

    public BlockLib mineableWithHoe() {
        mineableType = MINEABLE_TYPE.HOE;
        return this;
    }

    public BlockLib mineableWithPickaxe() {
        mineableType = MINEABLE_TYPE.PICKAXE;
        return this;
    }

    public BlockLib mineableWithPickaxe(LEVEL mineableLevel) {
        mineableType = MINEABLE_TYPE.PICKAXE;
        this.mineableLevel = mineableLevel;
        return this;
    }

    public BlockLib mineableWithShovel() {
        mineableType = MINEABLE_TYPE.SHOVEL;
        return this;
    }

    public BlockLib swordEfficient() {
        mineableType = MINEABLE_TYPE.SWORD;
        return this;
    }

    public BlockLib wooden() {
        this.isWooden = true;
        return this;
    }

    public BlockLib slab() {
        this.isSlab = true;
        return this;
    }

    public BlockLib stairs() {
        this.isStairs = true;
        return this;
    }

    public BlockLib translucent() {
        this.isGlass = true;
        return this;
    }

    public BlockLib drops(ModItem dropItem, float minDrop, float maxDrop, boolean applyToFortune) {
        isDropSelf = false;
        this.dropItem = dropItem;
        this.minDrop = minDrop;
        this.maxDrop = maxDrop;
        this.applyToFortune = applyToFortune;
        return this;
    }
}
