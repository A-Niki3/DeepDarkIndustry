package org.niki3.ddi.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.others.StorageBlock;
import org.niki3.ddi.creative.creative_add;

public class addBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ddi.MODID);

    //test
    public static final RegistryObject<Block> STORAGE_BLOCK = BLOCKS.register("test_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0f,6.0f)));

    // v1.1.x
    public static final RegistryObject<Block> SIMPLE_MACHINE_FRAME = BLOCKS.register("simple_machine_frame",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .strength(1.5f,6.0f)
                    .noOcclusion()
            ));

    //アイテムとしてのブロック
    // 1.1.x

}
