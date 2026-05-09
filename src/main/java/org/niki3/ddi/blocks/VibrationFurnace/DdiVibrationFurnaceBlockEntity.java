package org.niki3.ddi.blocks.VibrationFurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiBlockEntities;

import java.util.Objects;
import java.util.Optional;

public class DdiVibrationFurnaceBlockEntity extends BlockEntity {
    private static final int ENERGY_PER_TICK = 20;
    private static final int MAX_PROGRESS = 100;

    private final EnergyStorage energyStorage = new EnergyStorage(64000,200,0,0){
        @Override
        public boolean canReceive(){
            return true;
        }
    };

    private final ItemStackHandler inventory = new ItemStackHandler(2){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack){
            return slot == 0;
        }
    };

    private int progress = 0;

    public DdiVibrationFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(DdiBlockEntities.VIBRATION_FURNACE.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DdiVibrationFurnaceBlockEntity be){
        if(level.isClientSide) return;

        ItemStack input = be.inventory.getStackInSlot(0);
        ItemStack output = be.inventory.getStackInSlot(1);

        boolean canCraft = false;

        SingleRecipeInput inventoryInput = new SingleRecipeInput(input);

        Optional<RecipeHolder<SmeltingRecipe>> recipe = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, inventoryInput, level);

        if(recipe.isPresent()){
            ItemStack result = recipe.get().value().assemble(inventoryInput, level.registryAccess());

            if(output.isEmpty()){
                canCraft = true;
            } else if (ItemStack.isSameItemSameComponents(output, result) && output.getCount() + result.getCount() <= output.getMaxStackSize()) {
                canCraft = true;
            }
        }

        if(canCraft && be.energyStorage.getEnergyStored() >= ENERGY_PER_TICK){
            be.energyStorage.extractEnergy(ENERGY_PER_TICK, false);

            be.progress++;

            if(be.progress >= MAX_PROGRESS){
                ItemStack result = recipe.get().value().assemble(inventoryInput, level.registryAccess());

                be.inventory.extractItem(0,1,false);

                if(output.isEmpty()){
                    be.inventory.setStackInSlot(1,result.copy());
                }else {
                    output.grow(result.getCount());
                }

                be.progress = 0;
            }

            setChanged(level, pos, state);
        }else {
            be.progress = 0;
        }

        boolean isLit = be.progress > 0;

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

    public int getProgress(){
        return progress;
    }

    public int getMaxProgress(){
        return MAX_PROGRESS;
    }
}
