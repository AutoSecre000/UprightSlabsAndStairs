package cn.autosec.onecore.uss.definition.lib;

import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;

public class StoneCutterRecipeLib extends ModLib {
    public Item input;
    public Item output;
    public int outputNum;
    public RecipeCategory category;

    public StoneCutterRecipeLib(Item input, RecipeCategory recipeCategory, Item output, int outputNum) {
        this.input = input;
        this.category = recipeCategory;
        this.output = output;
        this.outputNum = outputNum;
    }
}
