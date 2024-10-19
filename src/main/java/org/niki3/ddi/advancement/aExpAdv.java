package org.niki3.ddi.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.niki3.ddi.creative.creative_add;

import java.util.function.Consumer;

public class aExpAdv implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> t){
        //最初から解禁しているやつ
        Advancement start = Advancement.Builder.advancement().display(
                        Items.SCULK,
                Component.translatable("advancements.ddi.exp.root.title"),
                Component.translatable("advancements.ddi.exp.root.desc"),
                new ResourceLocation("minecraft:textures/block/blast_furnace_top.png"),
                FrameType.TASK,
                false,false,false
                )
                .addCriterion("in_overworld",
                        PlayerTrigger.TriggerInstance.located(LocationPredicate.inDimension(Level.OVERWORLD)))
                .save(t, "ddi:expage/root");
        //task1:クリスタルカッター入手 task2:ウールブーツ入手 task3:エコーロケーター入手 task4:エコーロケーターの次に残響の欠片を入手
        Advancement task1 = Advancement.Builder.advancement()
                .parent(start)
                .display(
                        creative_add.CRYSTAL_CUTTER.get(),
                        Component.translatable("advancements.ddi.exp.root.task1"),
                        Component.translatable("advancements.ddi.exp.root.task1_desc"),
                        null,
                        FrameType.TASK,
                        true,true,false
                )
                .addCriterion(
                        "crafted_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(creative_add.CRYSTAL_CUTTER.get())
                )
                .save(t, "ddi:expage/cutter");
        Advancement task2 = Advancement.Builder.advancement()
                .parent(start)
                .display(
                        creative_add.WOOL_BOOTS.get(),
                        Component.translatable("advancements.ddi.exp.root.task2"),
                        Component.translatable("advancements.ddi.exp.root.task2_desc"),
                        null,
                        FrameType.TASK,
                        true,true,false
                )
                .addCriterion(
                        "crafted_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(creative_add.WOOL_BOOTS.get())
                )
                .save(t, "ddi:expage/wool_boots");
        Advancement task3 = Advancement.Builder.advancement()
                .parent(start)
                .display(
                        creative_add.ECHO_LOCATOR.get(),
                        Component.translatable("advancements.ddi.exp.root.task3"),
                        Component.translatable("advancements.ddi.exp.root.task3_desc"),
                        null,
                        FrameType.TASK,
                        true,true,false
                )
                .addCriterion(
                        "crafted_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(creative_add.ECHO_LOCATOR.get())
                )
                .save(t, "ddi:expage/echo_locator");
        Advancement task4 = Advancement.Builder.advancement()
                .parent(task3)
                .display(
                        Items.ECHO_SHARD,
                        Component.translatable("advancements.ddi.exp.root.task4"),
                        Component.translatable("advancements.ddi.exp.root.task4_desc"),
                        null,
                        FrameType.GOAL,
                        true,true,false
                )
                .addCriterion(
                        "get_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.ECHO_SHARD)
                )
                .save(t, "ddi:expage/get_shard");

    }
}
