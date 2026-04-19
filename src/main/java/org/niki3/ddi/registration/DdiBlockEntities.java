package org.niki3.ddi.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.ThermalGenerator.DdiThermalGeneratorBlockEntity;

public class DdiBlockEntities {
    DdiBlockEntities(){}

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Ddi.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DdiThermalGeneratorBlockEntity>> THERMAL_GENERATOR = BLOCK_ENTITIES.register(
            "thermal_generator",
            () -> BlockEntityType.Builder.of(
                    DdiThermalGeneratorBlockEntity::new,
                    DdiBlocks.THERMAL_GENERATOR.get()
            ).build(null)
    );
}
