package org.niki3.ddi.blocks.others;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.blocks.addBlock;
import org.niki3.ddi.blocks.addMenuType;

public class StorageContainer extends AbstractContainerMenu {
    public final StorageBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public StorageContainer(int id, Inventory inventory, FriendlyByteBuf buf){
        this(id,inventory,inventory.player.level.getBlockEntity(buf.readBlockPos()),new SimpleContainerData(0));
    }

    public StorageContainer(int id, Inventory inventory, BlockEntity entity,ContainerData data){
        super(addMenuType.STORAGE_CONTAINER.get(),id);
        checkContainerSize(inventory,27);
        blockEntity = (StorageBlockEntity) entity;
        this.level = inventory.player.level;
        this.data = data;

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 9;j++){
                    this.addSlot(new SlotItemHandler(handler,i * 9 + j,8 + j * 18,18 + i *18));
                }
            }
        });

        addDataSlots(data);
    }

    private static final int HOTBAR_SLOT = 9;
    private static final int PLINVENTORY_ROW = 3;
    private static final int PLINVENTORY_COL = 9;
    private static final int PLINVENTORY = PLINVENTORY_COL * PLINVENTORY_ROW;
    private static final int VANILLA_SLOT = HOTBAR_SLOT + PLINVENTORY;
    private static final int VANILLA_FIRST_INDEX = 0;
    private static final int INVENTORY_FIRST_INDEX = VANILLA_FIRST_INDEX + VANILLA_SLOT;

    private static final int INVENTORY_SLOT = 27;

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if(!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if(index < INVENTORY_FIRST_INDEX){
            if(!moveItemStackTo(sourceStack,INVENTORY_FIRST_INDEX,INVENTORY_FIRST_INDEX + INVENTORY_SLOT,false)){
                return ItemStack.EMPTY;
            }
        }
        else if(index < INVENTORY_FIRST_INDEX + INVENTORY_SLOT){
            if(!moveItemStackTo(sourceStack,VANILLA_FIRST_INDEX,INVENTORY_FIRST_INDEX,false)){
                return ItemStack.EMPTY;
            }
        }
        else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }

        if(sourceStack.getCount() == 0){
            sourceSlot.set(ItemStack.EMPTY);
        }
        else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(player,sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),player, addBlock.STORAGE_BLOCK.get());
    }

    private void addPlayerInventory(Inventory plInventory){
        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 9;j++){
                this.addSlot(new Slot(plInventory, j + i * 9 + 9,8 + j * 18,84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory plInventory){
        for(int i = 0;i < 9;i++){
            this.addSlot(new Slot(plInventory,i,8 + i * 18,142));
        }
    }

    public ContainerData getData() {
        return data;
    }
}
