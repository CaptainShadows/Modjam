package Cooling.utils;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

    public static int coolerID;

    public static void init(FMLPreInitializationEvent event) {

        File configFile = new File(
                event.getModConfigurationDirectory(),
                Registry.name + ".cfg");

        Configuration configuration = new Configuration(
                configFile);

        try{
            configuration.load();

            // *************************Block config's***********************

            coolerID = configuration
                    .getBlock("Cooler Block", 900,
                            "Cooler Block Id:").getInt();

        }catch(Exception e){
            FMLLog.log(
                    Level.SEVERE,
                    e,
                    Registry.name
                            + " has had a problem loading its configuration");
        }finally{
            configuration.save();
        }
    }
}