package org.niki3.ddi.server;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import org.niki3.ddi.blocks.others.StorageBlockEntity;

import java.util.function.Supplier;

public class ChestGuiPacket {
    private final BlockPos pos;

    // コンストラクタ
    public ChestGuiPacket(BlockPos pos) {
        this.pos = pos;
    }

    // デコード（受信時に呼び出される）
    public ChestGuiPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    // エンコード（送信時に呼び出される）
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    // サーバー側でPacketを受け取って処理するメソッド
    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.level.isLoaded(pos)) {
                BlockEntity blockEntity = player.level.getBlockEntity(pos);
                //packet送信元で確認しているからこの条件は不要かも？
                if (blockEntity instanceof MenuProvider) {
                    player.openMenu((MenuProvider) blockEntity); // インベントリを開く
                }
            }
        });

        context.setPacketHandled(true);
    }
}
