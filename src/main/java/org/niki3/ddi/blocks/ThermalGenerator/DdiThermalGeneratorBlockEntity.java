package org.niki3.ddi.blocks.ThermalGenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiBlockEntities;

import java.util.Objects;

public class DdiThermalGeneratorBlockEntity extends BlockEntity {
    public int burnTime = 0;
    public int maxBurnTime = 0;

    private static final int MAX_OUTPUT = 100;

    private final EnergyStorage energyStorage = new EnergyStorage(64000,20,100,0){
        @Override
        public boolean canReceive(){
            return true;
        }
    };

    private final ItemStackHandler inventory = new ItemStackHandler(1){
        @Override
        public boolean isItemValid(int slot, ItemStack stack){
            return stack.getBurnTime(RecipeType.SMELTING) > 0;
        }
    };

    public DdiThermalGeneratorBlockEntity(BlockPos pos, BlockState state){
        super(DdiBlockEntities.THERMAL_GENERATOR.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DdiThermalGeneratorBlockEntity be){
        if (level.isClientSide) return;

        boolean hasSpace = be.energyStorage.getEnergyStored() < be.energyStorage.getMaxEnergyStored();

        if(be.burnTime > 0){
            be.burnTime--;

            if(hasSpace) {
                be.energyStorage.receiveEnergy(20, false); // 1tick 20FE
            }

            setChanged(level, pos, state);
        }

        if(be.burnTime == 0 && hasSpace){
            ItemStack stack = be.inventory.getStackInSlot(0);

            if(!stack.isEmpty()){
                int fuel = stack.getBurnTime(RecipeType.SMELTING);

                if(fuel > 0){
                    be.burnTime = fuel;
                    be.maxBurnTime = fuel;

                    stack.shrink(1);

                    setChanged(level, pos, state);
                }
            }
        }

        int energy = be.energyStorage.getEnergyStored();
        if(energy > 0){
            for(Direction direction : Direction.values()){
                BlockPos targetPos = pos.relative(direction);
                BlockEntity target = level.getBlockEntity(targetPos);

                if(target == null) continue;

                var capability = level.getCapability(
                        Capabilities.EnergyStorage.BLOCK,
                        targetPos,
                        direction.getOpposite()
                );
                if(capability != null){
                    int extractAmount = Math.min(energy, MAX_OUTPUT);

                    // 受け取り可能量チェック
                    int received = capability.receiveEnergy(extractAmount, true);

                    if(received > 0){
                        int extracted = be.energyStorage.extractEnergy(received, false);
                        capability.receiveEnergy(extracted, false);

                        energy -= extracted;

                        if(energy <= 0){
                            break;
                        }
                    }
                }
            }
        }

        boolean isLit = be.burnTime > 0;

        if(state.getValue(DdiThermalGenerator.LIT) != isLit){
            level.setBlock(pos, state.setValue(DdiThermalGenerator.LIT, isLit), 3);
        }
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.saveAdditional(tag, provider);

        tag.putInt("BurnTime", burnTime);
        tag.putInt("MaxBurnTime", maxBurnTime);
        tag.put("Energy", energyStorage.serializeNBT(provider));
        tag.put("Inventory", inventory.serializeNBT(provider));
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.loadAdditional(tag, provider);

        burnTime = tag.getInt("BurnTime");
        maxBurnTime = tag.getInt("MaxBurnTime");

        if (tag.contains("Energy")){
            energyStorage.deserializeNBT(provider, Objects.requireNonNull(tag.get("Energy")));
        }
        if (tag.contains("Inventory")){
            inventory.deserializeNBT(provider, tag.getCompound("Inventory"));
        }
    }

    public ItemStackHandler getInventory(){
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
