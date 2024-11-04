package org.niki3.ddi.blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.others.StorageBlockEntity;

public class addBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Ddi.MODID);
    /* sample
    public static final RegistryObject<BlockEntityType<entity_class>> NAME = BLOCK_ENTITIES.register("name",
            () -> BlockEntityType.Builder.of(entity_class::new,addBlock.NAME.get()).build(null);
     */


    // test
    public static final RegistryObject<BlockEntityType<StorageBlockEntity>> TEST_STORAGE = BLOCK_ENTITIES.register("test_storage",
            () -> BlockEntityType.Builder.of(StorageBlockEntity::new,addBlock.STORAGE_BLOCK.get()).build(null));
}
