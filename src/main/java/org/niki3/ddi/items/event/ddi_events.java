package org.niki3.ddi.items.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ddi_events {
    //メッセージ送信メソッド
    public static void sendMessage(String message,Player player){
        player.sendSystemMessage(Component.literal(message));
    }
}
