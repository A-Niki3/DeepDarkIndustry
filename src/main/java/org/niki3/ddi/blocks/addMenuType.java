package org.niki3.ddi.blocks;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.niki3.ddi.Ddi;
import org.niki3.ddi.blocks.others.StorageContainer;

public class addMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Ddi.MODID);

    public static final RegistryObject<MenuType<StorageContainer>> STORAGE_CONTAINER = registryMenuType(
            StorageContainer::new,"storage_container");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registryMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name,() -> IForgeMenuType.create(factory));
    }
}
