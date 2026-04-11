package org.niki3.ddi.advancement;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AdvProvider extends AdvancementProvider {

    public AdvProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper){
        super(output, registries, existingFileHelper, List.of(
                (provider, consumer, helper) -> new aExpAdv().generate(provider, consumer),
                (provider, consumer, helper) -> new bMecAdv().generate(provider, consumer)
        ));
    }
}
