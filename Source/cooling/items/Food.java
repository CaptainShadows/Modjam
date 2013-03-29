package cooling.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class Food extends ItemFood{

    public Food(int par1, int par2, float par3, boolean par4)
    {
        super(par1, par2, par3, par4);
        this.setCreativeTab(CreativeTabs.tabFood);
    }
    
}
