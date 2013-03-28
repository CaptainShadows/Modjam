package com.shadows.Cooling.inv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import com.shadows.Cooling.TE.TECooler;

public class CoolerC extends Container {

	public CoolerC(InventoryPlayer inventoryPlayer, TECooler CoolerTE) {

		// Add the Cooler "to be cooled" slot to the container
		this.addSlotToContainer(new Slot(CoolerTE, 0, 56, 17));

		// Add the Cooler fuel slot to the container
		this.addSlotToContainer(new Slot(CoolerTE, 1, 56, 62));

		// Add the Cooled results slot to the container
		this.addSlotToContainer(new Slot(CoolerTE, 2, 116, 35));

		// Add the player's inventory slots to the container
		for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
				this.addSlotToContainer(new Slot(inventoryPlayer,
						inventoryColumnIndex + inventoryRowIndex * 9 + 9,
						8 + inventoryColumnIndex * 18,
						94 + inventoryRowIndex * 18));
			}
		}

		// Add the player's action bar slots to the container
		for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
			this.addSlotToContainer(new Slot(inventoryPlayer,
					actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 152));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return true;
	}

}
