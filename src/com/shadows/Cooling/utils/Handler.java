package com.shadows.Cooling.utils;

import net.minecraft.block.Block;

import com.shadows.Cooling.exeptions.NotMyFault;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class Handler {

	private static boolean IsLoaded = false;

	public static void init() {

		Recipes();

	}

	// **********************Adding Language stuff*****************

	public static void addBlockL(Block block, String name) {
		// TODO Auto-generated method stub
		LanguageRegistry.addName(block, name);
	}

	// ****************************Adding Recipies**********************

	private static void Recipes() {
		// TODO Auto-generated method stub

	}

	// *******************************Mod State Check*************************

	public static boolean isModLoaded() {
		if (IsLoaded == true) {

			throw new NotMyFault(
					"Why did you install my Mod twice? Remove the second Cooler-Universal-"
							+ Registry.ver
							+ ".jar out of your mods-Folder, you need only one of them. And another Question: Why the Hax did Forge not detect that before me?");

		} else {
			return false;
		}
	}

	public static void LoadMod() {
		IsLoaded = true;
	}

	public static void UnLoadMod() {
		IsLoaded = false;
	}
	
	public static void log(String s) {
		System.out.println(s);
	}
	
	public static void logName(String s) {
		System.out.println(Registry.name + " " + s);
	}
}
