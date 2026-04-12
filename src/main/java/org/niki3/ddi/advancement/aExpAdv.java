package org.niki3.ddi.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiItems;

import java.util.function.Consumer;

public class aExpAdv implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer){
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        DdiItems.ECHO_LOCATOR.get(),
                        Component.translatable("advancements.ddi.exp.root.title"),
                        Component.translatable("advancements.ddi.exp.root.desc"),
                        ResourceLocation.fromNamespaceAndPath("minecraft","textures/block/blast_furnace_top.png"),
                        AdvancementType.GOAL,
                        true,true,false
                )
                .addCriterion("in_overworld", PlayerTrigger.TriggerInstance.located(
                        LocationPredicate.Builder.inDimension(Level.OVERWORLD)
                ))
                .save(consumer, "ddi:exp/root");

        AdvancementHolder get_crystal_cutter = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        DdiItems.CRYSTAL_CUTTER.get(),
                        Component.translatable("advancements.ddi.exp.root.get_crystal_cutter"),
                        Component.translatable("advancements.ddi.exp.root.get_crystal_cutter_desc"),
                        null,
                        AdvancementType.TASK,
                        true,true,false
                )
                .addCriterion("crafted_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        DdiItems.CRYSTAL_CUTTER.get()
                ))
                .save(consumer,"ddi:exp/get_crystal_cutter");

        AdvancementHolder get_wool_boots = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        DdiItems.WOOL_BOOTS.get(),
                        Component.translatable("advancements.ddi.exp.root.get_wool_boots"),
                        Component.translatable("advancements.ddi.exp.root.get_wool_boots_desc"),
                        null,
                        AdvancementType.TASK,
                        true,true,false
                )
                .addCriterion("crafted_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        DdiItems.WOOL_BOOTS.get()
                ))
                .save(consumer,"ddi:exp/get_wool_boots");

        AdvancementHolder get_echo_locator = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        DdiItems.ECHO_LOCATOR.get(),
                        Component.translatable("advancements.ddi.exp.root.get_echo_locator"),
                        Component.translatable("advancements.ddi.exp.root.get_echo_locator_desc"),
                        null,
                        AdvancementType.TASK,
                        true,true,false
                )
                .addCriterion("crafted_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        DdiItems.ECHO_LOCATOR.get()
                ))
                .save(consumer,"ddi:exp/get_echo_locator");

        AdvancementHolder get_shard = Advancement.Builder.advancement()
                .parent(get_echo_locator)
                .display(
                        Items.ECHO_SHARD,
                        Component.translatable("advancements.ddi.exp.root.get_shard"),
                        Component.translatable("advancements.ddi.exp.root.get_shard_desc"),
                        null,
                        AdvancementType.TASK,
                        true,true,false
                )
                .addCriterion("get_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        Items.ECHO_SHARD
                ))
                .save(consumer,"ddi:exp/get_shard");

        AdvancementHolder get_sculk_gear = Advancement.Builder.advancement()
                .parent(get_shard)
                .display(
                        DdiItems.SCULK_GEAR.get(),
                        Component.translatable("advancements.ddi.exp.root.get_sculk_gear"),
                        Component.translatable("advancements.ddi.exp.root.get_sculk_gear_desc"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,true,false
                )
                .addCriterion("crafted_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        DdiItems.SCULK_GEAR.get()
                ))
                .save(consumer,"ddi:exp/get_sculk_gear");
    }
}
