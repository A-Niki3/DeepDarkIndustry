package org.niki3.ddi.registration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.blocks.DdiThermalGenerator;

public class DdiBlocks{
    private DdiBlocks(){}

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("ddi");

    // 1.1.x
    public static final DeferredHolder<Block, Block> SIMPLE_MACHINE_FRAME = BLOCKS.register("simple_machine_frame",
            () -> new Block(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .strength(1.5f,6.0f)
                    .noOcclusion()
            ));

    public static final DeferredHolder<Block, Block> THERMAL_GENERATOR = BLOCKS.register("thermal_generator",
            () -> new DdiThermalGenerator(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .strength(1.5f,6.0f)
            ));
}