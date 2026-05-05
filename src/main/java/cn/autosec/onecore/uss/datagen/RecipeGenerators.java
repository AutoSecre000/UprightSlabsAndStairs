package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.definition.lib.CraftingRecipeLib;
import cn.autosec.onecore.uss.definition.lib.StoneCutterRecipeLib;
import cn.autosec.onecore.uss.registry.ModRecipes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;

import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerators extends RecipeProvider {
    public RecipeGenerators(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        List<CraftingRecipeLib> craftingRecipeBuilders = ModRecipes.getCraftingRecipeBuilders();
        if (craftingRecipeBuilders != null) {
            for (CraftingRecipeLib lib : craftingRecipeBuilders) {
                if (lib.isConversionRecipe) {
                    lib.recipeBuilder.save(consumer, getConversionRecipeName(lib.conversionInput, lib.converter));
                } else {
                    lib.recipeBuilder.save(consumer);
                }
            }
        }

        List<StoneCutterRecipeLib> stoneCutterRecipeLibs = ModRecipes.getStoneCutterRecipes();
        if (stoneCutterRecipeLibs != null) {
            for (StoneCutterRecipeLib stoneCutterRecipeLib : stoneCutterRecipeLibs) {
                stonecutterResultFromBase(consumer, stoneCutterRecipeLib.category, stoneCutterRecipeLib.output,
                        stoneCutterRecipeLib.input, stoneCutterRecipeLib.outputNum);
            }
        }
    }
}
