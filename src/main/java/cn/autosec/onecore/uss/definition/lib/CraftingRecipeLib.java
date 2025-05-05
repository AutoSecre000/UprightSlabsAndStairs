package cn.autosec.onecore.uss.definition.lib;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;

public class CraftingRecipeLib extends ModLib {
    public static CraftingRecipeLib of(Item input, Item converter, Item output,
                                       RecipeCategory recipeCategory, String trigger) {
        CraftingRecipeLib craftingRecipeLib =
                new CraftingRecipeLib(ShapelessRecipeBuilder.shapeless(recipeCategory, output, 1)
                .requires(input).requires(converter)
                .unlockedBy(trigger, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(input).build())));
        craftingRecipeLib.converter = converter;
        craftingRecipeLib.conversionInput = input;
        return craftingRecipeLib;
    }

    public static CraftingRecipeLib of(RecipeBuilder recipeBuilder) {
        return new CraftingRecipeLib(recipeBuilder);
    }

    public RecipeBuilder recipeBuilder;
    public Item conversionInput;
    public Item converter;
    public boolean isConversionRecipe = false;

    private CraftingRecipeLib(RecipeBuilder recipeBuilder) {
        this.recipeBuilder = recipeBuilder;
    }

    public CraftingRecipeLib conversionRecipe() {
        this.isConversionRecipe = true;
        return this;
    }
}
