package cn.autosec.onecore.uss.definition.lib;

import cn.autosec.onecore.uss.definition.registries.ModBlock;
import cn.autosec.onecore.uss.definition.registries.ModItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockLib extends ModRegistryLib<Block> {
    public ItemLib itemLib;
    public Block block;
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
    public ResourceLocation texture;
    public boolean hasSideTexture = false;
    public ResourceLocation sideTexture = null;
    public BlockLib(ModBlock modBlock, String name) {
        super(modBlock, name);
        itemLib = new ItemLib(modBlock.selfModItem(), name, true);
        block = modBlock.get();
    }

    @Override
    public BlockLib locale(String localeEn, String localeCn) {
        super.locale(localeEn, localeCn);
        this.itemLib.locale(localeEn, localeCn);
        return this;
    }

    @Override
    public BlockLib creativeTab(String tab) {
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

    public BlockLib texture(Block texture) {
        this.hasSideTexture = false;
        ResourceLocation name = ForgeRegistries.BLOCKS.getKey(texture);
        this.texture = new ResourceLocation(name.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + name.getPath());
        return this;
    }

    public BlockLib texture(Block texture, Block sideTexture) {
        this.hasSideTexture = true;
        ResourceLocation name1 = ForgeRegistries.BLOCKS.getKey(texture);
        this.texture = new ResourceLocation(name1.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + name1.getPath());
        ResourceLocation name2 = ForgeRegistries.BLOCKS.getKey(sideTexture);
        this.sideTexture = new ResourceLocation(name2.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + name2.getPath());
        return this;
    }

    public BlockLib texture(Block texture, Block sideTexture, String suffix) {
        this.hasSideTexture = true;
        ResourceLocation name1 = ForgeRegistries.BLOCKS.getKey(texture);
        this.texture = new ResourceLocation(name1.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + name1.getPath());
        ResourceLocation name2 = ForgeRegistries.BLOCKS.getKey(sideTexture);
        this.sideTexture = new ResourceLocation(name2.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + name2.getPath() + suffix);
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
