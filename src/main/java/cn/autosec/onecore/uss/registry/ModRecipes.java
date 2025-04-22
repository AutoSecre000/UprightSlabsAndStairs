package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.FurnaceRecipeLib;
import cn.autosec.onecore.uss.definition.StoneCutterRecipeLib;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
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

    private static void AddStairsInputRecipe(Item input, Item output, String trigger) {
        craftingRecipes.add(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 4)
                .pattern("###").pattern("## ").pattern("#  ")
                .define('#', input).group(OneCore.MODID)
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input).build())));
    }

    public static List<RecipeBuilder> getCraftingRecipeBuilders() {
        if (craftingRecipes.isEmpty()) {
            AddSimple3VInputRecipe(Items.STONE, ModBlocks.STONE_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddSimple3VInputRecipe(Items.OAK_PLANKS, ModBlocks.OAK_UPRIGHT_SLAB.getItem(), "has_upright_slab");
            AddStairsInputRecipe(Items.STONE, ModBlocks.STONE_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
            AddStairsInputRecipe(Items.OAK_PLANKS, ModBlocks.OAK_UPRIGHT_STAIRS.getItem(), "has_upright_stairs");
        }
        return craftingRecipes;
    }

    private static final List<StoneCutterRecipeLib> stoneCutterRecipes = new ArrayList<>();

    public static List<StoneCutterRecipeLib> getStoneCutterRecipes() {
        if (stoneCutterRecipes.isEmpty()) {
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE, RecipeCategory.MISC, ModBlocks.STONE_UPRIGHT_SLAB.getItem(), 2));
            stoneCutterRecipes.add(new StoneCutterRecipeLib(Items.STONE, RecipeCategory.MISC, ModBlocks.STONE_UPRIGHT_STAIRS.getItem(), 1));
        }
        return stoneCutterRecipes;
    }
}
