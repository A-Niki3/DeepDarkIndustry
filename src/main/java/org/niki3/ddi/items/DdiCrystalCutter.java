package org.niki3.ddi.items;

import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class DdiCrystalCutter extends DiggerItem {
    public DdiCrystalCutter(Tier tier, float attackDamage, float attackSpeed , Properties properties) {

        super(tier, BlockTags.MINEABLE_WITH_HOE, properties.attributes(createAttributes(tier, attackDamage, attackSpeed)));
    }
    // クワ認識
    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, net.neoforged.neoforge.common.@NotNull ItemAbility itemAbility) {
        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
    }

    @Override
    public boolean isPrimaryItemFor(@NotNull ItemStack stack, @NotNull Holder<Enchantment> enchantment){
        if (enchantment.is(Enchantments.SILK_TOUCH)){
            return false;
        }
        return super.isPrimaryItemFor(stack, enchantment);
    }
}
