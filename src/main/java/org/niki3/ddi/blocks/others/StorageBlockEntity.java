package org.niki3.ddi.blocks.others;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niki3.ddi.blocks.addBlockEntity;

import java.util.Objects;

public class StorageBlockEntity extends BlockEntity implements MenuProvider {
    //private final SimpleContainer inventory = new SimpleContainer(27); // スロット数を27に設定（チェストと同じ）
    private final ItemStackHandler itemHandler = new ItemStackHandler(27){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyInventory = LazyOptional.of(() -> itemHandler);
    private final ContainerData data;

    public StorageBlockEntity(BlockPos pos, BlockState state) {
        super(addBlockEntity.TEST_STORAGE.get(), pos, state);
        this.data = new ContainerData(){

            @Override
            public int get(int p_39284_) {
                return 0;
            }

            @Override
            public void set(int p_39285_, int p_39286_) {

            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }



    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.ddi.test_storage");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player player) {
        return new StorageContainer(id,playerInventory,this,this.data);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        if (capability == ForgeCapabilities.ITEM_HANDLER) {
            return lazyInventory.cast();
        }
        return super.getCapability(capability, side);
    }

    @Override
    public void onLoad() {
        lazyInventory = LazyOptional.of(() -> itemHandler);
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyInventory.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY));
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY));
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0;i < itemHandler.getSlots();i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(Objects.requireNonNull(this.level),this.worldPosition,inventory);
    }
}

/* 参考動画： https://youtu.be/jo0BTisGpJk?si=rZyHE2gGfyWveAzw */