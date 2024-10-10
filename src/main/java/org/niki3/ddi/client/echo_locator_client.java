package org.niki3.ddi.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.niki3.ddi.server.PacketHandler;
import org.niki3.ddi.server.LocatorPacket;

public class echo_locator_client {
    @SubscribeEvent
    public static void locate_empty(PlayerInteractEvent.RightClickEmpty event){
        Player player = event.getEntity();
        ItemStack item_main = player.getMainHandItem();
        ItemStack item_off = player.getOffhandItem();
        //System.out.println(item_main + "\n" + item_off);
        if(!(item_main.isEmpty() && item_off.isEmpty())){
            ResourceLocation regnameMAIN = ForgeRegistries.ITEMS.getKey(item_main.getItem());
            ResourceLocation regnameOFF = ForgeRegistries.ITEMS.getKey(item_off.getItem());
            if((regnameMAIN != null && regnameMAIN.toString().equals("ddi:echo_locator"))
                    || (regnameOFF != null && regnameOFF.toString().equals("ddi:echo_locator"))){
                PacketHandler.INSTANCE.sendToServer(new LocatorPacket());
            }
        }
    }
    @SubscribeEvent
    public static void locate_block(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        ItemStack item_main = player.getMainHandItem();
        ItemStack item_off = player.getOffhandItem();
        //System.out.println(item_main + "\n" + item_off);
        if(!(item_main.isEmpty() && item_off.isEmpty())){
            ResourceLocation regnameMAIN = ForgeRegistries.ITEMS.getKey(item_main.getItem());
            ResourceLocation regnameOFF = ForgeRegistries.ITEMS.getKey(item_off.getItem());
            if((regnameMAIN != null && regnameMAIN.toString().equals("ddi:echo_locator"))
                    || (regnameOFF != null && regnameOFF.toString().equals("ddi:echo_locator"))){
                PacketHandler.INSTANCE.sendToServer(new LocatorPacket());
            }
        }
    }

}
