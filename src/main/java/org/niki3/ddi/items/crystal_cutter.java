package org.niki3.ddi.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.niki3.ddi.creative.creative_add;

public class crystal_cutter extends PickaxeItem {
    public crystal_cutter(Tier tier, int attackDamadeModifier, float attackSpeedModifier, Properties properties){
        super(tier,attackDamadeModifier,attackSpeedModifier,properties);
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment){
        if(stack.getItem() == creative_add.CRYSTAL_CUTTER.get()){
            return enchantment == Enchantments.UNBREAKING ||
                    enchantment == Enchantments.MENDING ||
                    enchantment == Enchantments.BLOCK_EFFICIENCY ||
                    enchantment == Enchantments.SHARPNESS;
        }
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack){
        return stack.getItem() == creative_add.CRYSTAL_CUTTER.get();
    }
}
