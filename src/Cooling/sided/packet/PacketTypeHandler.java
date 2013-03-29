package Cooling.sided.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import Cooling.utils.Registry;

public enum PacketTypeHandler {

    TILE(PacketTileUpdate.class);

    private Class<? extends MainPacket> clazz;

    PacketTypeHandler(Class<? extends MainPacket> clazz) {

        this.clazz = clazz;
    }

    public static MainPacket buildPacket(byte[] data) {

        ByteArrayInputStream bis = new ByteArrayInputStream(
                data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        MainPacket packet = null;

        try{
            packet = values()[selector].clazz.newInstance();
        }catch(Exception e){
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }

    public static MainPacket buildPacket(
            PacketTypeHandler type) {

        MainPacket packet = null;

        try{
            packet = values()[type.ordinal()].clazz
                    .newInstance();
        }catch(Exception e){
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(MainPacket packetEE) {

        byte[] data = packetEE.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = Registry.channel;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetEE.isChunkDataPacket;

        return packet250;
    }
}