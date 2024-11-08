package org.niki3.ddi;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.niki3.ddi.advancement.AdvProvider;
import org.niki3.ddi.blocks.addBlock;
import org.niki3.ddi.blocks.addBlockEntity;
import org.niki3.ddi.blocks.others.ModMenuTypes;
import org.niki3.ddi.blocks.others.StorageScreen;
import org.niki3.ddi.items.addItem;
import org.niki3.ddi.items.event.add_tags;
import org.niki3.ddi.server.PacketHandler;
import org.slf4j.Logger;

@Mod(Ddi.MODID)
public class Ddi {
    public static final String MODID = "ddi";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public Ddi() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        //items
        addItem.ITEMS.register(modEventBus);
        //blocks
        addBlock.BLOCKS.register(modEventBus);
        //block entity
        addBlockEntity.BLOCK_ENTITIES.register(modEventBus);
        //MenuType
        ModMenuTypes.MENUS.register(modEventBus);
        //なんか
        MinecraftForge.EVENT_BUS.register(this);
        //packet
        PacketHandler.registerPackets();
        PacketHandler.chestPackets();

    }

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // タグの生成プロバイダを追加
        generator.addProvider(event.includeServer(), new add_tags(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new AdvProvider(generator,existingFileHelper));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            MenuScreens.register(ModMenuTypes.STORAGE_CONTAINER.get(), StorageScreen::new);
        }
    }
}
