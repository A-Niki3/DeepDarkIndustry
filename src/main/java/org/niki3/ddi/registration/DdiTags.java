package org.niki3.ddi.registration;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class DdiTags extends ItemTagsProvider {
    public static final TagKey<Item> VIBRATION_SILENCER = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("ddi","vibration_silencer"));


    public DdiTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(output, lookup, CompletableFuture.completedFuture(TagLookup.empty()), "ddi", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider){
        this.tag(VIBRATION_SILENCER).add(DdiItems.WOOL_BOOTS.get());
    }
}
