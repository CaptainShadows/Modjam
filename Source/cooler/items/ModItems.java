package cooler.items;

import net.minecraft.item.Item;
import cooler.utils.Config;
import cooler.utils.Registry;

public class ModItems {

    public static Item flesh, cooler;

    public static void init() {
        flesh = new FoodItem(Config.fleshID - 256, 4, 0.8F)
                .setUnlocalizedName(Registry.flesh);
        
        cooler = new ItemCooler(Config.coolerID - 256)
        .setUnlocalizedName(Registry.cooler);
        cooler.setContainerItem(cooler);
    }
}