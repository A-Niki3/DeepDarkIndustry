package org.niki3.ddi.items.event;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.niki3.ddi.creative.creative_add;

public class cutter_shard {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();
        if (block == Blocks.SCULK) {
            ItemStack heldItem = event.getPlayer().getMainHandItem();
            if (heldItem.getItem() == creative_add.CRYSTAL_CUTTER.get()){
                RandomSource random = RandomSource.create();
                int amount = random.nextInt(3) + 1;
                BlockPos pos = event.getPos();
                Level world = (Level) event.getLevel();
                // スカルクブロックが破壊された場合、残響の欠片をドロップ
                ItemStack drop = new ItemStack(Items.ECHO_SHARD,amount);
                ItemEntity drop_entity = new ItemEntity(world,pos.getX(),pos.getY(),pos.getZ(),drop);

                world.addFreshEntity(drop_entity);
            }
        }
    }
}
