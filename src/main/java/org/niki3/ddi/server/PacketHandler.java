package org.niki3.ddi.server;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    public static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE;

    public static void registerPackets(){
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("ddi","deepdark"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        INSTANCE.messageBuilder(LocatorPacket.class,0, NetworkDirection.PLAY_TO_SERVER)
                .decoder(LocatorPacket::new)
                .encoder(LocatorPacket::toBytes)
                .consumerMainThread(LocatorPacket::handle)
                .add();
    }
}
