package cooling.items;

import net.minecraft.item.Item;
import cooling.utils.Config;

public class ModItems {

    public static Item flesh;

    public static void init() {
        flesh = new FoodItem(Config.FeshID, 4, 0.8F)
                .setUnlocalizedName("flesh");
    }
}