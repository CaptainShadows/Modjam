package cooler.utils;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

    public static int fleshID, coolerID, coolerDamage;

    public static void init(FMLPreInitializationEvent event) {

        File configFile = new File(
                event.getModConfigurationDirectory(),
                Registry.name + ".cfg");

        Configuration configuration = new Configuration(
                configFile);

        try{
            configuration.load();

            // *************************Item config's***********************

            fleshID = configuration.getItem(
                    Registry.flesh + " Item", 9000,
                    Registry.flesh + " Item Id:").getInt();

            coolerID = configuration.getItem(
                    Registry.cooler + " Item", 9001,
                    Registry.cooler + " Item Id:").getInt();

            coolerDamage = configuration.get("How many times can you use a " + Registry.cooler,
                    "You can use it", 8).getInt();

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