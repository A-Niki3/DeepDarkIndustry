package org.niki3.ddi.registration;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.ThermalGenerator.DdiThermalGeneratorMenu;
import org.niki3.ddi.blocks.ThermalGenerator.DdiThermalGeneratorScreen;

public class DdiMenuScreen {
    DdiMenuScreen(){}

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Ddi.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<DdiThermalGeneratorMenu>> THERMAL_GENERATOR = MENUS.register(
            "thermal_generator", () -> IMenuTypeExtension.create(DdiThermalGeneratorMenu::new)
    );
}
