package org.niki3.ddi.blocks.machines.MachineMenu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.niki3.ddi.blocks.addMenuType;
import org.niki3.ddi.blocks.machines.BlockEntities.Generators.ThermalGeneratorBlock;

public class GeneratorMenu extends AbstractContainerMenu {
    public final ThermalGeneratorBlock blockEntity;
    private final Level level;
    private final ContainerData data;

    public GeneratorMenu(int id, Inventory inventory, FriendlyByteBuf buf){
        this(id,inventory,inventory.player.level.getBlockEntity(buf.readBlockPos()),new SimpleContainerData(2));
    }

    public GeneratorMenu(int id, Inventory inventory, BlockEntity entity, ContainerData data){
        super(addMenuType.GENERATOR_MENU.get(),id);
        checkContainerSize(inventory,2);
        blockEntity = (ThermalGeneratorBlock) entity;
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
    }

    private void addPlayerHotbar(Inventory inventory) {
        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 9;j++){
                this.addSlot(new Slot(inventory, j + i * 9 + 9,8 + j * 18,84 + i * 18));
            }
        }
    }

    private void addPlayerInventory(Inventory inventory) {
        for(int i = 0;i < 9;i++){
            this.addSlot(new Slot(inventory,i,8 + i * 18,142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }
}
