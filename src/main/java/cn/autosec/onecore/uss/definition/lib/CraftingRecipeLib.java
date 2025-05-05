package cn.autosec.onecore.uss.definition.lib;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;

public class CraftingRecipeLib extends ModLib {
    public static CraftingRecipeLib of(Item input, Item converter, Item output,
                                       RecipeCategory recipeCategory) {
        CraftingRecipeLib craftingRecipeLib =
                new CraftingRecipeLib(ShapelessRecipeJsonBuilder.create(recipeCategory, output, 1)
                .input(input).input(converter)
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input)));
        craftingRecipeLib.converter = converter;
        craftingRecipeLib.conversionInput = input;
        return craftingRecipeLib;
    }

    public static CraftingRecipeLib of(CraftingRecipeJsonBuilder recipeBuilder) {
        return new CraftingRecipeLib(recipeBuilder);
    }

    public CraftingRecipeJsonBuilder recipeBuilder;
    public Item conversionInput;
    public Item converter;
    public boolean isConversionRecipe = false;

    private CraftingRecipeLib(CraftingRecipeJsonBuilder recipeBuilder) {
        this.recipeBuilder = recipeBuilder;
    }

    public CraftingRecipeLib conversionRecipe() {
        this.isConversionRecipe = true;
        return this;
    }
}
