package org.niki3.ddi.registration;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.items.DdiCrystalCutter;
import org.niki3.ddi.items.DdiEchoLocator;
import org.niki3.ddi.items.DdiWoolBoots;

public class DdiItems {
    private DdiItems(){}

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Ddi.MODID);

    // アイテム追加テンプレ
    // public static final DeferredRegister<Item, Item> ITEM_NAME = ITEMS.register("item_name",() -> new Item(new Item.Properties()));

    // テストアイテム追加

    // 1.0.x アイテム
    public static final DeferredHolder<Item, Item> SCULK_GEAR = ITEMS.register("sculk_gear",() -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CRYSTAL_CUTTER = ITEMS.register("crystal_cutter",() -> new DdiCrystalCutter(
            Tiers.IRON,1.0F,1.0F,new Item.Properties().durability(1024)
    ));
    //public static final DeferredHolder<Item, Item> ECHO_LOCATOR = ITEMS.register("echo_locator",() -> new DdiEchoLocator());
    //public static final DeferredHolder<Item, Item> WOOL_BOOTS = ITEMS.register("wool_boots",() -> new DdiWoolBoots());

    // 1.1.x アイテム

}