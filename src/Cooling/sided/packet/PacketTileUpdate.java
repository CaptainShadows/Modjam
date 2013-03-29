package Cooling.sided.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraftforge.common.ForgeDirection;
import Cooling.MainClass;
import cpw.mods.fml.common.network.Player;

public class PacketTileUpdate extends MainPacket {

    public int x, y, z;
    public byte orientation;
    public short state;
    public String owner;
    public String customName;

    public PacketTileUpdate() {

        super(PacketTypeHandler.TILE, true);
    }

    public PacketTileUpdate(int x, int y, int z,
            ForgeDirection orientation, short state,
            String owner, String customName) {

        super(PacketTypeHandler.TILE, true);
        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = (byte) orientation.ordinal();
        this.state = state;
        this.owner = owner;
        this.customName = customName;
    }

    @Override
    public void writeData(DataOutputStream data)
            throws IOException {

        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeByte(orientation);
        data.writeShort(state);
        data.writeUTF(owner);
        data.writeUTF(customName);
    }

    @Override
    public void readData(DataInputStream data)
            throws IOException {

        x = data.readInt();
        y = data.readInt();
        z = data.readInt();
        orientation = data.readByte();
        state = data.readShort();
        owner = data.readUTF();
        customName = data.readUTF();
    }

    @Override
    public void execute(INetworkManager manager,
            Player player) {

        MainClass.proxy.handleTileEntityPacket(x, y, z,
                ForgeDirection.getOrientation(orientation),
                state, owner, customName);
    }
}