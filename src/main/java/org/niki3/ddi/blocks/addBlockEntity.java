package org.niki3.ddi.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.others.StorageBlock;
import org.niki3.ddi.blocks.others.StorageBlockEntity;
import org.niki3.ddi.blocks.others.StorageContainer;
import org.niki3.ddi.creative.creative_add;

public class addBlockEntity {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ddi.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ddi.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Ddi.MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,Ddi.MODID);



    //ブロック
    // v1.1.x
    public static final RegistryObject<Block> STORAGE_BLOCK = BLOCKS.register("test_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0f,6.0f)));

    //ブロックエンティティ
    public static final RegistryObject<BlockEntityType<StorageBlockEntity>> STORAGE_BLOCK_ENTITY = BLOCK_ENTITIES.register("test_storage",
            () -> BlockEntityType.Builder.of(StorageBlockEntity::new, STORAGE_BLOCK.get()).build(null));

    //エンティティメニュー
    public static final RegistryObject<MenuType<StorageContainer>> TEST_STORAGE_CONTAINER = CONTAINERS.register("test_storage_container",
            () -> IForgeMenuType.create((id, inventory, data) -> {
                BlockPos pos = data.readBlockPos();
                Level level = inventory.player.level;
                return new StorageContainer(id,inventory,pos,level);
            }));


    //アイテム
    // v1.1.x
    public static final RegistryObject<Item> STORAGE_BLOCK_ITEM = ITEMS.register("test_storage",
            () -> new BlockItem(STORAGE_BLOCK.get(),new Item.Properties().tab(creative_add.CREATIVE_TAB)));
}
