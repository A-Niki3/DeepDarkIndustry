package org.niki3.ddi.blocks.machines.BlockEntities.Generators;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.blocks.machines.AbstractMachines.AbstractGeneratorBlock;

public class ThermalGeneratorBlock extends AbstractGeneratorBlock {
    public ThermalGeneratorBlock(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, Component.literal("Thermal Generator"));
    }

    @Override
    public int getPowerGeneration(){
        return 20;
    }

    @Override
    public int getFuelConsumption(){
        return 5;
    }

    @Override
    public void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        // アイテムが変更された際の処理（例えば、エネルギー生成や消費のトリガー）
    }

    @Override
    public boolean isFuel(ItemStack stack) {
        // 燃料スロットに入れられるアイテムかどうかを判定
        return stack.getItem() instanceof FuelItem;
    }

    @Override
    public boolean isChargeable(ItemStack stack) {
        // 充電スロットに入れられるアイテムかどうかを判定
        return stack.getItem() instanceof ChargeableItem;
    }

    @Override
    public void processFuel() {
        // 燃料を消費してエネルギーを生成する処理
        ItemStack fuelStack = itemHandler.getStackInSlot(FUEL_SLOT);
        if (isFuel(fuelStack)) {
            // 燃料を消費してエネルギーを受け取る
            // energyStorage.receiveEnergy(...);
        }
    }

    @Override
    public void processCharging() {
        // 充電アイテムを消費してエネルギーを充電する処理
        ItemStack chargeStack = itemHandler.getStackInSlot(CHARGE_SLOT);
        if (isChargeable(chargeStack)) {
            // 充電アイテムの処理
            // energyStorage.receiveEnergy(...);
        }
    }

    @Override
    protected void generatorPower() {
        storeEnergy(100);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player){
        return new ThermalGeneratorContainer(id,player,this);
    }
}
