package org.niki3.ddi.blocks.ThermalGenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.EnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niki3.ddi.registration.DdiBlockEntities;

import java.security.DrbgParameters;
import java.util.Objects;

public class DdiThermalGeneratorBlockEntity extends BlockEntity {
    public int burnTime = 0;
    public int maxBurnTime = 0;

    private final EnergyStorage energyStorage = new EnergyStorage(10000,0,100,0){
        @Override
        public boolean canReceive(){
            return false;
        }
    };

    private final SimpleContainer inventory = new SimpleContainer(1);

    public DdiThermalGeneratorBlockEntity(BlockPos pos, BlockState state){
        super(DdiBlockEntities.THERMAL_GENERATOR.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DdiThermalGeneratorBlockEntity be){
        if (level.isClientSide) return;

        if(be.burnTime > 0){
            be.burnTime--;

            be.energyStorage.receiveEnergy(20,false); // 1tick 20FE

            setChanged(level, pos, state);
        }

        if(be.burnTime == 0){
            ItemStack stack = be.inventory.getItem(0);

            if(!stack.isEmpty()){
                int fuel = AbstractFurnaceBlockEntity.getFuel().getOrDefault(stack.getItem(), 0);

                if(fuel > 0){
                    be.burnTime = fuel;
                    be.maxBurnTime = fuel;

                    stack.shrink(1);

                    setChanged(level, pos, state);
                }
            }
        }
        /*
        if(be.burnTime % 20 == 0){
            System.out.println("Burn: " + be.burnTime);
        }
         */
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.saveAdditional(tag, provider);

        tag.putInt("BurnTime", burnTime);
        tag.putInt("MaxBurnTime", maxBurnTime);
        tag.put("Energy", energyStorage.serializeNBT(provider));

        NonNullList<ItemStack> list = NonNullList.withSize(1, ItemStack.EMPTY);
        list.set(0, inventory.getItem(0));

        ContainerHelper.saveAllItems(tag, list, provider);
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.loadAdditional(tag, provider);

        burnTime = tag.getInt("BurnTime");
        maxBurnTime = tag.getInt("MaxBurnTime");

        if (tag.contains("Energy")){
            energyStorage.deserializeNBT(provider, Objects.requireNonNull(tag.get("Energy")));
        }

        NonNullList<ItemStack> list = NonNullList.withSize(1, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list, provider);
        inventory.setItem(0, list.get(0));
    }

    public Container getContainer() {
        return inventory;
    }

    public EnergyStorage getEnergyStorage(){
        return energyStorage;
    }
    public int getBurnTime(){
        return burnTime;
    }

    public int getMaxBurnTime() {
        return maxBurnTime;
    }

    public int getEnergy() {
        return energyStorage.getEnergyStored();
    }

    public int getMaxEnergy() {
        return energyStorage.getMaxEnergyStored();
    }
}
