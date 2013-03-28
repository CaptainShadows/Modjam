package com.shadows.Cooling.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.shadows.Cooling.TE.CoolerTE;
import com.shadows.Cooling.utils.Registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class coolerBlock extends BlockContainer {

	/** True if this is an active cooler, false if idle */
	private final boolean isActive;

	@SideOnly(Side.CLIENT)
    private Icon field_94458_cO;
    @SideOnly(Side.CLIENT)
    private Icon field_94459_cP;
	
	protected coolerBlock(int id, boolean isActive) {
		// TODO Auto-generated constructor stub
		super(id, Material.rock);
		this.isActive = isActive;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new CoolerTE();
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Registry.texture + "Sides");
		this.field_94459_cP = par1IconRegister
				.registerIcon(this.isActive ? Registry.texture + "front_On"
						: Registry.texture + "front_Off");
		this.field_94458_cO = par1IconRegister.registerIcon(Registry.texture + "Top");
	}
}
