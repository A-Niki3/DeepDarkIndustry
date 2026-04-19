package org.niki3.ddi.blocks.Providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.registration.DdiBlocks;

import java.util.concurrent.CompletableFuture;

public class DdiBlockTagProvider extends BlockTagsProvider {
    public DdiBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper existingFileHelper){
        super(output, lookup, Ddi.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider){
        // ピッケル採掘
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DdiBlocks.SIMPLE_MACHINE_FRAME.get());

        // 石ツール
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(DdiBlocks.SIMPLE_MACHINE_FRAME.get());
    }
}
