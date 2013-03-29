package Cooling.sided;

import net.minecraftforge.client.MinecraftForgeClient;
import Cooling.utils.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends ServerProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void init() {

		MinecraftForgeClient.preloadTexture(Registry.GUILoc + "cooler.png");
	}
}