package com.shadows.Cooling;

import com.shadows.Cooling.Blocks.ModBlocks;
import com.shadows.Cooling.sided.ServerProxy;
import com.shadows.Cooling.utils.Config;
import com.shadows.Cooling.utils.FuelHandler;
import com.shadows.Cooling.utils.Handler;
import com.shadows.Cooling.utils.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Registry.id, name = Registry.name, version = Registry.ver)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MainClass {

	@Instance(Registry.id)
	public static MainClass instance;

	@SidedProxy(clientSide = Registry.ClientProxy, serverSide = Registry.ServerProxy)
	public static ServerProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		boolean modLoaded = Handler.isModLoaded();

		if (modLoaded == false) {

			Handler.logName("is Loading it's configuration");
			Config.init(event);

			Handler.LoadMod();
		}
	}

	@Init
	public void load(FMLInitializationEvent event) {

		ModBlocks.init();

		Handler.logName("is Loading it's Recipies");
		Handler.init();

		GameRegistry.registerFuelHandler(new FuelHandler());
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}
}
