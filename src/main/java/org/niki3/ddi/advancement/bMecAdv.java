package org.niki3.ddi.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import org.niki3.ddi.creative.creative_add;

import java.util.function.Consumer;

public class bMecAdv  implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> t){
        Advancement start = Advancement.Builder.advancement().display(
                        creative_add.SCULK_GEAR.get(),
                        Component.translatable("advancements.ddi.mec.root.title"),
                        Component.translatable("advancements.ddi.mec.root.desc"),
                        new ResourceLocation("minecraft:textures/block/deepslate.png"),
                        FrameType.TASK,
                        false,false,false
                )
                .addCriterion("complete_ExpAge",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.ECHO_SHARD))
                .save(t, "ddi:mecage/root");

        // task1:スカルクギア作成
        Advancement task1 = Advancement.Builder.advancement()
                .parent(start)
                .display(
                        creative_add.SCULK_GEAR.get(),
                        Component.translatable("advancements.ddi.mec.root.task1"),
                        Component.translatable("advancements.ddi.mec.root.task1_desc"),
                        null,
                        FrameType.TASK,
                        true,true,false
                )
                .addCriterion(
                        "crafted_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(creative_add.SCULK_GEAR.get())
                )
                .save(t, "ddi:mecage/gear");
    }
}
