package org.niki3.ddi.registration;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class DdiCapabilities {
    private DdiCapabilities(){}

    public static void registerCapabilities(RegisterCapabilitiesEvent event){
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                DdiBlockEntities.THERMAL_GENERATOR.get(),
                (be, side) -> be.getEnergyStorage()
        );

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                DdiBlockEntities.THERMAL_GENERATOR.get(),
                (be, side) -> be.getInventory()
        );

        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                DdiBlockEntities.VIBRATION_FURNACE.get(),
                (be, side) -> be.getEnergyStorage()
        );

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                DdiBlockEntities.VIBRATION_FURNACE.get(),
                (be, side) -> be.getInventory()
        );
    }
}
