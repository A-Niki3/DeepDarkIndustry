package org.niki3.ddi.blocks.others;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class StorageScreen extends AbstractContainerScreen<StorageContainer> {
    // 通常のチェストサイズ（27スロット用）テクスチャ
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/shulker_box.png");

    public StorageScreen(StorageContainer container, Inventory inv, Component title) {
        super(container, inv, title);
        this.imageWidth = 176;  // GUIの幅
        this.imageHeight = 166; // 通常チェストに合わせた高さ
    }

    @Override
    protected void init(){
        super.init();
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float delta){
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack,mouseX,mouseY);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, this.title, 8, 6, 4210752);
        this.font.draw(poseStack, this.playerInventoryTitle, 8, this.imageHeight - 94, 4210752);
    }
}

