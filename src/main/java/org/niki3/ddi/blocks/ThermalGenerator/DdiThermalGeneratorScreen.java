package org.niki3.ddi.blocks.ThermalGenerator;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DdiThermalGeneratorScreen extends AbstractContainerScreen<DdiThermalGeneratorMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("ddi", "textures/gui/container/thermal_generator.png");
    private static final ResourceLocation ENERGY = ResourceLocation.fromNamespaceAndPath("ddi","textures/gui/sprites/container/energy52_24.png");
    private static final ResourceLocation FIRE = ResourceLocation.fromNamespaceAndPath("minecraft","textures/gui/sprites/container/furnace/lit_progress.png");

    public DdiThermalGeneratorScreen(DdiThermalGeneratorMenu menu, Inventory inventory, Component title){
        super(menu, inventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
        gui.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        int energy = menu.getEnergy();
        int maxEnergy = menu.getMaxEnergy();

        if (maxEnergy > 0){
            int fullHeight = 52;
            int height = energy * fullHeight / maxEnergy;

            gui.blit(ENERGY, leftPos + 116, topPos + 20 + (fullHeight - height), 0, fullHeight - height, 24, height);
        }

        int burn = menu.getBurnTime();
        int maxBurn = menu.getMaxBurnTime();

        if (maxBurn > 0 && burn > 0){
            int fullHeight = 14;
            int height = burn * fullHeight / maxBurn;

            gui.blit(FIRE, leftPos + 56, topPos + 36 + (fullHeight - height), 0, fullHeight - height, 14, height);
        }
    }
}
