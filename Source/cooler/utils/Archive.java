package cooler.utils;

public class Archive {

    // ************************************Mod**********************************
    public static final String id = "cooler";
    public static final String name = "Cooler Mod";
    public static final String ver = "@VERSION@";
    public static final String depend = "required-after:Forge@[7.7.1.650,)";

    // ************************************Items**********************************
    public static final String flesh = "Flesh";
    public static final String cooler = "Cooler";

    // ********************************Locations********************************
    public static final String texture = id + ":";
    public static final String lang = "/mods/" + id + "/lang/";
    public static final String proxy = id + ".sided.";
    public static final String clientProxy = proxy + "ClientProxy";
    public static final String serverProxy = proxy + "CommonProxy";
    public static final String capes = "https://raw.github.com/Claycorp/Harvestry/master/capes.txt";
}