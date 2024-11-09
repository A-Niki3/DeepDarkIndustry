package org.niki3.ddi.blocks.machines.AbstractMachines;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractGeneratorBlock extends BlockEntity implements MenuProvider {
    private final Component displayName;
    public static final int FUEL_SLOT = 0;
    public static final int CHARGE_SLOT = 1;
    protected final ItemStackHandler itemHandler = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }
    };

    protected final IEnergyStorage energyStorage;

    private final ContainerData data;

    public AbstractGeneratorBlock(BlockEntityType<?> type, BlockPos pos, BlockState state){
        super(type,pos,state);
        this.energyStorage = new EnergyStorage(10000,500,100);
        this.data = createData(2);
        this.displayName = Component.literal("Default Generator Name");

    }

    public AbstractGeneratorBlock(BlockEntityType<?> type, BlockPos pos, BlockState state, Component customDisplayName) {
        super(type, pos, state);
        this.displayName = customDisplayName;
        //メモ：完成後各パラメータを引数で調整できるようにやっとく
        this.energyStorage = new EnergyStorage(10000,500,100);
        this.data = createData(2);
    }

    private ContainerData createData(int dataCnt){
        return new ContainerData() {
            private final int[] values = new int[dataCnt];
            @Override
            public int get(int index) {
                return index >= 0 && index < values.length ? values[index] : 0;
            }

            @Override
            public void set(int index, int value) {
                if (index >= 0 && index < values.length) {
                    values[index] = value;
                    setChanged();  // 状態が変更されたことを通知
                }
            }

            @Override
            public int getCount() {
                return values.length;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName(){
        return displayName;
    }

    public abstract int getPowerGeneration();
    public abstract int getFuelConsumption();

    protected abstract void processFuel();

    public void storeEnergy(int amount){
        energyStorage.receiveEnergy(amount,false);
    }

    public int extractEnergy(int amount){
        return energyStorage.extractEnergy(amount,false);
    }

    public int getEnergyStored(){
        return energyStorage.getEnergyStored();
    }

    public int getMaxEnergyStored(){
        return energyStorage.getMaxEnergyStored();
    }

    protected abstract void generatorPower();


    protected void onContentsChanged(int slot) {
    }
}
