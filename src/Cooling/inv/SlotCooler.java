package Cooling.inv;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCooler extends Slot {

    public SlotCooler(IInventory inventory, int x, int y,
            int z) {

        super(inventory, x, y, z);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {

        return false;
    }

}
