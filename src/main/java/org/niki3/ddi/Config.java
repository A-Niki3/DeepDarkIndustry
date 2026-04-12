package org.niki3.ddi;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue ECHO_RADIUS = BUILDER
            .comment("Echo Loactor search radius")
            .defineInRange("Radius",2000, 500, 10000);
    public static final ModConfigSpec.IntValue ECHO_HORIZONTAL_STEP = BUILDER
            .comment("Echo Locator horizontal step")
            .defineInRange("echoHorizontalStep", 32, 8, 256);

    public static final ModConfigSpec.IntValue ECHO_VERTICAL_STEP = BUILDER
            .comment("Echo Locator vertical step")
            .defineInRange("echoVerticalStep", 20, 4, 128);

    static final ModConfigSpec SPEC = BUILDER.build();
}
