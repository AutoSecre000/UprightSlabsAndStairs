package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.definition.ItemLib;
import cn.autosec.onecore.uss.definition.custom.CustomBlockItem;
import cn.autosec.onecore.uss.definition.custom.block.*;
import cn.autosec.onecore.uss.definition.registries.ModBlock;
import cn.autosec.onecore.uss.definition.BlockLib;
import cn.autosec.onecore.uss.definition.registries.ModItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    /* Block state type */
    public static final EnumProperty<ModSlabTypes> UPRIGHT_SLAB_TYPE = EnumProperty.create("type", ModSlabTypes.class);
    public static final EnumProperty<ModStairTypes> UPRIGHT_STAIR_TYPE = EnumProperty.create("type", ModStairTypes.class);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OneCore.MODID);

    private static ModBlock registerBlockAndItemForUprightSlab(String name, Supplier<UprightSlabBlock> sup) {
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(name, sup);
        RegistryObject<Item> itemRegistryObject = ModItems.ITEMS.register(name,
                () -> new CustomBlockItem(blockRegistryObject.get(), new Item.Properties()));
        TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK,
                new ResourceLocation(OneCore.MODID, name + "_tag"));
        TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM,
                new ResourceLocation(OneCore.MODID, name + "_tag"));
        ModItem modItem = new ModItem(itemRegistryObject, itemTagKey);
        return new ModBlock(blockRegistryObject, modItem, blockTagKey);
    }

    private static ModBlock registerBlockAndItemForUprightStairs(String name, Supplier<UprightStairsBlock> sup) {
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(name, sup);
        RegistryObject<Item> itemRegistryObject = ModItems.ITEMS.register(name,
                () -> new CustomBlockItem(blockRegistryObject.get(), new Item.Properties()));
        TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK,
                new ResourceLocation(OneCore.MODID, name + "_tag"));
        TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM,
                new ResourceLocation(OneCore.MODID, name + "_tag"));
        ModItem modItem = new ModItem(itemRegistryObject, itemTagKey);
        return new ModBlock(blockRegistryObject, modItem, blockTagKey);
    }

    /* Register blocks */
    public static final ModBlock STONE_UPRIGHT_SLAB = registerBlockAndItemForUprightSlab("stone_upright_slab", () -> new StoneUprightSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final ModBlock STONE_UPRIGHT_STAIRS = registerBlockAndItemForUprightStairs("stone_upright_stairs", () -> new StoneUprightStairsBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final ModBlock GLASS_UPRIGHT_SLAB = registerBlockAndItemForUprightSlab("glass_upright_slab", () -> new GlassUprightSlabBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops()));
    public static final ModBlock GLASS_UPRIGHT_STAIRS = registerBlockAndItemForUprightStairs("glass_upright_stairs", () -> new GlassUprightStairsBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops()));
    public static final ModBlock OAK_UPRIGHT_SLAB = registerBlockAndItemForUprightSlab("oak_upright_slab", () -> new OakUprightSlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock OAK_UPRIGHT_STAIRS = registerBlockAndItemForUprightStairs("oak_upright_stairs", () -> new OakUprightStairsBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).requiresCorrectToolForDrops()));

    private static final List<BlockLib> blockLib = new ArrayList<>();

    public static List<BlockLib> getBlockLib() {
        if (blockLib.isEmpty()) {
            blockLib.add(new BlockLib(STONE_UPRIGHT_SLAB, "stone_upright_slab").locale("Stone Upright Slab", "石头台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slabBehavior().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(STONE_UPRIGHT_STAIRS, "stone_upright_stairs").locale("Stone Upright Stairs", "石头楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slabBehavior().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(GLASS_UPRIGHT_SLAB, "glass_upright_slab").locale("Glass Upright Slab", "玻璃台阶（竖直）").slabBehavior().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(GLASS_UPRIGHT_STAIRS, "glass_upright_stairs").locale("Glass Upright Stairs", "玻璃楼梯（竖直）").slabBehavior().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(OAK_UPRIGHT_SLAB, "oak_upright_slab").locale("Oak Upright Slab", "橡木台阶（竖直）").slabBehavior().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(OAK_UPRIGHT_STAIRS, "oak_upright_stairs").locale("Oak Upright Stairs", "橡木楼梯（竖直）").slabBehavior().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
        }
        return blockLib;
    }

    public static ItemLib getFirstBlockItemLib(String creativeTab) {
        List<BlockLib> localBlockLib = getBlockLib();
        for (BlockLib blockLib : localBlockLib) {
            if (blockLib.creativeTab.equals(creativeTab)) {
                return blockLib.itemLib;
            }
        }
        return localBlockLib.get(0).itemLib;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            // add new block item to vanilla tabs
            // event.accept(ALUMINIUM_ORE_ITEM);
        }
    }
}
