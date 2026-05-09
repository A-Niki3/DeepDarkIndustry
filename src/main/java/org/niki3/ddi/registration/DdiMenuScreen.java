package org.niki3.ddi.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.ThermalGenerator.DdiThermalGeneratorMenu;
import org.niki3.ddi.blocks.VibrationFurnace.DdiVibrationFurnaceMenu;

public class DdiMenuScreen {
    DdiMenuScreen(){}

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Ddi.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<DdiThermalGeneratorMenu>> THERMAL_GENERATOR = MENUS.register(
            "thermal_generator", () -> IMenuTypeExtension.create(DdiThermalGeneratorMenu::new)
    );
    public static final DeferredHolder<MenuType<?>, MenuType<DdiVibrationFurnaceMenu>> VIBRATION_FURNACE = MENUS.register(
            "vibration_furnace", () -> IMenuTypeExtension.create(DdiVibrationFurnaceMenu::new)
    );
}
