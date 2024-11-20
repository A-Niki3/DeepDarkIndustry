package org.niki3.ddi.blocks.machines.BlockEntities.Generators;

import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niki3.ddi.blocks.machines.AbstractMachines.AbstractGeneratorBlock;
import org.niki3.ddi.blocks.machines.MachineMenu.GeneratorMenu;

public class ThermalGeneratorBlock extends AbstractGeneratorBlock {
    private int currentBurnTime = 0;
    private int maxBurnTime = 0;

    public ThermalGeneratorBlock(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, Component.literal("Thermal Generator"));
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        if (capability == ForgeCapabilities.ITEM_HANDLER) {
            return lazyInventory.cast();
        }
        return super.getCapability(capability, side);
    }

    @Override
    public int getPowerGeneration(){
        return 20;
    }

    @Override
    public int getFuelConsumption(){
        return 5;
    }

    /* あとで実装 */

    @Override
    public void processFuel() {
        ItemStack fuelStack = itemHandler.getStackInSlot(FUEL_SLOT);
        if(!fuelStack.isEmpty()){
            int burnTime = getTimeOfBarn(fuelStack);
            if (burnTime > 0) {
                setBurnTime(burnTime,burnTime);
                itemHandler.extractItem(FUEL_SLOT,1,false);
            }
        }
    }

    private void setBurnTime(int current, int max) {
        currentBurnTime = current;
        maxBurnTime = max;
    }

    private int getTimeOfBarn(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack,null);
    }

    @Override
    protected void generatorPower() {
        storeEnergy(100);
    }

    @Override
    protected void doWork() {
        if(currentBurnTime > 0){
            generateEnergy();
            currentBurnTime--;
            setChanged();
        }
        else {
            processFuel();
        }
    }

    private void generateEnergy() {
        int EpT = 20;
        energyStorage.receiveEnergy(EpT,false);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player){
        return new GeneratorMenu(id,inventory,this,data);
    }

    public int getCurrentBurnTime() {
        return currentBurnTime;
    }

    public void setCurrentBurnTime(int currentBurnTime) {
        this.currentBurnTime = currentBurnTime;
    }

    public int getMaxBurnTime() {
        return maxBurnTime;
    }

    public void setMaxBurnTime(int maxBurnTime) {
        this.maxBurnTime = maxBurnTime;
    }
}
