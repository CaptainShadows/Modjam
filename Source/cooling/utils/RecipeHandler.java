package cooling.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cooling.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {

    public static void add() {
        Handler.logName("is Loading it's Recipies");
        addSRecipe(new ItemStack(ModItems.flesh),
                new Object[] { Item.rottenFlesh });
    }

    private static void addSRecipe(ItemStack output,
            Object... params) {
        GameRegistry.addShapelessRecipe(output, params);
    }
}