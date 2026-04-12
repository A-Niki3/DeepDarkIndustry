package org.niki3.ddi;

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
