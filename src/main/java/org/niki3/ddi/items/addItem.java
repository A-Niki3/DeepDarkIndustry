package org.niki3.ddi.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.addBlock;
import org.niki3.ddi.creative.creative_add;
import org.niki3.ddi.items.detail.EchoLocatorItem;
import org.niki3.ddi.items.detail.armors;
import org.niki3.ddi.items.detail.crystal_cutter;
import org.niki3.ddi.items.detail.wool_boots;

public class addItem {
    // アイテムの登録用
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Ddi.MODID);

    //アイテム登録コピペ用
    /*
    public static final DeferredItem<Item> NAME = ITEMS.registerSimpleItem("item_name",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> NAME_ITEM = ITEMS.registerSimpleBlockItem("name",
            () -> new BlockItem(addBlock.NAME.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));

     */
    // アイテムの登録
    //test
    public static final DeferredItem<Item> STORAGE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("test_storage",
            () -> new BlockItem(addBlock.STORAGE_BLOCK.get(),new Item.Properties()));

    // v1.0.x
    public static final DeferredItem<Item> CRYSTAL_CUTTER = ITEMS.registerSimpleItem("crystal_cutter",
            () -> new crystal_cutter(Tiers.STONE,4,-1.6F,new Item.Properties().tab(creative_add.CREATIVE_TAB).durability(500)));
    public static final DeferredItem<Item> SCULK_GEAR = ITEMS.registerSimpleItem("sculk_gear",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> ECHO_LOCATOR = ITEMS.registerSimpleItem("echo_locator",
            () -> new EchoLocatorItem(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> WOOL_BOOTS = ITEMS.registerSimpleItem("wool_boots",
            () -> new wool_boots(armors.WOOL, EquipmentSlot.FEET,new Item.Properties().tab(creative_add.CREATIVE_TAB)));

    // v1.1.x
    //items
    public static final DeferredItem<Item> SCULK_AMPLIFIER = ITEMS.registerSimpleItem("sculk_amplifier",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> SCULK_VIBRATOR = ITEMS.registerSimpleItem("sculk_vibrator",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> SCULK_CAPACITOR = ITEMS.registerSimpleItem("sculk_capacitor",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> EARLY_CHAINSAW = ITEMS.registerSimpleItem("early_chainsaw",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> EARLY_DRILL = ITEMS.registerSimpleItem("early_drill",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));

    // blocks
    public static final DeferredItem<Item> SIMPLE_MACHINE_FRAME_ITEM = ITEMS.registerSimpleBlockItem("simple_machine_frame",
            () -> new BlockItem(addBlock.SIMPLE_MACHINE_FRAME.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> THERMAL_GEN_ITEM = ITEMS.registerSimpleBlockItem("thermal_gen",
            () -> new BlockItem(addBlock.THERMAL_GEN.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> SIMPLE_SCULK_GEN_ITEM = ITEMS.registerSimpleBlockItem("simple_sculk_gen",
            () -> new BlockItem(addBlock.SIMPLE_SCULK_GEN.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> VIB_FURNACE_ITEM = ITEMS.registerSimpleBlockItem("vib_furnace",
            () -> new BlockItem(addBlock.VIB_FURNACE.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> VIB_CRUSHER_ITEM = ITEMS.registerSimpleBlockItem("vib_crusher",
            () -> new BlockItem(addBlock.VIB_CRUSHER.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final DeferredItem<Item> SCULK_BATTERY_ITEM = ITEMS.registerSimpleBlockItem("sculk_battery",
            () -> new BlockItem(addBlock.SCULK_BATTERY.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));

}
