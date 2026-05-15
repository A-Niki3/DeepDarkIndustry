package org.niki3.ddi.blocks.VibrationFurnace;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class DdiVibrationFurnaceScreen extends AbstractContainerScreen<DdiVibrationFurnaceMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("ddi","textures/gui/container/作成したメニュー.png");
    private static final ResourceLocation ENERGY = ResourceLocation.fromNamespaceAndPath("ddi", "textures/gui/container/energy52_24.png");
    private static final ResourceLocation PROG = ResourceLocation.withDefaultNamespace("container/furnace/burn_progress");

    public DdiVibrationFurnaceScreen(DdiVibrationFurnaceMenu menu, Inventory inventory, Component title){
        super(menu, inventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY){
        gui.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        int energy = menu.getEnergy();
        int maxEnergy = menu.getMaxEnergy();
        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();

        if(maxEnergy > 0){
            int fullHeight = 52;
            int height = energy * fullHeight / maxEnergy;

            gui.blit(ENERGY, leftPos + 112, topPos + 17 + (fullHeight - height), 16, 53 - height, 24, height, 54, 54);
        }
        // 精錬進捗バーを追加
        if(){

        }
    }
}
