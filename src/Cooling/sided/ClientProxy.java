package Cooling.sided;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;
import Cooling.TE.MainTE;
import Cooling.utils.Registry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends ServerProxy {

    @SideOnly(Side.CLIENT)
    @Override
    public void init() {

        MinecraftForgeClient.preloadTexture(Registry.GUILoc
                + "cooler.png");
    }

    @Override
    public void handleTileEntityPacket(int x, int y, int z,
            ForgeDirection orientation, short state,
            String owner, String customName) {

        TileEntity tileEntity = FMLClientHandler.instance()
                .getClient().theWorld.getBlockTileEntity(x,
                y, z);

        if (tileEntity != null){
            if (tileEntity instanceof MainTE){
                ((MainTE) tileEntity)
                        .setOrientation(orientation);
                ((MainTE) tileEntity).setState(state);
                ((MainTE) tileEntity).setOwner(owner);
                ((MainTE) tileEntity)
                        .setCustomName(customName);
            }
        }
    }
}