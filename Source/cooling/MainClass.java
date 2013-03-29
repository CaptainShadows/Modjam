package cooling;

import cooling.items.ModItems;
import cooling.utils.Config;
import cooling.utils.Handler;
import cooling.utils.Registry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Registry.id, name = Registry.name, version = Registry.ver)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MainClass {

    @Instance(Registry.id)
    public static MainClass instance;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {

        boolean modLoaded = Handler.isModLoaded();

        if (modLoaded == false){

            Handler.logName("is Loading it's configuration");
            Config.init(event);

            Handler.LoadMod();
        }
    }

    @Init
    public void load(FMLInitializationEvent event) {
        //Init Items
        ModItems.init();

        //Init Recipies
        Handler.init();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {

    }
}
