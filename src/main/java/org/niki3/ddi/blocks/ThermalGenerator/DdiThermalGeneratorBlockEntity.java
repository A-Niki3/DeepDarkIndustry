package org.niki3.ddi.blocks.ThermalGenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiBlockEntities;

public class DdiThermalGeneratorBlockEntity extends BlockEntity {
    private int burnTime = 0;
    private int maxBurnTime = 0;

    private final SimpleContainer inventory = new SimpleContainer(1);

    public DdiThermalGeneratorBlockEntity(BlockPos pos, BlockState state){
        super(DdiBlockEntities.THERMAL_GENERATOR.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DdiThermalGeneratorBlockEntity be){
        if (level.isClientSide) return;

        if(be.burnTime > 0){
            be.burnTime--;
        }

        if(be.burnTime == 0){
            ItemStack stack = be.inventory.getItem(0);

            if(!stack.isEmpty()){
                int fuel = AbstractFurnaceBlockEntity.getFuel().getOrDefault(stack.getItem(), 0);

                if(fuel > 0){
                    be.burnTime = fuel;
                    be.maxBurnTime = fuel;

                    stack.shrink(1);
                }
            }
        }

        if(be.burnTime % 20 == 0){
            System.out.println("Burn: " + be.burnTime);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.saveAdditional(tag, provider);

        tag.putInt("BurnTime", burnTime);
        tag.putInt("MaxBurnTime", maxBurnTime);

        NonNullList<ItemStack> list = NonNullList.withSize(1, ItemStack.EMPTY);
        list.set(0, inventory.getItem(0));

        ContainerHelper.saveAllItems(tag, list, provider);
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider){
        super.loadAdditional(tag, provider);

        burnTime = tag.getInt("BurnTime");
        maxBurnTime = tag.getInt("MaxBurnTime");

        NonNullList<ItemStack> list = NonNullList.withSize(1, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list, provider);
        inventory.setItem(0, list.get(0));
    }
}
