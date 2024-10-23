package org.niki3.ddi.creative;

import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;
import org.niki3.ddi.items.*;

public class creative_add {
    // アイテムのCreativeタブ追加
    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("ddi_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(addItem.SCULK_GEAR.get());
        }
    };


}
