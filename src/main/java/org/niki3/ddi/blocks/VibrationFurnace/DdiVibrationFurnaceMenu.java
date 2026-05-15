package org.niki3.ddi.blocks.VibrationFurnace;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiMenuScreen;

public class DdiVibrationFurnaceMenu extends AbstractContainerMenu {

    private final ItemStackHandler inventory;
    private final DdiVibrationFurnaceBlockEntity blockEntity;
    private int energy;
    private int maxEnergy;
    private int progress;
    private int maxProgress;

    public DdiVibrationFurnaceMenu(int id, Inventory plInv, ItemStackHandler inventory, DdiVibrationFurnaceBlockEntity blockEntity){
        super(DdiMenuScreen.VIBRATION_FURNACE.get(), id);

        this.inventory = inventory;
        this.blockEntity = blockEntity;

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getProgress() : 0;
            }

            @Override
            public void set(int value) {
                progress = value;
            }
        });

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getProgress() : 0;
            }

            @Override
            public void set(int value) {
                maxProgress = value;
            }
        });

        addDataSlot(new DataSlot(){
            @Override
            public int get(){
                return blockEntity != null ? blockEntity.getEnergy() : 0;
            }

            @Override
            public void set(int value){
                energy = value;
            }
        });

        addDataSlot(new DataSlot(){
            @Override
            public int get(){
                return blockEntity != null ? blockEntity.getMaxEnergy() : 0;
            }

            @Override
            public void set(int value){
                maxEnergy = value;
            }
        });
        // 精錬スロット 微調整必要
        this.addSlot(new SlotItemHandler(inventory, 0, 56, 47));
        // 完成品スロット　微調整必要
        this.addSlot(new SlotItemHandler(inventory, 1, 61, 49));

        // インベントリ
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 9; j++){
                this.addSlot(new Slot(plInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // ホットバー
        for(int i = 0; i < 9; i++){
            this.addSlot(new Slot(plInv, i, 8 + i * 18, 142));
        }

    }

    public DdiVibrationFurnaceMenu(int id, Inventory plInv, RegistryFriendlyByteBuf buf){
        this(id, plInv, new ItemStackHandler(2), null);
    }

    public int getEnergy(){
        return energy;
    }

    public int getMaxEnergy(){
        return maxEnergy;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index){
        ItemStack original = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            original = stack.copy();

            if (index < 2){
                if (!this.moveItemStackTo(stack, 2, 38, true)){
                    return ItemStack.EMPTY;
                }
            }else {
                if (!this.moveItemStackTo(stack, 0, 1, false)){
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()){
                slot.set(ItemStack.EMPTY);
            }else {
                slot.setChanged();
            }
        }
        return original;
    }

    @Override
    public boolean stillValid(@NotNull Player player){
        return true;
    }

    public DdiVibrationFurnaceBlockEntity getBlockEntity(){
        return blockEntity;
    }

    public ItemStackHandler getInventory(){
        return inventory;
    }

    public int getProgress(){
        return progress;
    }

    public int getMaxProgress(){
        return maxProgress;
    }
}
