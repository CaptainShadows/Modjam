package cooler.utils;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

    // ID for Flesh
    public static int fleshID;

    // Id for Cooler
    public static int coolerID;

    // Amount of times that a Cooler can be used
    public static int coolerDamage;

    public static void init(FMLPreInitializationEvent event) {

        File configFile = new File(event.getModConfigurationDirectory(), Archive.name + ".cfg");

        Configuration configuration = new Configuration(configFile);

        try{
            configuration.load();

            // *************************Item config's***********************
            fleshID = configuration.getItem(Archive.flesh + " Item", 9000,
                    Archive.flesh + " Item Id:").getInt();

            coolerID = configuration.getItem(Archive.cooler + " Item", 9001,
                    Archive.cooler + " Item Id:").getInt();

            coolerDamage = configuration.get("General",
                    "How many times can you use a " + Archive.cooler, 8).getInt();

        }catch(Exception e){
            FMLLog.log(Level.SEVERE, e, Archive.name
                    + " has had a problem loading its configuration");
        }finally{
            configuration.save();
        }
    }
}