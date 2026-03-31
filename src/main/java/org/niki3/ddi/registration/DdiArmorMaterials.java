package org.niki3.ddi.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;

import java.util.List;
import java.util.Map;

public class DdiArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Ddi.MODID);

    public static final Holder<ArmorMaterial> WOOL = ARMOR_MATERIALS.register(
            "wool",
            () -> new ArmorMaterial(
                    Map.of(
                            ArmorItem.Type.BOOTS, 1,
                            ArmorItem.Type.LEGGINGS, 2,
                            ArmorItem.Type.CHESTPLATE, 3,
                            ArmorItem.Type.HELMET, 1
                    ),
                    5,
                    BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.WOOL_PLACE),
                    () -> Ingredient.of(Items.WHITE_WOOL),
                    List.of(
                            new ArmorMaterial.Layer(
                                ResourceLocation.fromNamespaceAndPath("ddi","wool")
                            )
                    ),
                    0.0F,
                    0.0F
            )
    );

}
