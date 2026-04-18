package org.niki3.ddi;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.item.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.niki3.ddi.advancement.AdvProvider;
import org.niki3.ddi.blocks.DdiBlockLootProvider;
import org.niki3.ddi.blocks.DdiBlockTagProvider;
import org.niki3.ddi.registration.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Ddi.MODID)
public class Ddi {
    public static final String MODID = "ddi";
    public static final String MOD_NAME = "DeepDarkIndustry";
    public static final String LOG_TAG = '[' + MOD_NAME + ']';
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final GameProfile gameProfile = new GameProfile(UUID.nameUUIDFromBytes("Ddi".getBytes(StandardCharsets.UTF_8)),LOG_TAG);

    public Ddi(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

        addRegistrationListeners(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void addRegistrationListeners(IEventBus modEventBus){
        DdiArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        DdiItems.ITEMS.register(modEventBus);
        DdiBlocks.BLOCKS.register(modEventBus);
        DdiCreativeTab.TAB.register(modEventBus);
    }

    private void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new DdiTags(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new AdvProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new DdiBlockTagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(
                        DdiBlockLootProvider::new,
                        LootContextParamSets.BLOCK
                )
            ),
                lookupProvider
        ));
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("DDI COMMON SETUP");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == DdiCreativeTab.DDI_TAB.getKey()) {
            event.acceptAll(
                    DdiItems.ITEMS.getEntries()
                            .stream()
                            .map(holder -> new ItemStack(holder.get()))
                            .toList()
            );
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("DDI from server starting");
    }
}
