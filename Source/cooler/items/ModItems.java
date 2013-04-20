package cooler.items;

import net.minecraft.item.Item;
import cooler.utils.Config;
import cooler.utils.Archive;

public class ModItems {

    public static Item flesh, cooler;

    public static void init() {

        // Loads Flesh
        flesh = new FoodItem(Config.fleshID - 256, 8, 2F).setUnlocalizedName(Archive.flesh);

        // Loads the Cooler. Sets the Cooler's container to it self.
        cooler = new ItemCooler(Config.coolerID - 256).setUnlocalizedName(Archive.cooler)
                .setContainerItem(cooler);
    }
}