package org.niki3.ddi.items;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public class DdiEchoLocator extends Item {
    public DdiEchoLocator(Properties properties){
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand){
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)){
            return InteractionResultHolder.fail(stack);
        }
        if(!level.isClientSide && player instanceof ServerPlayer serverPlayer){
            ServerLevel serverLevel = (ServerLevel) level;
            BlockPos PlayerPos = player.blockPosition();

            Pair<BlockPos, Holder<Biome>> result = serverLevel.findClosestBiome3d(
                    biomeHolder -> biomeHolder.is(Biomes.DEEP_DARK),
                    PlayerPos,
                    5000,
                    100,
                    8
            );

            if (result != null){
                BlockPos found = result.getFirst();
                serverPlayer.sendSystemMessage(
                        Component.literal("Deep Dark found at:"
                                + "x:" + found.getX() + ","
                                + "z:" + found.getZ()
                        )
                );
            } else{
                serverPlayer.sendSystemMessage(
                        Component.literal("Deep Dark Not Found")
                );
            }
            player.getCooldowns().addCooldown(this, 20 * 5);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
