package org.niki3.ddi.creative;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.items.EchoLocatorItem;
import org.niki3.ddi.items.crystal_cutter;
import org.niki3.ddi.items.armors;
import org.niki3.ddi.items.wool_boots;

public class creative_add {
    // アイテムの登録用
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ddi.MODID);

    // アイテムのCreativeタブ追加
    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("ddi_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(creative_add.SCULK_GEAR.get());
        }
    };

    //アイテム登録コピペ用
    /*
    public static final RegistryObject<Item> NAME = ITEMS.register("item_name",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));

     */
    // アイテムの登録
    public static final RegistryObject<Item> CRYSTAL_CUTTER = ITEMS.register("crystal_cutter",
            () -> new crystal_cutter(Tiers.STONE,4,-1.6F,new Item.Properties().tab(CREATIVE_TAB).durability(500)));
    public static final RegistryObject<Item> SCULK_GEAR = ITEMS.register("sculk_gear",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> ECHO_LOCATOR = ITEMS.register("echo_locator",
            () -> new EchoLocatorItem(new Item.Properties().tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> WOOL_BOOTS = ITEMS.register("wool_boots",
            () -> new wool_boots(armors.WOOL, EquipmentSlot.FEET,new Item.Properties().tab(CREATIVE_TAB)));
}
