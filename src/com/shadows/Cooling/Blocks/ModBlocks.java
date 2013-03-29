package com.shadows.Cooling.Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

import com.shadows.Cooling.utils.Config;
import com.shadows.Cooling.utils.Registry;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block coolerIdle, coolerCooling;

	public static void init() {

		coolerIdle = new BlockCooler(Config.coolerID, false).setHardness(3.5F)
				.setStepSound(Block.soundStoneFootstep)
				.setCreativeTab(CreativeTabs.tabDecorations);

		coolerCooling = new BlockCooler(Config.coolerID + 1, true)
				.setHardness(3.5F).setStepSound(Block.soundStoneFootstep)
				.setLightValue(0.875F);

		GameRegistry.registerBlock(ModBlocks.coolerIdle, Registry.coolerUnloc);

	}
}
