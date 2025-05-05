package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.definition.custom.block.normal.glass.GlassStairsBlock;
import cn.autosec.onecore.uss.definition.custom.block.normal.stone.SmoothStoneStairsBlock;
import cn.autosec.onecore.uss.definition.custom.block.normal.stone.SmoothStoneTransverseStairsBlock;
import cn.autosec.onecore.uss.definition.custom.block.upright.glass.GlassUprightSlabBlock;
import cn.autosec.onecore.uss.definition.custom.block.upright.glass.GlassUprightStairsBlock;
import cn.autosec.onecore.uss.definition.custom.block.upright.stone.*;
import cn.autosec.onecore.uss.definition.lib.ItemLib;
import cn.autosec.onecore.uss.definition.custom.CustomBlockItem;
import cn.autosec.onecore.uss.definition.custom.block.upright.*;
import cn.autosec.onecore.uss.definition.custom.block.upright.copper.*;
import cn.autosec.onecore.uss.definition.custom.block.normal.glass.GlassSlabBlock;
import cn.autosec.onecore.uss.definition.custom.block.upright.wooden.*;
import cn.autosec.onecore.uss.definition.registries.ModBlock;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.definition.registries.ModItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
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
    public static final EnumProperty<ModSlabTypes> UPRIGHT_SLAB_TYPE =
            EnumProperty.create("type", ModSlabTypes.class);
    public static final EnumProperty<ModStairTypes> UPRIGHT_STAIR_TYPE =
            EnumProperty.create("type", ModStairTypes.class);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OneCore.MODID);

    private static ModBlock registerSlabBlock(String name, Supplier<SlabBlock> sup) {
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(name, sup);
        RegistryObject<Item> itemRegistryObject = ModItems.ITEMS.register(name,
                () -> new CustomBlockItem(blockRegistryObject.get(), new Item.Properties()));
        TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        ModItem modItem = new ModItem(itemRegistryObject, itemTagKey);
        return new ModBlock(blockRegistryObject, modItem, blockTagKey);
    }

    private static ModBlock registerStairsBlock(String name, Supplier<StairBlock> sup) {
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(name, sup);
        RegistryObject<Item> itemRegistryObject = ModItems.ITEMS.register(name,
                () -> new CustomBlockItem(blockRegistryObject.get(), new Item.Properties()));
        TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        ModItem modItem = new ModItem(itemRegistryObject, itemTagKey);
        return new ModBlock(blockRegistryObject, modItem, blockTagKey);
    }

    private static ModBlock registerUprightSlabBlock(String name, Supplier<UprightSlabBlock> sup) {
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(name, sup);
        RegistryObject<Item> itemRegistryObject = ModItems.ITEMS.register(name,
                () -> new CustomBlockItem(blockRegistryObject.get(), new Item.Properties()));
        TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        ModItem modItem = new ModItem(itemRegistryObject, itemTagKey);
        return new ModBlock(blockRegistryObject, modItem, blockTagKey);
    }

    private static ModBlock registerUprightStairsBlock(String name, Supplier<UprightStairsBlock> sup) {
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(name, sup);
        RegistryObject<Item> itemRegistryObject = ModItems.ITEMS.register(name,
                () -> new CustomBlockItem(blockRegistryObject.get(), new Item.Properties()));
        TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(OneCore.MODID, name + "_tag"));
        ModItem modItem = new ModItem(itemRegistryObject, itemTagKey);
        return new ModBlock(blockRegistryObject, modItem, blockTagKey);
    }

    /* Register blocks */
    public static final ModBlock OAK_UPRIGHT_SLAB = registerUprightSlabBlock("oak_upright_slab", () -> new OakUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final ModBlock OAK_UPRIGHT_STAIRS = registerUprightStairsBlock("oak_upright_stairs", () -> new OakUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final ModBlock SPRUCE_UPRIGHT_SLAB = registerUprightSlabBlock("spruce_upright_slab", () -> new SpruceUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB)));
    public static final ModBlock SPRUCE_UPRIGHT_STAIRS = registerUprightStairsBlock("spruce_upright_stairs", () -> new SpruceUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB)));
    public static final ModBlock BIRCH_UPRIGHT_SLAB = registerUprightSlabBlock("birch_upright_slab", () -> new BirchUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB)));
    public static final ModBlock BIRCH_UPRIGHT_STAIRS = registerUprightStairsBlock("birch_upright_stairs", () -> new BirchUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB)));
    public static final ModBlock JUNGLE_UPRIGHT_SLAB = registerUprightSlabBlock("jungle_upright_slab", () -> new JungleUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB)));
    public static final ModBlock JUNGLE_UPRIGHT_STAIRS = registerUprightStairsBlock("jungle_upright_stairs", () -> new JungleUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB)));
    public static final ModBlock ACACIA_UPRIGHT_SLAB = registerUprightSlabBlock("acacia_upright_slab", () -> new AcaciaUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
    public static final ModBlock ACACIA_UPRIGHT_STAIRS = registerUprightStairsBlock("acacia_upright_stairs", () -> new AcaciaUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
    public static final ModBlock CHERRY_UPRIGHT_SLAB = registerUprightSlabBlock("cherry_upright_slab", () -> new CherryUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB)));
    public static final ModBlock CHERRY_UPRIGHT_STAIRS = registerUprightStairsBlock("cherry_upright_stairs", () -> new CherryUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB)));
    public static final ModBlock DARK_OAK_UPRIGHT_SLAB = registerUprightSlabBlock("dark_oak_upright_slab", () -> new DarkOakUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB)));
    public static final ModBlock DARK_OAK_UPRIGHT_STAIRS = registerUprightStairsBlock("dark_oak_upright_stairs", () -> new DarkOakUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB)));
    public static final ModBlock MANGROVE_UPRIGHT_SLAB = registerUprightSlabBlock("mangrove_upright_slab", () -> new MangroveUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB)));
    public static final ModBlock MANGROVE_UPRIGHT_STAIRS = registerUprightStairsBlock("mangrove_upright_stairs", () -> new MangroveUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB)));
    public static final ModBlock BAMBOO_UPRIGHT_SLAB = registerUprightSlabBlock("bamboo_upright_slab", () -> new BambooUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB)));
    public static final ModBlock BAMBOO_UPRIGHT_STAIRS = registerUprightStairsBlock("bamboo_upright_stairs", () -> new BambooUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB)));
    public static final ModBlock BAMBOO_MOSAIC_UPRIGHT_SLAB = registerUprightSlabBlock("bamboo_mosaic_upright_slab", () -> new BambooMosaicUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC_SLAB)));
    public static final ModBlock BAMBOO_MOSAIC_UPRIGHT_STAIRS = registerUprightStairsBlock("bamboo_mosaic_upright_stairs", () -> new BambooMosaicUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC_SLAB)));
    public static final ModBlock STONE_UPRIGHT_SLAB = registerUprightSlabBlock("stone_upright_slab", () -> new StoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock STONE_UPRIGHT_STAIRS = registerUprightStairsBlock("stone_upright_stairs", () -> new StoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_STONE_UPRIGHT_SLAB = registerUprightSlabBlock("smooth_stone_upright_slab", () -> new SmoothStoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_STONE_UPRIGHT_STAIRS = registerUprightStairsBlock("smooth_stone_upright_stairs", () -> new SmoothStoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SANDSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("sandstone_upright_slab", () -> new SandstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SANDSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("sandstone_upright_stairs", () -> new SandstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock CUT_SANDSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("cut_sandstone_upright_slab", () -> new CutSandstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock CUT_SANDSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("cut_sandstone_upright_stairs", () -> new CutSandstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock COBBLESTONE_UPRIGHT_SLAB = registerUprightSlabBlock("cobblestone_upright_slab", () -> new CobblestoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock COBBLESTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("cobblestone_upright_stairs", () -> new CobblestoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("brick_upright_slab", () -> new BrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("brick_upright_stairs", () -> new BrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock STONE_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("stone_brick_upright_slab", () -> new StoneBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock STONE_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("stone_brick_upright_stairs", () -> new StoneBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock MUD_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("mud_brick_upright_slab", () -> new MudBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock MUD_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("mud_brick_upright_stairs", () -> new MudBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock NETHER_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("nether_brick_upright_slab", () -> new NetherBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock NETHER_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("nether_brick_upright_stairs", () -> new NetherBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock PRISMARINE_UPRIGHT_SLAB = registerUprightSlabBlock("prismarine_upright_slab", () -> new PrismarineUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock PRISMARINE_UPRIGHT_STAIRS = registerUprightStairsBlock("prismarine_upright_stairs", () -> new PrismarineUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock PRISMARINE_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("prismarine_brick_upright_slab", () -> new PrismarineBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock PRISMARINE_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("prismarine_brick_upright_stairs", () -> new PrismarineBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DARK_PRISMARINE_UPRIGHT_SLAB = registerUprightSlabBlock("dark_prismarine_upright_slab", () -> new DarkPrismarineUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DARK_PRISMARINE_UPRIGHT_STAIRS = registerUprightStairsBlock("dark_prismarine_upright_stairs", () -> new DarkPrismarineUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock QUARTZ_UPRIGHT_SLAB = registerUprightSlabBlock("quartz_upright_slab", () -> new QuartzUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock QUARTZ_UPRIGHT_STAIRS = registerUprightStairsBlock("quartz_upright_stairs", () -> new QuartzUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock RED_SANDSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("red_sandstone_upright_slab", () -> new RedSandstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock RED_SANDSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("red_sandstone_upright_stairs", () -> new RedSandstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock CUT_RED_SANDSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("cut_red_sandstone_upright_slab", () -> new CutRedSandstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock CUT_RED_SANDSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("cut_red_sandstone_upright_stairs", () -> new CutRedSandstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock PURPUR_UPRIGHT_SLAB = registerUprightSlabBlock("purpur_upright_slab", () -> new PurpurUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock PURPUR_UPRIGHT_STAIRS = registerUprightStairsBlock("purpur_upright_stairs", () -> new PurpurUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_GRANITE_UPRIGHT_SLAB = registerUprightSlabBlock("polished_granite_upright_slab", () -> new PolishedGraniteUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_GRANITE_UPRIGHT_STAIRS = registerUprightStairsBlock("polished_granite_upright_stairs", () -> new PolishedGraniteUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_RED_SANDSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("smooth_red_sandstone_upright_slab", () -> new SmoothRedSandstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_RED_SANDSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("smooth_red_sandstone_upright_stairs", () -> new SmoothRedSandstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock MOSSY_STONE_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("mossy_stone_brick_upright_slab", () -> new MossyStoneBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock MOSSY_STONE_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("mossy_stone_brick_upright_stairs", () -> new MossyStoneBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_DIORITE_UPRIGHT_SLAB = registerUprightSlabBlock("polished_diorite_upright_slab", () -> new PolishedDioriteUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_DIORITE_UPRIGHT_STAIRS = registerUprightStairsBlock("polished_diorite_upright_stairs", () -> new PolishedDioriteUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock MOSSY_COBBLESTONE_UPRIGHT_SLAB = registerUprightSlabBlock("mossy_cobblestone_upright_slab", () -> new MossyCobblestoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock MOSSY_COBBLESTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("mossy_cobblestone_upright_stairs", () -> new MossyCobblestoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock END_STONE_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("end_stone_brick_upright_slab", () -> new EndStoneBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock END_STONE_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("end_stone_brick_upright_stairs", () -> new EndStoneBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_SANDSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("smooth_sandstone_upright_slab", () -> new SmoothSandstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_SANDSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("smooth_sandstone_upright_stairs", () -> new SmoothSandstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_QUARTZ_UPRIGHT_SLAB = registerUprightSlabBlock("smooth_quartz_upright_slab", () -> new SmoothQuartzUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_QUARTZ_UPRIGHT_STAIRS = registerUprightStairsBlock("smooth_quartz_upright_stairs", () -> new SmoothQuartzUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock GRANITE_UPRIGHT_SLAB = registerUprightSlabBlock("granite_upright_slab", () -> new GraniteUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock GRANITE_UPRIGHT_STAIRS = registerUprightStairsBlock("granite_upright_stairs", () -> new GraniteUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock ANDESITE_UPRIGHT_SLAB = registerUprightSlabBlock("andesite_upright_slab", () -> new AndesiteUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock ANDESITE_UPRIGHT_STAIRS = registerUprightStairsBlock("andesite_upright_stairs", () -> new AndesiteUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock RED_NETHER_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("red_nether_brick_upright_slab", () -> new RedNetherBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock RED_NETHER_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("red_nether_brick_upright_stairs", () -> new RedNetherBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_ANDESITE_UPRIGHT_SLAB = registerUprightSlabBlock("polished_andesite_upright_slab", () -> new PolishedAndesiteUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_ANDESITE_UPRIGHT_STAIRS = registerUprightStairsBlock("polished_andesite_upright_stairs", () -> new PolishedAndesiteUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DIORITE_UPRIGHT_SLAB = registerUprightSlabBlock("diorite_upright_slab", () -> new DioriteUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DIORITE_UPRIGHT_STAIRS = registerUprightStairsBlock("diorite_upright_stairs", () -> new DioriteUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock CRIMSON_UPRIGHT_SLAB = registerUprightSlabBlock("crimson_upright_slab", () -> new CrimsonUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)));
    public static final ModBlock CRIMSON_UPRIGHT_STAIRS = registerUprightStairsBlock("crimson_upright_stairs", () -> new CrimsonUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)));
    public static final ModBlock WARPED_UPRIGHT_SLAB = registerUprightSlabBlock("warped_upright_slab", () -> new WarpedUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB)));
    public static final ModBlock WARPED_UPRIGHT_STAIRS = registerUprightStairsBlock("warped_upright_stairs", () -> new WarpedUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB)));
    public static final ModBlock BLACKSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("blackstone_upright_slab", () -> new BlackstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock BLACKSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("blackstone_upright_stairs", () -> new BlackstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_BLACKSTONE_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("polished_blackstone_brick_upright_slab", () -> new PolishedBlackstoneBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_BLACKSTONE_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("polished_blackstone_brick_upright_stairs", () -> new PolishedBlackstoneBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_BLACKSTONE_UPRIGHT_SLAB = registerUprightSlabBlock("polished_blackstone_upright_slab", () -> new PolishedBlackstoneUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_BLACKSTONE_UPRIGHT_STAIRS = registerUprightStairsBlock("polished_blackstone_upright_stairs", () -> new PolishedBlackstoneUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock OXIDIZED_CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("oxidized_cut_copper_upright_slab", () -> new OxidizedCutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("oxidized_cut_copper_upright_stairs", () -> new OxidizedCutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WEATHERED_CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("weathered_cut_copper_upright_slab", () -> new WeatheredCutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WEATHERED_CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("weathered_cut_copper_upright_stairs", () -> new WeatheredCutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock EXPOSED_CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("exposed_cut_copper_upright_slab", () -> new ExposedCutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock EXPOSED_CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("exposed_cut_copper_upright_stairs", () -> new ExposedCutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("cut_copper_upright_slab", () -> new CutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("cut_copper_upright_stairs", () -> new CutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("waxed_oxidized_cut_copper_upright_slab", () -> new WaxedOxidizedCutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("waxed_oxidized_cut_copper_upright_stairs", () -> new WaxedOxidizedCutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_WEATHERED_CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("waxed_weathered_cut_copper_upright_slab", () -> new WaxedWeatheredCutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_WEATHERED_CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("waxed_weathered_cut_copper_upright_stairs", () -> new WaxedWeatheredCutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_EXPOSED_CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("waxed_exposed_cut_copper_upright_slab", () -> new WaxedExposedCutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_EXPOSED_CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("waxed_exposed_cut_copper_upright_stairs", () -> new WaxedExposedCutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_CUT_COPPER_UPRIGHT_SLAB = registerUprightSlabBlock("waxed_cut_copper_upright_slab", () -> new WaxedCutCopperUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock WAXED_CUT_COPPER_UPRIGHT_STAIRS = registerUprightStairsBlock("waxed_cut_copper_upright_stairs", () -> new WaxedCutCopperUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_CUT_COPPER_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock COBBLED_DEEPSLATE_UPRIGHT_SLAB = registerUprightSlabBlock("cobbled_deepslate_upright_slab", () -> new CobbledDeepslateUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock COBBLED_DEEPSLATE_UPRIGHT_STAIRS = registerUprightStairsBlock("cobbled_deepslate_upright_stairs", () -> new CobbledDeepslateUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_DEEPSLATE_UPRIGHT_SLAB = registerUprightSlabBlock("polished_deepslate_upright_slab", () -> new PolishedDeepslateUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock POLISHED_DEEPSLATE_UPRIGHT_STAIRS = registerUprightStairsBlock("polished_deepslate_upright_stairs", () -> new PolishedDeepslateUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DEEPSLATE_TILE_UPRIGHT_SLAB = registerUprightSlabBlock("deepslate_tile_upright_slab", () -> new DeepslateTileUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DEEPSLATE_TILE_UPRIGHT_STAIRS = registerUprightStairsBlock("deepslate_tile_upright_stairs", () -> new DeepslateTileUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILE_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DEEPSLATE_BRICK_UPRIGHT_SLAB = registerUprightSlabBlock("deepslate_brick_upright_slab", () -> new DeepslateBrickUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock DEEPSLATE_BRICK_UPRIGHT_STAIRS = registerUprightStairsBlock("deepslate_brick_upright_stairs", () -> new DeepslateBrickUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB).requiresCorrectToolForDrops()));
    public static final ModBlock GLASS_UPRIGHT_SLAB = registerUprightSlabBlock("glass_upright_slab", () -> new GlassUprightSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final ModBlock GLASS_UPRIGHT_STAIRS = registerUprightStairsBlock("glass_upright_stairs", () -> new GlassUprightStairsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));

    public static final ModBlock GLASS_SLAB = registerSlabBlock("glass_slab", () -> new GlassSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final ModBlock GLASS_STAIRS = registerStairsBlock("glass_stairs", () -> new GlassStairsBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final ModBlock SMOOTH_STONE_STAIRS = registerStairsBlock("smooth_stone_stairs", () -> new SmoothStoneStairsBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE).requiresCorrectToolForDrops()));
    public static final ModBlock SMOOTH_STONE_TRANSVERSE_STAIRS = registerStairsBlock("smooth_stone_transverse_stairs", () -> new SmoothStoneTransverseStairsBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE).requiresCorrectToolForDrops()));

    private static final List<BlockLib> blockLib = new ArrayList<>();

    public static List<BlockLib> getBlockLib() {
        if (blockLib.isEmpty()) {
            blockLib.add(new BlockLib(OAK_UPRIGHT_SLAB, "oak_upright_slab").locale("Oak Upright Slab", "橡木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(OAK_UPRIGHT_STAIRS, "oak_upright_stairs").locale("Oak Upright Stairs", "橡木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SPRUCE_UPRIGHT_SLAB, "spruce_upright_slab").locale("Spruce Upright Slab", "云杉木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SPRUCE_UPRIGHT_STAIRS, "spruce_upright_stairs").locale("Spruce Upright Stairs", "云杉木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BIRCH_UPRIGHT_SLAB, "birch_upright_slab").locale("Birch Upright Slab", "白桦木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BIRCH_UPRIGHT_STAIRS, "birch_upright_stairs").locale("Birch Upright Stairs", "白桦木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(JUNGLE_UPRIGHT_SLAB, "jungle_upright_slab").locale("Jungle Upright Slab", "丛林木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(JUNGLE_UPRIGHT_STAIRS, "jungle_upright_stairs").locale("Jungle Upright Stairs", "丛林木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(ACACIA_UPRIGHT_SLAB, "acacia_upright_slab").locale("Acacia Upright Slab", "金合欢木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(ACACIA_UPRIGHT_STAIRS, "acacia_upright_stairs").locale("Acacia Upright Stairs", "金合欢木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CHERRY_UPRIGHT_SLAB, "cherry_upright_slab").locale("Cherry Upright Slab", "樱花木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CHERRY_UPRIGHT_STAIRS, "cherry_upright_stairs").locale("Cherry Upright Stairs", "樱花木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DARK_OAK_UPRIGHT_SLAB, "dark_oak_upright_slab").locale("Dark Oak Upright Slab", "深色橡木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DARK_OAK_UPRIGHT_STAIRS, "dark_oak_upright_stairs").locale("Dark Oak Upright Stairs", "深色橡木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MANGROVE_UPRIGHT_SLAB, "mangrove_upright_slab").locale("Mangrove Upright Slab", "红树木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MANGROVE_UPRIGHT_STAIRS, "mangrove_upright_stairs").locale("Mangrove Upright Stairs", "红树木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BAMBOO_UPRIGHT_SLAB, "bamboo_upright_slab").locale("Bamboo Upright Slab", "竹台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BAMBOO_UPRIGHT_STAIRS, "bamboo_upright_stairs").locale("Bamboo Upright Stairs", "竹楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BAMBOO_MOSAIC_UPRIGHT_SLAB, "bamboo_mosaic_upright_slab").locale("Bamboo Mosaic Upright Slab", "竹马赛克台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BAMBOO_MOSAIC_UPRIGHT_STAIRS, "bamboo_mosaic_upright_stairs").locale("Bamboo Mosaic Upright Stairs", "竹马赛克楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(STONE_UPRIGHT_SLAB, "stone_upright_slab").locale("Stone Upright Slab", "石头台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(STONE_UPRIGHT_STAIRS, "stone_upright_stairs").locale("Stone Upright Stairs", "石头楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_STONE_UPRIGHT_SLAB, "smooth_stone_upright_slab").locale("Smooth Stone Upright Slab", "平滑石头台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_STONE_UPRIGHT_STAIRS, "smooth_stone_upright_stairs").locale("Smooth Stone Upright Stairs", "平滑石头楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SANDSTONE_UPRIGHT_SLAB, "sandstone_upright_slab").locale("Sandstone Upright Slab", "砂岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SANDSTONE_UPRIGHT_STAIRS, "sandstone_upright_stairs").locale("Sandstone Upright Stairs", "砂岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CUT_SANDSTONE_UPRIGHT_SLAB, "cut_sandstone_upright_slab").locale("Cut Sandstone Upright Slab", "切制砂岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CUT_SANDSTONE_UPRIGHT_STAIRS, "cut_sandstone_upright_stairs").locale("Cut Sandstone Upright Stairs", "切制砂岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(COBBLESTONE_UPRIGHT_SLAB, "cobblestone_upright_slab").locale("Cobblestone Upright Slab", "圆石台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(COBBLESTONE_UPRIGHT_STAIRS, "cobblestone_upright_stairs").locale("Cobblestone Upright Stairs", "圆石楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BRICK_UPRIGHT_SLAB, "brick_upright_slab").locale("Brick Upright Slab", "红砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BRICK_UPRIGHT_STAIRS, "brick_upright_stairs").locale("Brick Upright Stairs", "红砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(STONE_BRICK_UPRIGHT_SLAB, "stone_brick_upright_slab").locale("Stone Brick Upright Slab", "石砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(STONE_BRICK_UPRIGHT_STAIRS, "stone_brick_upright_stairs").locale("Stone Brick Upright Stairs", "石砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MUD_BRICK_UPRIGHT_SLAB, "mud_brick_upright_slab").locale("Mud Brick Upright Slab", "泥砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MUD_BRICK_UPRIGHT_STAIRS, "mud_brick_upright_stairs").locale("Mud Brick Upright Stairs", "泥砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(NETHER_BRICK_UPRIGHT_SLAB, "nether_brick_upright_slab").locale("Nether Brick Upright Slab", "下界砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(NETHER_BRICK_UPRIGHT_STAIRS, "nether_brick_upright_stairs").locale("Nether Brick Upright Stairs", "下界砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(PRISMARINE_UPRIGHT_SLAB, "prismarine_upright_slab").locale("Prismarine Upright Slab", "海晶石台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(PRISMARINE_UPRIGHT_STAIRS, "prismarine_upright_stairs").locale("Prismarine Upright Stairs", "海晶石楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(PRISMARINE_BRICK_UPRIGHT_SLAB, "prismarine_brick_upright_slab").locale("Prismarine Brick Upright Slab", "海晶石砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(PRISMARINE_BRICK_UPRIGHT_STAIRS, "prismarine_brick_upright_stairs").locale("Prismarine Brick Upright Stairs", "海晶石砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DARK_PRISMARINE_UPRIGHT_SLAB, "dark_prismarine_upright_slab").locale("Dark Prismarine Upright Slab", "暗海晶石台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DARK_PRISMARINE_UPRIGHT_STAIRS, "dark_prismarine_upright_stairs").locale("Dark Prismarine Upright Stairs", "暗海晶石楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(QUARTZ_UPRIGHT_SLAB, "quartz_upright_slab").locale("Quartz Upright Slab", "石英台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(QUARTZ_UPRIGHT_STAIRS, "quartz_upright_stairs").locale("Quartz Upright Stairs", "石英楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(RED_SANDSTONE_UPRIGHT_SLAB, "red_sandstone_upright_slab").locale("Red Sandstone Upright Slab", "红砂岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(RED_SANDSTONE_UPRIGHT_STAIRS, "red_sandstone_upright_stairs").locale("Red Sandstone Upright Stairs", "红砂岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CUT_RED_SANDSTONE_UPRIGHT_SLAB, "cut_red_sandstone_upright_slab").locale("Cut Red Sandstone Upright Slab", "切制红砂岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CUT_RED_SANDSTONE_UPRIGHT_STAIRS, "cut_red_sandstone_upright_stairs").locale("Cut Red Sandstone Upright Stairs", "切制红砂岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(PURPUR_UPRIGHT_SLAB, "purpur_upright_slab").locale("Purpur Upright Slab", "紫珀台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(PURPUR_UPRIGHT_STAIRS, "purpur_upright_stairs").locale("Purpur Upright Stairs", "紫珀楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_GRANITE_UPRIGHT_SLAB, "polished_granite_upright_slab").locale("Polished Granite Upright Slab", "磨制花岗岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_GRANITE_UPRIGHT_STAIRS, "polished_granite_upright_stairs").locale("Polished Granite Upright Stairs", "磨制花岗岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_RED_SANDSTONE_UPRIGHT_SLAB, "smooth_red_sandstone_upright_slab").locale("Smooth Red Sandstone Upright Slab", "平滑红砂岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_RED_SANDSTONE_UPRIGHT_STAIRS, "smooth_red_sandstone_upright_stairs").locale("Smooth Red Sandstone Upright Stairs", "平滑红砂岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MOSSY_STONE_BRICK_UPRIGHT_SLAB, "mossy_stone_brick_upright_slab").locale("Mossy Stone Brick Upright Slab", "苔石砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MOSSY_STONE_BRICK_UPRIGHT_STAIRS, "mossy_stone_brick_upright_stairs").locale("Mossy Stone Brick Upright Stairs", "苔石砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_DIORITE_UPRIGHT_SLAB, "polished_diorite_upright_slab").locale("Polished Diorite Upright Slab", "磨制闪长岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_DIORITE_UPRIGHT_STAIRS, "polished_diorite_upright_stairs").locale("Polished Diorite Upright Stairs", "磨制闪长岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MOSSY_COBBLESTONE_UPRIGHT_SLAB, "mossy_cobblestone_upright_slab").locale("Mossy Cobblestone Upright Slab", "苔石台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(MOSSY_COBBLESTONE_UPRIGHT_STAIRS, "mossy_cobblestone_upright_stairs").locale("Mossy Cobblestone Upright Stairs", "苔石楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(END_STONE_BRICK_UPRIGHT_SLAB, "end_stone_brick_upright_slab").locale("End Stone Brick Upright Slab", "下界石砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(END_STONE_BRICK_UPRIGHT_STAIRS, "end_stone_brick_upright_stairs").locale("End Stone Brick Upright Stairs", "下界石砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_SANDSTONE_UPRIGHT_SLAB, "smooth_sandstone_upright_slab").locale("Smooth Sandstone Upright Slab", "平滑砂岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_SANDSTONE_UPRIGHT_STAIRS, "smooth_sandstone_upright_stairs").locale("Smooth Sandstone Upright Stairs", "平滑砂岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_QUARTZ_UPRIGHT_SLAB, "smooth_quartz_upright_slab").locale("Smooth Quartz Upright Slab", "平滑石英台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_QUARTZ_UPRIGHT_STAIRS, "smooth_quartz_upright_stairs").locale("Smooth Quartz Upright Stairs", "平滑石英楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(GRANITE_UPRIGHT_SLAB, "granite_upright_slab").locale("Granite Upright Slab", "花岗岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(GRANITE_UPRIGHT_STAIRS, "granite_upright_stairs").locale("Granite Upright Stairs", "花岗岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(ANDESITE_UPRIGHT_SLAB, "andesite_upright_slab").locale("Andesite Upright Slab", "安山岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(ANDESITE_UPRIGHT_STAIRS, "andesite_upright_stairs").locale("Andesite Upright Stairs", "安山岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(RED_NETHER_BRICK_UPRIGHT_SLAB, "red_nether_brick_upright_slab").locale("Red Nether Brick Upright Slab", "红色下界砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(RED_NETHER_BRICK_UPRIGHT_STAIRS, "red_nether_brick_upright_stairs").locale("Red Nether Brick Upright Stairs", "红色下界砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_ANDESITE_UPRIGHT_SLAB, "polished_andesite_upright_slab").locale("Polished Andesite Upright Slab", "磨制安山岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_ANDESITE_UPRIGHT_STAIRS, "polished_andesite_upright_stairs").locale("Polished Andesite Upright Stairs", "磨制安山岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DIORITE_UPRIGHT_SLAB, "diorite_upright_slab").locale("Diorite Upright Slab", "闪长岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DIORITE_UPRIGHT_STAIRS, "diorite_upright_stairs").locale("Diorite Upright Stairs", "闪长岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CRIMSON_UPRIGHT_SLAB, "crimson_upright_slab").locale("Crimson Upright Slab", "绯红木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CRIMSON_UPRIGHT_STAIRS, "crimson_upright_stairs").locale("Crimson Upright Stairs", "绯红木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WARPED_UPRIGHT_SLAB, "warped_upright_slab").locale("Warped Upright Slab", "诡异木台阶（竖直）").mineableWithAxe().slab().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WARPED_UPRIGHT_STAIRS, "warped_upright_stairs").locale("Warped Upright Stairs", "诡异木楼梯（竖直）").mineableWithAxe().stairs().wooden().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BLACKSTONE_UPRIGHT_SLAB, "blackstone_upright_slab").locale("Blackstone Upright Slab", "黑石台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(BLACKSTONE_UPRIGHT_STAIRS, "blackstone_upright_stairs").locale("Blackstone Upright Stairs", "黑石楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_BLACKSTONE_BRICK_UPRIGHT_SLAB, "polished_blackstone_brick_upright_slab").locale("Polished Blackstone Brick Upright Slab", "磨制黑石砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_BLACKSTONE_BRICK_UPRIGHT_STAIRS, "polished_blackstone_brick_upright_stairs").locale("Polished Blackstone Brick Upright Stairs", "磨制黑石砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_BLACKSTONE_UPRIGHT_SLAB, "polished_blackstone_upright_slab").locale("Polished Blackstone Upright Slab", "磨制黑石台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_BLACKSTONE_UPRIGHT_STAIRS, "polished_blackstone_upright_stairs").locale("Polished Blackstone Upright Stairs", "磨制黑石楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(OXIDIZED_CUT_COPPER_UPRIGHT_SLAB, "oxidized_cut_copper_upright_slab").locale("Oxidized Cut Copper Upright Slab", "氧化的切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS, "oxidized_cut_copper_upright_stairs").locale("Oxidized Cut Copper Upright Stairs", "氧化的切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WEATHERED_CUT_COPPER_UPRIGHT_SLAB, "weathered_cut_copper_upright_slab").locale("Weathered Cut Copper Upright Slab", "锈蚀的切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WEATHERED_CUT_COPPER_UPRIGHT_STAIRS, "weathered_cut_copper_upright_stairs").locale("Weathered Cut Copper Upright Stairs", "锈蚀的切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(EXPOSED_CUT_COPPER_UPRIGHT_SLAB, "exposed_cut_copper_upright_slab").locale("Exposed Cut Copper Upright Slab", "斑驳的切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(EXPOSED_CUT_COPPER_UPRIGHT_STAIRS, "exposed_cut_copper_upright_stairs").locale("Exposed Cut Copper Upright Stairs", "斑驳的切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CUT_COPPER_UPRIGHT_SLAB, "cut_copper_upright_slab").locale("Cut Copper Upright Slab", "切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(CUT_COPPER_UPRIGHT_STAIRS, "cut_copper_upright_stairs").locale("Cut Copper Upright Stairs", "切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(false).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_SLAB, "waxed_oxidized_cut_copper_upright_slab").locale("Waxed Oxidized Cut Copper Upright Slab", "涂蜡的氧化切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS, "waxed_oxidized_cut_copper_upright_stairs").locale("Waxed Oxidized Cut Copper Upright Stairs", "涂蜡的氧化切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_WEATHERED_CUT_COPPER_UPRIGHT_SLAB, "waxed_weathered_cut_copper_upright_slab").locale("Waxed Weathered Cut Copper Upright Slab", "涂蜡的锈蚀切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_WEATHERED_CUT_COPPER_UPRIGHT_STAIRS, "waxed_weathered_cut_copper_upright_stairs").locale("Waxed Weathered Cut Copper Upright Stairs", "涂蜡的锈蚀切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_EXPOSED_CUT_COPPER_UPRIGHT_SLAB, "waxed_exposed_cut_copper_upright_slab").locale("Waxed Exposed Cut Copper Upright Slab", "涂蜡的斑驳切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_EXPOSED_CUT_COPPER_UPRIGHT_STAIRS, "waxed_exposed_cut_copper_upright_stairs").locale("Waxed Exposed Cut Copper Upright Stairs", "涂蜡的斑驳切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_CUT_COPPER_UPRIGHT_SLAB, "waxed_cut_copper_upright_slab").locale("Waxed Cut Copper Upright Slab", "涂蜡的切制铜台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).slab().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(WAXED_CUT_COPPER_UPRIGHT_STAIRS, "waxed_cut_copper_upright_stairs").locale("Waxed Cut Copper Upright Stairs", "涂蜡的切制铜楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.STONE_TOOLS).stairs().copper(true).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(COBBLED_DEEPSLATE_UPRIGHT_SLAB, "cobbled_deepslate_upright_slab").locale("Cobbled Deepslate Upright Slab", "深板岩圆石台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(COBBLED_DEEPSLATE_UPRIGHT_STAIRS, "cobbled_deepslate_upright_stairs").locale("Cobbled Deepslate Upright Stairs", "深板岩圆石楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_DEEPSLATE_UPRIGHT_SLAB, "polished_deepslate_upright_slab").locale("Polished Deepslate Upright Slab", "磨制深板岩台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(POLISHED_DEEPSLATE_UPRIGHT_STAIRS, "polished_deepslate_upright_stairs").locale("Polished Deepslate Upright Stairs", "磨制深板岩楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DEEPSLATE_TILE_UPRIGHT_SLAB, "deepslate_tile_upright_slab").locale("Deepslate Tile Upright Slab", "深板岩瓦台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DEEPSLATE_TILE_UPRIGHT_STAIRS, "deepslate_tile_upright_stairs").locale("Deepslate Tile Upright Stairs", "深板岩瓦楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DEEPSLATE_BRICK_UPRIGHT_SLAB, "deepslate_brick_upright_slab").locale("Deepslate Brick Upright Slab", "深板岩砖台阶（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(DEEPSLATE_BRICK_UPRIGHT_STAIRS, "deepslate_brick_upright_stairs").locale("Deepslate Brick Upright Stairs", "深板岩砖楼梯（竖直）").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(GLASS_UPRIGHT_SLAB, "glass_upright_slab").locale("Glass Upright Slab", "玻璃台阶（竖直）").slab().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(GLASS_UPRIGHT_STAIRS, "glass_upright_stairs").locale("Glass Upright Stairs", "玻璃楼梯（竖直）").stairs().creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());

            blockLib.add(new BlockLib(GLASS_SLAB, "glass_slab").locale("Glass Slab", "玻璃台阶").slab().translucent().texture(Blocks.GLASS).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS));
            blockLib.add(new BlockLib(GLASS_STAIRS, "glass_stairs").locale("Glass Stairs", "玻璃楼梯").stairs().translucent().texture(Blocks.GLASS).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS).complexCube());
            blockLib.add(new BlockLib(SMOOTH_STONE_STAIRS, "smooth_stone_stairs").locale("Smooth Stone Stairs", "平滑石楼梯").stairs().mineableWithPickaxe(BlockLib.LEVEL.WOOD).texture(Blocks.SMOOTH_STONE).creativeTab(Utils.ONECORE_CREATIVE_TAB_USS));
            blockLib.add(new BlockLib(SMOOTH_STONE_TRANSVERSE_STAIRS, "smooth_stone_transverse_stairs").locale("Smooth Stone Transverse Stairs", "横纹平滑石楼梯").mineableWithPickaxe(BlockLib.LEVEL.WOOD).stairs().texture(Blocks.SMOOTH_STONE, Blocks.SMOOTH_STONE_SLAB, "_side").creativeTab(Utils.ONECORE_CREATIVE_TAB_USS));
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
