package org.niki3.ddi.blocks.VibrationFurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiBlockEntities;

import java.util.Objects;

public class DdiVibrationFurnaceBlockEntity extends BlockEntity {
    private final EnergyStorage energyStorage = new EnergyStorage(64000,20,100,0){
        @Override
        public boolean canReceive(){
            return true;
        }
    };

    private final ItemStackHandler inventory = new ItemStackHandler(){

    };

    public DdiVibrationFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(DdiBlockEntities.VIBRATION_FURNACE.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DdiVibrationFurnaceBlockEntity be){
        if(level.isClientSide) return;

        boolean isLit = be.;

        if(state.getValue(DdiVibrationFurnace.LIT) != isLit){
            level.setBlock(pos, state.setValue(DdiVibrationFurnace.LIT, isLit), 3);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.saveAdditional(tag, provider);

        tag.put("Energy", energyStorage.serializeNBT(provider));
        tag.put("Inventory", inventory.serializeNBT(provider));
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.loadAdditional(tag, provider);

        if(tag.contains("Energy")){
            energyStorage.deserializeNBT(provider, Objects.requireNonNull(tag.get("Energy")));
        }
        if(tag.contains("Inventory")){
            inventory.deserializeNBT(provider, tag.getCompound("Inventory"));
        }
    }

    public ItemStackHandler getInventory(){
        return inventory;
    }

    public EnergyStorage getEnergyStorage(){
        return energyStorage;
    }

    public int getEnergy(){
        return energyStorage.getEnergyStored();
    }

    public int getMaxEnergy(){
        return energyStorage.getMaxEnergyStored();
    }
}
