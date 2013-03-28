package com.shadows.Cooling.Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

import com.shadows.Cooling.utils.Config;

public class ModBlocks {
	public static Block cooler;

	public static void init() {

		ModBlocks.cooler = (new coolerBlock(Config.coolerID, false))
				.setUnlocalizedName("cooler").setCreativeTab(
						CreativeTabs.tabDecorations);
	}
}
