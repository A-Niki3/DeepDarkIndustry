package org.niki3.ddi.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.niki3.ddi.creative.creative_add;

import java.util.function.Consumer;

public class aExpAdv implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> t){
        Advancement start = Advancement.Builder.advancement().display(
                creative_add.ECHO_LOCATOR.get(),
                Component.translatable("advancements.ddi.exp.root.title"),
                Component.translatable("advancements.ddi.exp.root.desc"),
                new ResourceLocation("minecraft:textures/block/blast_furnace_top.png"),
                FrameType.TASK,
                false,false,false
                )
                .addCriterion("in_overworld",
                        PlayerTrigger.TriggerInstance.located(LocationPredicate.inDimension(Level.OVERWORLD)))
                .save(t, "ddi:expage/root");
    }
}
