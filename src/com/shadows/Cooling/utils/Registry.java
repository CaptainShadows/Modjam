package com.shadows.Cooling.utils;

public class Registry {

	// ************************************Mod**********************************
	public static final String id = "Cooler";
	public static final String name = "Cooler Mod";
	public static final String ver = "0.1";
	public static final String channel = id;

	// ********************************Locations********************************
	public static final String sided = "com.shadows.Cooling.sided.";
	public static final String texture = "Cooling:";
	public static final String GUILoc = "/mods/Cooling/textures/guis/";

	// *********************************Proxies********************************
	public static final String ClientProxy = sided + "ClientProxy";
	public static final String ServerProxy = sided + "ServerProxy";

	// ********************************Cooler**********************************
	public static final String coolerUnloc = "Cooler";
	public static final String CoolerTE = "tileCooler";
	public static final String CoolerTName = "Cooler";
	public static final String ContainerINV = CoolerTName;
	public static final String CoolerGUI = GUILoc + "cooler.png";
	public static final int CoolerID = 0;

	
    public static final String NBT_TE_OWNER_KEY = "teOwner";
    public static final String NBT_TE_STATE_KEY = "teState";
    public static final String NBT_TE_CUSTOM_NAME = "CustomName";
    public static final String NBT_TE_DIRECTION_KEY = "teDirection";
}