package cooler.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cooler.utils.Config;
import cooler.utils.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCooler extends Item {

    public ItemCooler(int par1) {
        super(par1);
        maxStackSize = 1;
        setNoRepair();
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxDamage(Config.coolerDamage - 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ItemStack getContainerItemStack(
            ItemStack itemStack) {

        itemStack
                .setItemDamage(itemStack.getItemDamage() + 1);

        return itemStack;
    }

    /**
     * If this returns true, after a recipe involving this item is crafted the
     * container item will be added to the player's inventory instead of
     * remaining in the crafting grid.
     */
    public boolean doesContainerItemLeaveCraftingGrid(
            ItemStack par1ItemStack) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister) {

        iconIndex = iconRegister
                .registerIcon(Registry.texture
                        + Registry.cooler);
    }
}