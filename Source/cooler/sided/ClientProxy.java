package cooler.sided;

import com.jadarstudios.api.developercapesapi.DeveloperCapesAPI;

import cooler.utils.Archive;

public class ClientProxy extends CommonProxy {
    /**
     * Initializes the Capes.
     * Original code that adds capes is found in this Github repository:
     * https://github.com/jadar/DeveloperCapesAPI
     */
    @Override
    public void initCapes() {
        // Link for the file that makes the capes work
        DeveloperCapesAPI.getInstance().init(Archive.capes);
    }
}