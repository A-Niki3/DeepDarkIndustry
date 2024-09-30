package org.niki3.ddi.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.niki3.ddi.creative.creative_add;

public class wool_boots extends ArmorItem {
    public wool_boots(ArmorMaterial material,EquipmentSlot slot,Item.Properties properties){
        super(material,slot,properties);
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment){
        if(stack.getItem() == creative_add.WOOL_BOOTS.get()){
            return enchantment == Enchantments.UNBREAKING ||
                    enchantment == Enchantments.MENDING ||
                    enchantment == Enchantments.DEPTH_STRIDER ||
                    enchantment == Enchantments.FROST_WALKER ||
                    enchantment == Enchantments.ALL_DAMAGE_PROTECTION ||
                    enchantment == Enchantments.PROJECTILE_PROTECTION ||
                    enchantment == Enchantments.BLAST_PROTECTION ||
                    enchantment == Enchantments.FIRE_PROTECTION ||
                    enchantment == Enchantments.FALL_PROTECTION ||
                    enchantment == Enchantments.THORNS ||
                    enchantment == Enchantments.SOUL_SPEED ||
                    enchantment == Enchantments.BINDING_CURSE ||
                    enchantment == Enchantments.VANISHING_CURSE;
        }
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack){
        return stack.getItem() == creative_add.WOOL_BOOTS.get();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type){
        if (slot == EquipmentSlot.FEET){
            return "ddi:textures/models/armor/wool_layer_1.png";
        }
        else {
            return "ddi:textures/models/armor/wool_layer_2.png";
        }
    }
}
