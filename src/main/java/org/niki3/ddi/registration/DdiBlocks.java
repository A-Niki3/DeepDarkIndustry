package org.niki3.ddi.registration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DdiBlocks{
    private DdiBlocks(){}

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("ddi");

    // 1.1.x
    public static final DeferredHolder<Block, Block> SIMPLE_MACHINE_FRAME = BLOCKS.register("simple_machine_block",
            () -> new Block(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f,6.0f)
                    .noOcclusion()
            ));
}