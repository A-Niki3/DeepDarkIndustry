package org.niki3.ddi.advancement;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class AdvProvider extends AdvancementProvider {

    private final List<Consumer<Consumer<Advancement>>> tabs = ImmutableList.of(new aExpAdv(),new bMecAdv(),new cAdvAdv(),new dLosAdv());

    public AdvProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper);
    }

    @Override
    protected void registerAdvancements(@NotNull Consumer<Advancement> consumer, net.minecraftforge.common.data.@NotNull ExistingFileHelper existingFileHelper){
        for (Consumer<Consumer<Advancement>> con : this.tabs){
            con.accept(consumer);
        }
    }
}
