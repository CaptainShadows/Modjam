package cooling.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import cooling.utils.Config;

public class ModItems {

    public static Item flesh;

    public static void init() {
        flesh = new ItemFood(Config.FeshID, 4, 0.5F, true);
    }
}