package org.niki3.ddi;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.niki3.ddi.blocks.ThermalGenerator.DdiThermalGeneratorScreen;
import org.niki3.ddi.registration.DdiMenuScreen;

@Mod(value = Ddi.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Ddi.MODID, value = Dist.CLIENT)
public class DdiClient {
    public DdiClient(ModContainer container, IEventBus modEventBus) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event){
        event.register(
                DdiMenuScreen.THERMAL_GENERATOR.get(),
                DdiThermalGeneratorScreen::new
        );
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        Ddi.LOGGER.info("HELLO FROM CLIENT SETUP");
        Ddi.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
