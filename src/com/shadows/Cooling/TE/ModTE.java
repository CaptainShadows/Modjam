package com.shadows.Cooling.TE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.shadows.Cooling.utils.Registry;

public class ModTE extends TileEntity {

	private ForgeDirection orientation;
	private short state;
	private String owner;
	private String customName;

	public ModTE() {

		orientation = ForgeDirection.SOUTH;
		state = 0;
		owner = "";
		customName = "";
	}

	public ForgeDirection getOrientation() {

		return orientation;
	}

	public void setOrientation(ForgeDirection orientation) {

		this.orientation = orientation;
	}

	public void setOrientation(int orientation) {

		this.orientation = ForgeDirection.getOrientation(orientation);
	}

	public short getState() {

		return state;
	}

	public void setState(short state) {

		this.state = state;
	}

	public String getOwner() {

		return owner;
	}

	public boolean hasOwner() {

		return owner != null && owner.length() > 0;
	}

	public void setOwner(String owner) {

		this.owner = owner;
	}

	public boolean hasCustomName() {

		return customName != null && customName.length() > 0;
	}

	public String getCustomName() {

		return customName;
	}

	public void setCustomName(String customName) {

		this.customName = customName;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {

		return owner.equals(player.username);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);

		if (nbtTagCompound.hasKey(Registry.NBT_TE_DIRECTION_KEY)) {
			orientation = ForgeDirection.getOrientation(nbtTagCompound
					.getByte(Registry.NBT_TE_DIRECTION_KEY));
		}

		if (nbtTagCompound.hasKey(Registry.NBT_TE_STATE_KEY)) {
			state = nbtTagCompound.getShort(Registry.NBT_TE_STATE_KEY);
		}

		if (nbtTagCompound.hasKey(Registry.NBT_TE_OWNER_KEY)) {
			owner = nbtTagCompound.getString(Registry.NBT_TE_OWNER_KEY);
		}

		if (nbtTagCompound.hasKey(Registry.NBT_TE_CUSTOM_NAME)) {
			customName = nbtTagCompound.getString(Registry.NBT_TE_CUSTOM_NAME);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);

		nbtTagCompound.setByte(Registry.NBT_TE_DIRECTION_KEY,
				(byte) orientation.ordinal());
		nbtTagCompound.setShort(Registry.NBT_TE_STATE_KEY, state);

		if (hasOwner()) {
			nbtTagCompound.setString(Registry.NBT_TE_OWNER_KEY, owner);
		}

		if (this.hasCustomName()) {
			nbtTagCompound.setString(Registry.NBT_TE_CUSTOM_NAME, customName);
		}
	}
}