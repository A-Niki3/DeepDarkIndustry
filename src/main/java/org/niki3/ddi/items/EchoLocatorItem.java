package org.niki3.ddi.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.server.LocatorPacket;
import org.niki3.ddi.server.PacketHandler;

public class EchoLocatorItem extends Item {
    public EchoLocatorItem(Properties properties){
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand){
        if(!level.isClientSide()){
            PacketHandler.INSTANCE.sendToServer(new LocatorPacket());
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
