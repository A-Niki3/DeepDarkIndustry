package org.niki3.ddi.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.addBlock;
import org.niki3.ddi.creative.creative_add;
import org.niki3.ddi.items.detail.EchoLocatorItem;
import org.niki3.ddi.items.detail.armors;
import org.niki3.ddi.items.detail.crystal_cutter;
import org.niki3.ddi.items.detail.wool_boots;

public class addItem {
    // アイテムの登録用
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ddi.MODID);

    //アイテム登録コピペ用
    /*
    public static final RegistryObject<Item> NAME = ITEMS.register("item_name",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));

     */
    // アイテムの登録
    //test
    public static final RegistryObject<Item> STORAGE_BLOCK_ITEM = ITEMS.register("test_storage",
            () -> new BlockItem(addBlock.STORAGE_BLOCK.get(),new Item.Properties().tab(creative_add.CREATIVE_TAB)));

    // v1.0.x
    public static final RegistryObject<Item> CRYSTAL_CUTTER = ITEMS.register("crystal_cutter",
            () -> new crystal_cutter(Tiers.STONE,4,-1.6F,new Item.Properties().tab(creative_add.CREATIVE_TAB).durability(500)));
    public static final RegistryObject<Item> SCULK_GEAR = ITEMS.register("sculk_gear",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final RegistryObject<Item> ECHO_LOCATOR = ITEMS.register("echo_locator",
            () -> new EchoLocatorItem(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final RegistryObject<Item> WOOL_BOOTS = ITEMS.register("wool_boots",
            () -> new wool_boots(armors.WOOL, EquipmentSlot.FEET,new Item.Properties().tab(creative_add.CREATIVE_TAB)));

    // v1.1.x
    public static final RegistryObject<Item> SCULK_AMPLIFIER = ITEMS.register("sculk_amplifier",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final RegistryObject<Item> SCULK_VIBRATOR = ITEMS.register("sculk_vibrator",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final RegistryObject<Item> SCULK_CAPACITOR = ITEMS.register("sculk_capacitor",
            () -> new Item(new Item.Properties().tab(creative_add.CREATIVE_TAB)));
    public static final RegistryObject<Item> SIMPLE_MACHINE_FRAME_ITEM = ITEMS.register("simple_machine_frame",
            () -> new BlockItem(addBlock.SIMPLE_MACHINE_FRAME.get(), new Item.Properties().tab(creative_add.CREATIVE_TAB)));
}
