package com.shadows.Cooling.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.shadows.Cooling.TE.CoolerTE;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class coolerBlock extends BlockContainer {

	protected coolerBlock(int id, Material material) {
		super(id, material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new CoolerTE();
	}

	@SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("furnace_side");
        this.field_94459_cP = par1IconRegister.registerIcon(this.isActive ? "furnace_front_lit" : "furnace_front");
        this.field_94458_cO = par1IconRegister.registerIcon("furnace_top");
    }
}
