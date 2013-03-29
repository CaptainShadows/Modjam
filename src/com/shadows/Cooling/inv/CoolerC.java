package com.shadows.Cooling.inv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.shadows.Cooling.CoolerRecipes;
import com.shadows.Cooling.TE.TECooler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CoolerC extends Container {
	private TECooler coolerTE;
	private int lastCookTime = 0;
	private int lastBurnTime = 0;
	private int lastItemBurnTime = 0;

	public CoolerC(InventoryPlayer player, TECooler coolerTE) {
		this.coolerTE = coolerTE;
		this.addSlotToContainer(new Slot(coolerTE, 0, 56, 17));
		this.addSlotToContainer(new Slot(coolerTE, 1, 56, 53));
		this.addSlotToContainer(new SlotCooler(player.player, coolerTE, 2, 116,
				35));
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0,
				this.coolerTE.coolerCookTime);
		par1ICrafting.sendProgressBarUpdate(this, 1,
				this.coolerTE.coolerBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2,
				this.coolerTE.currentItemBurnTime);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.coolerTE.coolerCookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.coolerTE.coolerCookTime);
			}

			if (this.lastBurnTime != this.coolerTE.coolerBurnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.coolerTE.coolerBurnTime);
			}

			if (this.lastItemBurnTime != this.coolerTE.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.coolerTE.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.coolerTE.coolerCookTime;
		this.lastBurnTime = this.coolerTE.coolerBurnTime;
		this.lastItemBurnTime = this.coolerTE.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.coolerTE.coolerCookTime = par2;
		}

		if (par1 == 1) {
			this.coolerTE.coolerBurnTime = par2;
		}

		if (par1 == 2) {
			this.coolerTE.currentItemBurnTime = par2;
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.coolerTE.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (par2 != 1 && par2 != 0) {
				if (CoolerRecipes.Cooling().getCoolingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (coolerTE.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (par2 >= 3 && par2 < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39
						&& !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
}