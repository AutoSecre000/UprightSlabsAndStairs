package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.lib.StoneCutterRecipeLib;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {
    private static final List<RecipeBuilder> craftingRecipes = new ArrayList<>();

    private static void AddSimple3VInputRecipe(Item input, Item output, String trigger) {
        craftingRecipes.add(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 6)
                .pattern("#").pattern("#").pattern("#")
                .define('#', input).group(OneCore.MODID)
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input).build())));
    }

    private static void AddSimple3HInputRecipe(Item input, Item output, String trigger) {
        craftingRecipes.add(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 6)
                .pattern("###")
                .define('#', input).group(OneCore.MODID)
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input).build())));
    }

    private static void AddUprightStairsInputRecipe(Item input, Item output, String trigger) {
        craftingRecipes.add(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 4)
                .pattern("###").pattern("## ").pattern("#  ")
                .define('#', input).group(OneCore.MODID)
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input).build())));
    }

    private static void AddStairsInputRecipe(Item input, Item output, String trigger) {
        craftingRecipes.add(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 4)
                .pattern("#  ").pattern("## ").pattern("###")
                .define('#', input).group(OneCore.MODID)
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input).build())));
    }

    private static void AddTransverseStairsInputRecipe(Item input1, Item input2, Item output, String trigger) {
        craftingRecipes.add(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 4)
                .pattern("@  ").pattern("@@@").pattern("###")
                .define('#', input1).group(OneCore.MODID)
                .define('@', input2).group(OneCore.MODID)
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input1).build()))
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input2).build())));
    }

    public static List<RecipeBuilder> getCraftingRecipeBuilders() {
        if (craftingRecipes.isEmpty()) {
            AddSimple3VInputRecipe(Items.PRISMARINE, ModBlocks.PRISMARINE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.PRISMARINE, ModBlocks.PRISMARINE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.PRISMARINE_BRICKS, ModBlocks.PRISMARINE_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.PRISMARINE_BRICKS, ModBlocks.PRISMARINE_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.DARK_PRISMARINE, ModBlocks.DARK_PRISMARINE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.DARK_PRISMARINE, ModBlocks.DARK_PRISMARINE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.OAK_PLANKS, ModBlocks.OAK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.OAK_PLANKS, ModBlocks.OAK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.SPRUCE_PLANKS, ModBlocks.SPRUCE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.SPRUCE_PLANKS, ModBlocks.SPRUCE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.BIRCH_PLANKS, ModBlocks.BIRCH_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.BIRCH_PLANKS, ModBlocks.BIRCH_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.JUNGLE_PLANKS, ModBlocks.JUNGLE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.JUNGLE_PLANKS, ModBlocks.JUNGLE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.ACACIA_PLANKS, ModBlocks.ACACIA_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.ACACIA_PLANKS, ModBlocks.ACACIA_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.CHERRY_PLANKS, ModBlocks.CHERRY_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.CHERRY_PLANKS, ModBlocks.CHERRY_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.MANGROVE_PLANKS, ModBlocks.MANGROVE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.MANGROVE_PLANKS, ModBlocks.MANGROVE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.BAMBOO_BLOCK, ModBlocks.BAMBOO_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.BAMBOO_BLOCK, ModBlocks.BAMBOO_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.BAMBOO_MOSAIC, ModBlocks.BAMBOO_MOSAIC_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.BAMBOO_MOSAIC, ModBlocks.BAMBOO_MOSAIC_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.STONE, ModBlocks.STONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.STONE, ModBlocks.STONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.SMOOTH_STONE, ModBlocks.SMOOTH_STONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.SMOOTH_STONE, ModBlocks.SMOOTH_STONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.SANDSTONE, ModBlocks.SANDSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.SANDSTONE, ModBlocks.SANDSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.CUT_SANDSTONE, ModBlocks.CUT_SANDSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.CUT_SANDSTONE, ModBlocks.CUT_SANDSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.COBBLESTONE, ModBlocks.COBBLESTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.COBBLESTONE, ModBlocks.COBBLESTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.BRICKS, ModBlocks.BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.BRICKS, ModBlocks.BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.STONE_BRICKS, ModBlocks.STONE_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.STONE_BRICKS, ModBlocks.STONE_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.MUD_BRICKS, ModBlocks.MUD_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.MUD_BRICKS, ModBlocks.MUD_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.NETHER_BRICKS, ModBlocks.NETHER_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.NETHER_BRICKS, ModBlocks.NETHER_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.QUARTZ_BLOCK, ModBlocks.QUARTZ_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.QUARTZ_BLOCK, ModBlocks.QUARTZ_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.RED_SANDSTONE, ModBlocks.RED_SANDSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.RED_SANDSTONE, ModBlocks.RED_SANDSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.CUT_RED_SANDSTONE, ModBlocks.CUT_RED_SANDSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.CUT_RED_SANDSTONE, ModBlocks.CUT_RED_SANDSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.PURPUR_BLOCK, ModBlocks.PURPUR_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.PURPUR_BLOCK, ModBlocks.PURPUR_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.POLISHED_GRANITE, ModBlocks.POLISHED_GRANITE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.POLISHED_GRANITE, ModBlocks.POLISHED_GRANITE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.SMOOTH_RED_SANDSTONE, ModBlocks.SMOOTH_RED_SANDSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.SMOOTH_RED_SANDSTONE, ModBlocks.SMOOTH_RED_SANDSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.MOSSY_STONE_BRICKS, ModBlocks.MOSSY_STONE_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.MOSSY_STONE_BRICKS, ModBlocks.MOSSY_STONE_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.POLISHED_DIORITE, ModBlocks.POLISHED_DIORITE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.POLISHED_DIORITE, ModBlocks.POLISHED_DIORITE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.MOSSY_COBBLESTONE, ModBlocks.MOSSY_COBBLESTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.MOSSY_COBBLESTONE, ModBlocks.MOSSY_COBBLESTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.END_STONE_BRICKS, ModBlocks.END_STONE_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.END_STONE_BRICKS, ModBlocks.END_STONE_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.SMOOTH_SANDSTONE, ModBlocks.SMOOTH_SANDSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.SMOOTH_SANDSTONE, ModBlocks.SMOOTH_SANDSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.SMOOTH_QUARTZ, ModBlocks.SMOOTH_QUARTZ_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.SMOOTH_QUARTZ, ModBlocks.SMOOTH_QUARTZ_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.GRANITE, ModBlocks.GRANITE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.GRANITE, ModBlocks.GRANITE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.ANDESITE, ModBlocks.ANDESITE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.ANDESITE, ModBlocks.ANDESITE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.RED_NETHER_BRICKS, ModBlocks.RED_NETHER_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.RED_NETHER_BRICKS, ModBlocks.RED_NETHER_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.POLISHED_ANDESITE, ModBlocks.POLISHED_ANDESITE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.POLISHED_ANDESITE, ModBlocks.POLISHED_ANDESITE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.DIORITE, ModBlocks.DIORITE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.DIORITE, ModBlocks.DIORITE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.CRIMSON_PLANKS, ModBlocks.CRIMSON_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.CRIMSON_PLANKS, ModBlocks.CRIMSON_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.WARPED_PLANKS, ModBlocks.WARPED_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.WARPED_PLANKS, ModBlocks.WARPED_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.BLACKSTONE, ModBlocks.BLACKSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.BLACKSTONE, ModBlocks.BLACKSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.POLISHED_BLACKSTONE_BRICKS, ModBlocks.POLISHED_BLACKSTONE_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.POLISHED_BLACKSTONE_BRICKS, ModBlocks.POLISHED_BLACKSTONE_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.POLISHED_BLACKSTONE, ModBlocks.POLISHED_BLACKSTONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.POLISHED_BLACKSTONE, ModBlocks.POLISHED_BLACKSTONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.OXIDIZED_CUT_COPPER, ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.OXIDIZED_CUT_COPPER, ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.WEATHERED_CUT_COPPER, ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.WEATHERED_CUT_COPPER, ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.EXPOSED_CUT_COPPER, ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.EXPOSED_CUT_COPPER, ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.CUT_COPPER, ModBlocks.CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.CUT_COPPER, ModBlocks.CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.WAXED_OXIDIZED_CUT_COPPER, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.WAXED_OXIDIZED_CUT_COPPER, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.WAXED_WEATHERED_CUT_COPPER, ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.WAXED_WEATHERED_CUT_COPPER, ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.WAXED_EXPOSED_CUT_COPPER, ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.WAXED_EXPOSED_CUT_COPPER, ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.WAXED_CUT_COPPER, ModBlocks.WAXED_CUT_COPPER_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.WAXED_CUT_COPPER, ModBlocks.WAXED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.COBBLED_DEEPSLATE, ModBlocks.COBBLED_DEEPSLATE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.COBBLED_DEEPSLATE, ModBlocks.COBBLED_DEEPSLATE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.POLISHED_DEEPSLATE, ModBlocks.POLISHED_DEEPSLATE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.POLISHED_DEEPSLATE, ModBlocks.POLISHED_DEEPSLATE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.DEEPSLATE_TILES, ModBlocks.DEEPSLATE_TILE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.DEEPSLATE_TILES, ModBlocks.DEEPSLATE_TILE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.DEEPSLATE_BRICKS, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.DEEPSLATE_BRICKS, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3VInputRecipe(Items.GLASS, ModBlocks.GLASS_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddUprightStairsInputRecipe(Items.GLASS, ModBlocks.GLASS_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddSimple3HInputRecipe(Items.GLASS, ModBlocks.GLASS_SLAB.getItem(), "has_glass");
            AddStairsInputRecipe(Items.GLASS, ModBlocks.GLASS_STAIRS.getItem(), "has_glass");
            AddStairsInputRecipe(Items.SMOOTH_STONE, ModBlocks.SMOOTH_STONE_STAIRS.getItem(), "has_smooth_stone");
            AddTransverseStairsInputRecipe(Items.SMOOTH_STONE, Items.SMOOTH_STONE_SLAB, ModBlocks.SMOOTH_STONE_TRANSVERSE_STAIRS.getItem(), "has_smooth_stone");
        }
        return craftingRecipes;
    }

    private static final List<StoneCutterRecipeLib> stoneCutterRecipes = new ArrayList<>();

    public static List<StoneCutterRecipeLib> getStoneCutterRecipes() {
        if (stoneCutterRecipes.isEmpty()) {
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.PRISMARINE, RecipeCategory.MISC, ModBlocks.PRISMARINE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.PRISMARINE, RecipeCategory.MISC, ModBlocks.PRISMARINE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.PRISMARINE_BRICKS, RecipeCategory.MISC, ModBlocks.PRISMARINE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.PRISMARINE_BRICKS, RecipeCategory.MISC, ModBlocks.PRISMARINE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DARK_PRISMARINE, RecipeCategory.MISC, ModBlocks.DARK_PRISMARINE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DARK_PRISMARINE, RecipeCategory.MISC, ModBlocks.DARK_PRISMARINE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE, RecipeCategory.MISC, ModBlocks.STONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE, RecipeCategory.MISC, ModBlocks.STONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_STONE, RecipeCategory.MISC, ModBlocks.SMOOTH_STONE_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_STONE, RecipeCategory.MISC, ModBlocks.SMOOTH_STONE_TRANSVERSE_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_STONE, RecipeCategory.MISC, ModBlocks.SMOOTH_STONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_STONE, RecipeCategory.MISC, ModBlocks.SMOOTH_STONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(ModBlocks.SMOOTH_STONE_STAIRS.getItem(), RecipeCategory.MISC, ModBlocks.SMOOTH_STONE_TRANSVERSE_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SANDSTONE, RecipeCategory.MISC, ModBlocks.SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SANDSTONE, RecipeCategory.MISC, ModBlocks.SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.CUT_SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.CUT_SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLESTONE, RecipeCategory.MISC, ModBlocks.COBBLESTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLESTONE, RecipeCategory.MISC, ModBlocks.COBBLESTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BRICKS, RecipeCategory.MISC, ModBlocks.BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BRICKS, RecipeCategory.MISC, ModBlocks.BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE_BRICKS, RecipeCategory.MISC, ModBlocks.STONE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE_BRICKS, RecipeCategory.MISC, ModBlocks.STONE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE, RecipeCategory.MISC, ModBlocks.STONE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE, RecipeCategory.MISC, ModBlocks.STONE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.MUD_BRICKS, RecipeCategory.MISC, ModBlocks.MUD_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.MUD_BRICKS, RecipeCategory.MISC, ModBlocks.MUD_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.NETHER_BRICKS, RecipeCategory.MISC, ModBlocks.NETHER_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.NETHER_BRICKS, RecipeCategory.MISC, ModBlocks.NETHER_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.QUARTZ_BLOCK, RecipeCategory.MISC, ModBlocks.QUARTZ_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.QUARTZ_BLOCK, RecipeCategory.MISC, ModBlocks.QUARTZ_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.RED_SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.RED_SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.CUT_RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_RED_SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.CUT_RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_RED_SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_RED_SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.CUT_RED_SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.PURPUR_BLOCK, RecipeCategory.MISC, ModBlocks.PURPUR_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.PURPUR_BLOCK, RecipeCategory.MISC, ModBlocks.PURPUR_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_GRANITE, RecipeCategory.MISC, ModBlocks.POLISHED_GRANITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_GRANITE, RecipeCategory.MISC, ModBlocks.POLISHED_GRANITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.GRANITE, RecipeCategory.MISC, ModBlocks.POLISHED_GRANITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.GRANITE, RecipeCategory.MISC, ModBlocks.POLISHED_GRANITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.SMOOTH_RED_SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_RED_SANDSTONE, RecipeCategory.MISC, ModBlocks.SMOOTH_RED_SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.MOSSY_STONE_BRICKS, RecipeCategory.MISC, ModBlocks.MOSSY_STONE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.MOSSY_STONE_BRICKS, RecipeCategory.MISC, ModBlocks.MOSSY_STONE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DIORITE, RecipeCategory.MISC, ModBlocks.POLISHED_DIORITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DIORITE, RecipeCategory.MISC, ModBlocks.POLISHED_DIORITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DIORITE, RecipeCategory.MISC, ModBlocks.POLISHED_DIORITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DIORITE, RecipeCategory.MISC, ModBlocks.POLISHED_DIORITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.MOSSY_COBBLESTONE, RecipeCategory.MISC, ModBlocks.MOSSY_COBBLESTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.MOSSY_COBBLESTONE, RecipeCategory.MISC, ModBlocks.MOSSY_COBBLESTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.END_STONE_BRICKS, RecipeCategory.MISC, ModBlocks.END_STONE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.END_STONE_BRICKS, RecipeCategory.MISC, ModBlocks.END_STONE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_SANDSTONE, RecipeCategory.MISC, ModBlocks.SMOOTH_SANDSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_SANDSTONE, RecipeCategory.MISC, ModBlocks.SMOOTH_SANDSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_QUARTZ, RecipeCategory.MISC, ModBlocks.SMOOTH_QUARTZ_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.SMOOTH_QUARTZ, RecipeCategory.MISC, ModBlocks.SMOOTH_QUARTZ_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.QUARTZ_BLOCK, RecipeCategory.MISC, ModBlocks.SMOOTH_QUARTZ_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.QUARTZ_BLOCK, RecipeCategory.MISC, ModBlocks.SMOOTH_QUARTZ_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.GRANITE, RecipeCategory.MISC, ModBlocks.GRANITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.GRANITE, RecipeCategory.MISC, ModBlocks.GRANITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.ANDESITE, RecipeCategory.MISC, ModBlocks.ANDESITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.ANDESITE, RecipeCategory.MISC, ModBlocks.ANDESITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.RED_NETHER_BRICKS, RecipeCategory.MISC, ModBlocks.RED_NETHER_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.RED_NETHER_BRICKS, RecipeCategory.MISC, ModBlocks.RED_NETHER_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_ANDESITE, RecipeCategory.MISC, ModBlocks.POLISHED_ANDESITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_ANDESITE, RecipeCategory.MISC, ModBlocks.POLISHED_ANDESITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.ANDESITE, RecipeCategory.MISC, ModBlocks.POLISHED_ANDESITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.ANDESITE, RecipeCategory.MISC, ModBlocks.POLISHED_ANDESITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DIORITE, RecipeCategory.MISC, ModBlocks.DIORITE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DIORITE, RecipeCategory.MISC, ModBlocks.DIORITE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BLACKSTONE, RecipeCategory.MISC, ModBlocks.BLACKSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BLACKSTONE, RecipeCategory.MISC, ModBlocks.BLACKSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_BLACKSTONE_BRICKS, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_BLACKSTONE_BRICKS, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_BLACKSTONE, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_BLACKSTONE, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BLACKSTONE, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BLACKSTONE, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BLACKSTONE, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.BLACKSTONE, RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.OXIDIZED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.OXIDIZED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WEATHERED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WEATHERED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.EXPOSED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.EXPOSED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.CUT_COPPER, RecipeCategory.MISC, ModBlocks.CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.CUT_COPPER, RecipeCategory.MISC, ModBlocks.CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.OXIDIZED_COPPER, RecipeCategory.MISC, ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.OXIDIZED_COPPER, RecipeCategory.MISC, ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WEATHERED_COPPER, RecipeCategory.MISC, ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WEATHERED_COPPER, RecipeCategory.MISC, ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.EXPOSED_COPPER, RecipeCategory.MISC, ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.EXPOSED_COPPER, RecipeCategory.MISC, ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COPPER_BLOCK, RecipeCategory.MISC, ModBlocks.CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COPPER_BLOCK, RecipeCategory.MISC, ModBlocks.CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_OXIDIZED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_OXIDIZED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_WEATHERED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_WEATHERED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_EXPOSED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_EXPOSED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_CUT_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_OXIDIZED_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_OXIDIZED_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_WEATHERED_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_WEATHERED_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_EXPOSED_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_EXPOSED_COPPER, RecipeCategory.MISC, ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_COPPER_BLOCK, RecipeCategory.MISC, ModBlocks.WAXED_CUT_COPPER_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.WAXED_COPPER_BLOCK, RecipeCategory.MISC, ModBlocks.WAXED_CUT_COPPER_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.COBBLED_DEEPSLATE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.COBBLED_DEEPSLATE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.POLISHED_DEEPSLATE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.POLISHED_DEEPSLATE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DEEPSLATE_TILES, RecipeCategory.MISC, ModBlocks.DEEPSLATE_TILE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DEEPSLATE_TILES, RecipeCategory.MISC, ModBlocks.DEEPSLATE_TILE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DEEPSLATE_BRICKS, RecipeCategory.MISC, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.DEEPSLATE_BRICKS, RecipeCategory.MISC, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.POLISHED_DEEPSLATE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.POLISHED_DEEPSLATE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_TILE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_TILE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.COBBLED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_TILE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_TILE_UPRIGHT_STAIRS.getItem(), 1));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.POLISHED_DEEPSLATE, RecipeCategory.MISC, ModBlocks.DEEPSLATE_BRICK_UPRIGHT_STAIRS.getItem(), 1));
        }
        return stoneCutterRecipes;
    }
}
