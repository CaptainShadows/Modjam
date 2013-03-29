package com.shadows.Cooling.TE;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.shadows.Cooling.CoolerRecipes;
import com.shadows.Cooling.Blocks.BlockCooler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TECooler extends TileEntity implements ISidedInventory {

	private static final int[] field_102010_d = new int[] { 0 };
	private static final int[] field_102011_e = new int[] { 2, 1 };
	private static final int[] field_102009_f = new int[] { 1 };

	/**
	 * The ItemStacks that hold the items currently being used in the cooler
	 */
	private ItemStack[] coolerItemStacks = new ItemStack[3];

	/** The number of ticks that the cooler will keep burning */
	public int coolerBurnTime = 0;

	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the cooler burning for
	 */
	public int currentItemBurnTime = 0;

	/** The number of ticks that the current item has been cooking for */
	public int coolerCookTime = 0;
	private String field_94130_e;

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory() {
		return this.coolerItemStacks.length;
	}

	/**
	 * Returns the stack in slot i
	 */
	public ItemStack getStackInSlot(int par1) {
		return this.coolerItemStacks[par1];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number
	 * (second arg) of items and returns them in a new stack.
	 */
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.coolerItemStacks[par1] != null) {
			ItemStack itemstack;

			if (this.coolerItemStacks[par1].stackSize <= par2) {
				itemstack = this.coolerItemStacks[par1];
				this.coolerItemStacks[par1] = null;
				return itemstack;
			} else {
				itemstack = this.coolerItemStacks[par1].splitStack(par2);

				if (this.coolerItemStacks[par1].stackSize == 0) {
					this.coolerItemStacks[par1] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench
	 * GUI.
	 */
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.coolerItemStacks[par1] != null) {
			ItemStack itemstack = this.coolerItemStacks[par1];
			this.coolerItemStacks[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.coolerItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null
				&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory.
	 */
	public String getInvName() {
		return this.isInvNameLocalized() ? this.field_94130_e
				: "container.cooler";
	}

	/**
	 * If this returns false, the inventory name will be used as an unlocalized
	 * name, and translated into the player's language. Otherwise it will be
	 * used directly.
	 */
	public boolean isInvNameLocalized() {
		return this.field_94130_e != null && this.field_94130_e.length() > 0;
	}

	public void func_94129_a(String par1Str) {
		this.field_94130_e = par1Str;
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.coolerItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.coolerItemStacks.length) {
				this.coolerItemStacks[b0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.coolerBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.coolerCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.coolerItemStacks[1]);

		if (par1NBTTagCompound.hasKey("CustomName")) {
			this.field_94130_e = par1NBTTagCompound.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) this.coolerBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short) this.coolerCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.coolerItemStacks.length; ++i) {
			if (this.coolerItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.coolerItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.isInvNameLocalized()) {
			par1NBTTagCompound.setString("CustomName", this.field_94130_e);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be
	 * 64, possibly will be extended. *Isn't this more of a set than a get?*
	 */
	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being completely
	 * cooked
	 */
	public int getCookProgressScaled(int par1) {
		return this.coolerCookTime * par1 / 200;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
	 * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
	 */
	public int getBurnTimeRemainingScaled(int par1) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = 200;
		}

		return this.coolerBurnTime * par1 / this.currentItemBurnTime;
	}

	/**
	 * Returns true if the cooler is currently burning
	 */
	public boolean isBurning() {
		return this.coolerBurnTime > 0;
	}

	/**
	 * Allows the entity to update its state. Overridden in most subclasses,
	 * e.g. the mob spawner uses this to count ticks and creates a new spawn
	 * inside its implementation.
	 */
	public void updateEntity() {
		boolean flag = this.coolerBurnTime > 0;
		boolean flag1 = false;

		if (this.coolerBurnTime > 0) {
			--this.coolerBurnTime;
		}

		if (!this.worldObj.isRemote) {
			if (this.coolerBurnTime == 0 && this.canCool()) {
				this.currentItemBurnTime = this.coolerBurnTime = getItemBurnTime(this.coolerItemStacks[1]);

				if (this.coolerBurnTime > 0) {
					flag1 = true;

					if (this.coolerItemStacks[1] != null) {
						--this.coolerItemStacks[1].stackSize;

						if (this.coolerItemStacks[1].stackSize == 0) {
							this.coolerItemStacks[1] = this.coolerItemStacks[1]
									.getItem().getContainerItemStack(
											coolerItemStacks[1]);
						}
					}
				}
			}

			if (this.isBurning() && this.canCool()) {
				++this.coolerCookTime;

				if (this.coolerCookTime == 200) {
					this.coolerCookTime = 0;
					this.coolItem();
					flag1 = true;
				}
			} else {
				this.coolerCookTime = 0;
			}

			if (flag != this.coolerBurnTime > 0) {
				flag1 = true;
				BlockCooler.updateCoolerBlockState(this.coolerBurnTime > 0,
						this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1) {
			this.onInventoryChanged();
		}
	}

	/**
	 * Returns true if the cooler can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	private boolean canCool() {
		if (this.coolerItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = CoolerRecipes.Cooling().getCoolingResult(
					this.coolerItemStacks[0]);
			if (itemstack == null)
				return false;
			if (this.coolerItemStacks[2] == null)
				return true;
			if (!this.coolerItemStacks[2].isItemEqual(itemstack))
				return false;
			int result = coolerItemStacks[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack
					.getMaxStackSize());
		}
	}

	/**
	 * Turn one item from the cooler source stack into the appropriate smelted
	 * item in the cooler result stack
	 */
	public void coolItem() {
		if (this.canCool()) {
			ItemStack itemstack = CoolerRecipes.Cooling().getCoolingResult(
					this.coolerItemStacks[0]);

			if (this.coolerItemStacks[2] == null) {
				this.coolerItemStacks[2] = itemstack.copy();
			} else if (this.coolerItemStacks[2].isItemEqual(itemstack)) {
				coolerItemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.coolerItemStacks[0].stackSize;

			if (this.coolerItemStacks[0].stackSize <= 0) {
				this.coolerItemStacks[0] = null;
			}
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the
	 * cooler burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack par0ItemStack) {
		if (par0ItemStack == null) {
			return 0;
		} else {
			int i = par0ItemStack.getItem().itemID;
			Item item = par0ItemStack.getItem();

			if (i == Item.snowball.itemID)
				return 400;
			if (i == Block.snow.blockID)
				return 2400;
			if (i == Block.ice.blockID)
				return 4200;
			return GameRegistry.getFuelValue(par0ItemStack);
		}
	}

	/**
	 * Return true if item is a fuel source (getItemBurnTime() > 0).
	 */
	public static boolean isItemFuel(ItemStack par0ItemStack) {
		return getItemBurnTime(par0ItemStack) > 0;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack)
				: true);
	}

	/**
	 * Get the size of the side inventory.
	 */
	public int[] getSizeInventorySide(int par1) {
		return par1 == 0 ? field_102011_e : (par1 == 1 ? field_102010_d
				: field_102009_f);
	}

	public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
		return this.isStackValidForSlot(par1, par2ItemStack);
	}

	public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
		return par3 != 0 || par1 != 1
				|| par2ItemStack.itemID == Item.bucketEmpty.itemID;
	}
}