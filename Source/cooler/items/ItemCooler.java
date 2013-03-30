package cooler.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cooler.utils.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCooler extends Item {

    public ItemCooler(int par1) {
        super(par1);
        this.canRepair(false);
        this.maxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setMaxDamage(8);
        // TODO Auto-generated constructor stub
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister) {

        iconIndex = iconRegister
                .registerIcon(Registry.texture
                        + Registry.cooler);
    }
}