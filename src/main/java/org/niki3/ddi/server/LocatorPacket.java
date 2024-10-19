package org.niki3.ddi.server;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.network.NetworkEvent;


import java.util.function.Supplier;

public class LocatorPacket {
    public LocatorPacket() {}

    public LocatorPacket(FriendlyByteBuf buf) {
        // 必要に応じてデータを読み込む
    }

    public void toBytes(FriendlyByteBuf buf) {
        // 必要に応じてデータを書き込む
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // サーバー側で処理
            ServerPlayer player = context.getSender();
            if (player != null) {
                ItemCooldowns cooldowns = player.getCooldowns();
                ItemStack item = player.getUseItem();
                if(!cooldowns.isOnCooldown(item.getItem())){
                    cooldowns.addCooldown(item.getItem(),100);
                    ServerLevel serverLevel = player.getLevel();
                    BlockPos start_pos = player.blockPosition();
                    Pair<BlockPos, Holder<Biome>> result = serverLevel.findClosestBiome3d(
                            holder -> holder.is(Biomes.DEEP_DARK),
                            start_pos,
                            5000,
                            100,
                            8
                    );
                    //System.out.println(result);
                    if(result != null){
                        BlockPos DeepDarkPos = result.getFirst();
                        player.sendSystemMessage(Component.literal("DeepDark:" + DeepDarkPos.getX() + "," + DeepDarkPos.getZ()));
                    }
                    else {
                        player.sendSystemMessage(Component.literal("DeepDark is not found"));
                    }
                }
                else {
                    player.sendSystemMessage(Component.literal("Cooling down"));
                }
                // プレイヤーの名前をサーバーコンソールに表示
                //player.sendSystemMessage(Component.literal(player.getName().getString() + " use echo locator"));
            }
        });
        context.setPacketHandled(true);
    }
}
