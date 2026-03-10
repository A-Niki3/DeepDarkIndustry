package org.niki3.ddi.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;

public class DdiCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Ddi.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DDI_TAB = TAB.register(
            "ddi_tab",
            () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.ddi"))
                            .icon(() -> new ItemStack(DdiItems.SCULK_GEAR.get()))
                            .build()
    );
}
