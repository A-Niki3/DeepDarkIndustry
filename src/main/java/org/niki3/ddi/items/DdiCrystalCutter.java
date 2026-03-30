package org.niki3.ddi.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
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

}
