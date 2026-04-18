package org.niki3.ddi.blocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.registration.DdiBlocks;

import java.util.List;
import java.util.Set;

public class DdiBlockLootProvider extends BlockLootSubProvider {
    public DdiBlockLootProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        this.dropSelf(
                DdiBlocks.SIMPLE_MACHINE_FRAME.get()
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return List.of(
                DdiBlocks.SIMPLE_MACHINE_FRAME.get()
        );
    }
}
