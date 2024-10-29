package org.niki3.ddi.blocks.others;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.blocks.addBlockEntity;

public class StorageContainer extends AbstractContainerMenu {
    public StorageContainer(int id, @NotNull Inventory playerInventory, BlockPos pos, Level level) {
        super(addBlockEntity.TEST_STORAGE_CONTAINER.get(),id);
        StorageBlockEntity blockEntity = (StorageBlockEntity) level.getBlockEntity(pos);

        if(blockEntity != null){
            for(int i = 0;i < 3;++i){
                for(int j = 0;j < 9;++j){
                    this.addSlot(new Slot(blockEntity.getInventory(), j + i * 9, 8 + j * 18, 18 + i * 18));
                }
            }
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if(slot.hasItem()){
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();

            if(index < 27){
                if(!this.moveItemStackTo(slotStack,27,this.slots.size(),true)){
                    return ItemStack.EMPTY;
                }
                else {
                    if(!this.moveItemStackTo(slotStack,0,27,false)){
                        return ItemStack.EMPTY;
                    }
                }
            }

            if(slotStack.isEmpty()){
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }

        return stack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }


}
