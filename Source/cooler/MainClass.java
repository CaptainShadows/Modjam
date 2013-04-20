package cooler;

import cooler.items.ModItems;
import cooler.sided.CommonProxy;
import cooler.utils.Archive;
import cooler.utils.Config;
import cooler.utils.handlers.Handler;
import cooler.utils.handlers.LangugeHandler;
import cooler.utils.handlers.RecipeHandler;
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

@Mod(modid = Archive.id, name = Archive.name, version = Archive.ver)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MainClass {

    @Instance(Archive.id)
    public static MainClass instance;
    
    @SidedProxy(serverSide = Archive.serverProxy, clientSide = Archive.clientProxy)
    public static CommonProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {

        boolean modLoaded = Handler.isModLoaded();

        if (modLoaded == false){

            Handler.logName("is Loading it's configuration");
            Config.init(event);

            Handler.LoadMod();

            // Init Items
            ModItems.init();

            // Load Languages
            LangugeHandler.loadLanguages();
        }
    }

    @Init
    public void load(FMLInitializationEvent event) {

        // Init Recipies
        RecipeHandler.add();
        
        proxy.initCapes();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {

    }
}