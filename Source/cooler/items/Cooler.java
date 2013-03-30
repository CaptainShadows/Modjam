package cooler.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Cooler extends Item {

    public Cooler(int par1) {
        super(par1);
        this.canRepair(false);
        this.maxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setMaxDamage(8);
        // TODO Auto-generated constructor stub
    }

}
