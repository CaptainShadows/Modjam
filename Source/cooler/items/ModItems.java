package cooler.items;

import net.minecraft.item.Item;
import cooler.utils.Config;
import cooler.utils.Registry;

public class ModItems {

    public static Item flesh, cooler;

    public static void init() {

        // Loads Flesh
        flesh = new FoodItem(Config.fleshID - 256, 4, 0.8F)
                .setUnlocalizedName(Registry.flesh);

        // Loads the Cooler
        cooler = new ItemCooler(Config.coolerID - 256)
                .setUnlocalizedName(Registry.cooler);
        // Sets the Cooler's container to it self.
        cooler.setContainerItem(cooler);
    }
}