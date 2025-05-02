package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.definition.lib.StoneCutterRecipeLib;
import cn.autosec.onecore.uss.registry.ModRecipes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerators extends FabricRecipeProvider {
    public RecipeGenerators(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ShapedRecipeJsonBuilder> craftingRecipeBuilders = ModRecipes.getCraftingRecipeBuilders();
        if (craftingRecipeBuilders != null) {
            for (ShapedRecipeJsonBuilder builder : craftingRecipeBuilders) {
                builder.offerTo(recipeExporter);
            }
        }

        List<StoneCutterRecipeLib> stoneCutterRecipeLibs = ModRecipes.getStoneCutterRecipes();
        if (stoneCutterRecipeLibs != null) {
            for (StoneCutterRecipeLib stoneCutterRecipeLib : stoneCutterRecipeLibs) {
                FabricRecipeProvider.offerStonecuttingRecipe(recipeExporter, stoneCutterRecipeLib.category,
                        stoneCutterRecipeLib.output, stoneCutterRecipeLib.input, stoneCutterRecipeLib.outputNum);
            }
        }
    }
}
