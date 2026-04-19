package org.niki3.ddi.items.Providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiItems;

import java.util.concurrent.CompletableFuture;

public class DdiTags extends ItemTagsProvider {
    public static final TagKey<Item> VIBRATION_SILENCER = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("ddi","vibration_silencer"));


    public DdiTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper existingFileHelper){
        super(output, lookup, CompletableFuture.completedFuture(TagLookup.empty()), "ddi", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider){
        /*
        テンプレ
        this.tag().add();
        */

        //装備タグ
        this.tag(VIBRATION_SILENCER).add(DdiItems.WOOL_BOOTS.get());
        this.tag(ItemTags.FOOT_ARMOR).add(DdiItems.WOOL_BOOTS.get());
        this.tag(ItemTags.ARROWS).add(DdiItems.WOOL_BOOTS.get());
        //エンチャントタグ
        //耐久系
        this.tag(ItemTags.DURABILITY_ENCHANTABLE).add(
                DdiItems.WOOL_BOOTS.get(),
                DdiItems.CRYSTAL_CUTTER.get()
        );
        this.tag(ItemTags.VANISHING_ENCHANTABLE).add(
                DdiItems.WOOL_BOOTS.get(),
                DdiItems.CRYSTAL_CUTTER.get()
        );
        //防具系
        this.tag(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(
                DdiItems.WOOL_BOOTS.get()
        );
        this.tag(ItemTags.ARMOR_ENCHANTABLE).add(
                DdiItems.WOOL_BOOTS.get()
        );
        this.tag(ItemTags.EQUIPPABLE_ENCHANTABLE).add(
                DdiItems.WOOL_BOOTS.get()
        );
        //ツール系
        this.tag(ItemTags.MINING_ENCHANTABLE).add(
                DdiItems.CRYSTAL_CUTTER.get()
        );
        this.tag(ItemTags.MINING_LOOT_ENCHANTABLE).add(
                DdiItems.CRYSTAL_CUTTER.get()
        );
        this.tag(ItemTags.SWORD_ENCHANTABLE).add(
                DdiItems.CRYSTAL_CUTTER.get()
        );
        this.tag(ItemTags.WEAPON_ENCHANTABLE).add(
                DdiItems.CRYSTAL_CUTTER.get()
        );
        this.tag(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(
                DdiItems.CRYSTAL_CUTTER.get()
        );
    }
}
