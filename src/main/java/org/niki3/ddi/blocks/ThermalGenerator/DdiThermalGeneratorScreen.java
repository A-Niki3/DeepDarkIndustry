package org.niki3.ddi.blocks.ThermalGenerator;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DdiThermalGeneratorScreen extends AbstractContainerScreen<DdiThermalGeneratorMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("ddi", "textures/gui/container/thermal_generator.png");
    private static final ResourceLocation ENERGY = ResourceLocation.fromNamespaceAndPath("ddi","textures/gui/container/energy52_24.png");
    private static final ResourceLocation FIRE = ResourceLocation.withDefaultNamespace("container/furnace/lit_progress");

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

            gui.blit(ENERGY, leftPos + 112, topPos + 17 + (fullHeight - height) , 16, 53 - height, 24, height,54,54);
        }

        int burn = menu.getBurnTime();
        int maxBurn = menu.getMaxBurnTime();

        if (maxBurn > 0 && burn > 0){
            int fullHeight = 14;
            int height = burn * fullHeight / maxBurn;

            gui.blitSprite(FIRE, 14, 14, 0, fullHeight - height, leftPos + 56, topPos + 36 + fullHeight - height, 14, height);
        }
    }
}
