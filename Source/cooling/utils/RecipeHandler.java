package cooling.utils;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {

    public static void add() {
        Handler.logName("is Loading it's Recipies");
        addSRecipe();
    }

    private static void addSRecipe(ItemStack output,
            Object... params) {
        GameRegistry.addShapelessRecipe(output, params);
    }
}