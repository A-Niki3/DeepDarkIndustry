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
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiItems;

import java.util.function.Consumer;

public class bMecAdv implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer){
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        DdiItems.SCULK_GEAR.get(),
                        Component.translatable("advancements.ddi.mec.root.title"),
                        Component.translatable("advancements.ddi.mec.root.desc"),
                        ResourceLocation.fromNamespaceAndPath("minecraft","textures/block/deepslate.png"),
                        AdvancementType.GOAL,
                        false,false,false
                )
                .addCriterion("crafted_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        DdiItems.SCULK_GEAR.get()
                ))
                .save(consumer, "ddi:mec/root");
    }
}
