package org.niki3.ddi.registration;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;

public class DdiItems {
    private DdiItems(){}

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Ddi.MODID);

    // アイテム追加テンプレ
    // public static final DeferredRegister<Item, Item> ITEM_NAME = ITEMS.register("item_name",() -> new Item(new Item.Properties()));

    // 1.0.x アイテム
    public static final DeferredHolder<Item, Item> SCULK_GEAR = ITEMS.register("sculk_gear",() -> new Item(new Item.Properties()));

    // 1.1.x アイテム

}