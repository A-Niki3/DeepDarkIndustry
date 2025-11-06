package org.niki3.ddi.items.detail;

import com.google.common.base.Supplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.sounds.SoundEvents.WOOL_PLACE;

public enum armors implements ArmorMaterial {
    WOOL("wool", 5, new int[]{1, 2, 3, 1}, 10, WOOL_PLACE,
            0.0F, () -> Ingredient.of(Items.WHITE_WOOL));

    private static final int[] DURABILITY_PER_SLOT = new int[]{13, 15, 16, 11}; // それぞれブーツ、レギンス、チェストプレート、ヘルメットの耐久値
    private final String name;
    private final int durabilityMultiplier;
    private final int[] defenseForSlot;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final Lazy<Ingredient> repairIngredient;

    armors(String name, int durabilityMultiplier, int[] defenseForSlot, int enchantability,
           SoundEvent equipSound, float toughness, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.defenseForSlot = defenseForSlot;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.repairIngredient = Lazy.of(repairIngredient);
    }

    // 各メソッドはインターフェース armors の実装
    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return DURABILITY_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.defenseForSlot[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F; // ノックバック耐性（特定の防具のみ高い値を持つ）
    }

}
