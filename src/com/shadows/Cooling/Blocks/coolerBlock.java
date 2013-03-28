package com.shadows.Cooling.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class coolerBlock extends BlockContainer{

	protected coolerBlock(int id, Material material) {
		super(id, material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
