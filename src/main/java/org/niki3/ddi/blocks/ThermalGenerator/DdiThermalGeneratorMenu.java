package org.niki3.ddi.blocks.ThermalGenerator;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiMenuScreen;

public class DdiThermalGeneratorMenu extends AbstractContainerMenu {

    private final Container container;
    private final DdiThermalGeneratorBlockEntity blockEntity;
    private int burnTime;
    private int maxBurnTime;
    private int energy;
    private int maxEnergy;

    public DdiThermalGeneratorMenu(int id, Inventory plInv){
        this(id, plInv, new SimpleContainer(1), null);
    }
    public DdiThermalGeneratorMenu(int id, Inventory plInv, Container container, DdiThermalGeneratorBlockEntity blockEntity){
        super(DdiMenuScreen.THERMAL_GENERATOR.get(), id);

        this.container = container;
        this.blockEntity = blockEntity;

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getBurnTime() : 0;
            }

            @Override
            public void set(int value) {
                burnTime = value;
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getMaxBurnTime() : 0;
            }

            @Override
            public void set(int value) {
                maxBurnTime = value;
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getEnergy() : 0;
            }

            @Override
            public void set(int value) {
                energy = value;
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getMaxEnergy() : 0;
            }

            @Override
            public void set(int value) {
                maxEnergy = value;
            }
        });
        // 燃料
        this.addSlot(new Slot(container, 0, 56, 53));
        // インベントリ
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 9; j++){
                this.addSlot(new Slot(plInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // ホットバー
        for (int i = 0; i < 9; i++){
            this.addSlot(new Slot(plInv, i, 8 + i * 18, 142));
        }
    }

    public DdiThermalGeneratorMenu(int id, Inventory plInv, RegistryFriendlyByteBuf buf) {
        this(id, plInv, new SimpleContainer(1), null);
    }

    public int getEnergy() {
        return energy;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMaxBurnTime() {
        return maxBurnTime;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack original = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            original = stack.copy();

            if (index == 0){
                if (!this.moveItemStackTo(stack, 1, 37, true)){
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

    public Container getContainer() {
        return container;
    }

    public DdiThermalGeneratorBlockEntity getBlockEntity() {
        return blockEntity;
    }
}
