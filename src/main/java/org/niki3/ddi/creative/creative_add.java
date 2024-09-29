package org.niki3.ddi.creative;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.niki3.ddi.Ddi;

public class creative_add {
    // アイテムの登録用
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ddi.MODID);

    // アイテムのCreativeタブ追加
    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("ddi_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(creative_add.SCULK_GEAR.get()); // タブのアイコンをダイヤモンドに設定
        }
    };

    //アイテム登録コピペ用
    /*
    public static final RegistryObject<Item> NAME = ITEMS.register("item_name",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));

     */
    // アイテムの登録
    public static final RegistryObject<Item> CRYSTAL_CUTTER = ITEMS.register("crystal_cutter",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SCULK_GEAR = ITEMS.register("sculk_gear",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> ECHO_LOCATOR = ITEMS.register("echo_locator",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> WOOL_BOOTS = ITEMS.register("wool_boots",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));
}
