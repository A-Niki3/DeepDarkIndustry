package org.niki3.ddi.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
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

public class aExpAdv implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer){
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        DdiItems.SCULK_GEAR.get(),
                        Component.translatable("advancements.ddi.exp.root.title"),
                        Component.translatable("advancements.ddi.exp.root.desc"),
                        ResourceLocation.fromNamespaceAndPath("ddi","textures/item/sculk_gear.png"),
                        AdvancementType.TASK,
                        true,true,false
                )
                .addCriterion("in_overworld", PlayerTrigger.TriggerInstance.located(
                        LocationPredicate.Builder.inDimension(Level.OVERWORLD)
                ))
                .save(consumer, "ddi:root");
    }
}
