package com.rouesvm.emeraldtools.items;

import com.rouesvm.emeraldtools.Main;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.utils.PolymerClientDecoded;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.*;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.item.custom.NonVanillaCustomItemData;
import org.jetbrains.annotations.Nullable;

public class GenericItem implements PolymerItem, PolymerKeepModel, PolymerClientDecoded {

    private final Item moddedItem;
    private final Identifier id;

    private final PolymerModelData model;

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.model.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.model.value();
    }

    public GenericItem(String id) {
        Item.Settings settings = new Item.Settings();

        this.id = Identifier.of(Main.MOD_ID, id);
        this.moddedItem = Registry.register(Registries.ITEM, this.id, new Item(settings));

        this.model = PolymerResourcePackUtils.requestModel(Items.LEAD, Identifier.of(Main.MOD_ID, "item/" + this.id.getPath()));
    }

    public void registerItem(GeyserDefineCustomItemsEvent event) {
        NonVanillaCustomItemData customItemData = NonVanillaCustomItemData.builder()
                .name(this.id.getPath())
                .identifier(this.id.getNamespace())
                .javaId(Registries.ITEM.getRawId(this.moddedItem))
                .creativeCategory(1)
                .build();

        event.register(customItemData);
    }
}

