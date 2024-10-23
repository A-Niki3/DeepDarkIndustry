package org.niki3.ddi.items.event;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.niki3.ddi.items.addItem;

public class add_tags extends TagsProvider<Item> {
    public add_tags(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, Registry.ITEM,"ddi",existingFileHelper);
    }

    /*
    コピペ用
    public static final TagKey<Item> TAG_NAME = create_tag(true or false,"tag_name");
    */

    public static final TagKey<Item> VIBRATION_SILENCER = create_tag(false,"vibration_silencer");
    public static final TagKey<Item> FORGE_BOOTS = create_tag(true,"boots");
    public static final TagKey<Item> FORGE_ARMORS_BOOTS = create_tag(true,"armors/boots");
    public static final TagKey<Item> FORGE_TOOLS = create_tag(true,"tools");
    public static final TagKey<Item> FORGE_TOOLS_PICKAXE = create_tag(true,"tools/pickaxe");
    public static final TagKey<Item> DDI_TOOLS = create_tag(false,"tools");

    public static TagKey<Item> create_tag(boolean forge, String name){
        if (forge){
            return ItemTags.create(new ResourceLocation("forge",name));
        }
        else {
            return ItemTags.create(new ResourceLocation("ddi",name));
        }
    }

    @Override
    protected void addTags(){
        /*コピペ用
        this.tag(TAG_NAME)
                .add(item);
        */

        //wool boots
        this.tag(VIBRATION_SILENCER)
                .add(addItem.WOOL_BOOTS.get());
        this.tag(FORGE_BOOTS)
                .add(addItem.WOOL_BOOTS.get());
        this.tag(FORGE_ARMORS_BOOTS)
                .add(addItem.WOOL_BOOTS.get());

        //crystal cutter
        this.tag(FORGE_TOOLS)
                .add(addItem.CRYSTAL_CUTTER.get());
        this.tag(FORGE_TOOLS_PICKAXE)
                .add(addItem.CRYSTAL_CUTTER.get());
        this.tag(DDI_TOOLS)
                .add(addItem.CRYSTAL_CUTTER.get());
    }
}
