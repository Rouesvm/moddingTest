package com.rouesvm.emeraldtools.blocks;

import com.rouesvm.emeraldtools.Main;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.core.api.utils.PolymerClientDecoded;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.geysermc.geyser.api.block.custom.NonVanillaCustomBlockData;
import org.geysermc.geyser.api.block.custom.component.BoxComponent;
import org.geysermc.geyser.api.block.custom.component.CustomBlockComponents;
import org.geysermc.geyser.api.block.custom.component.GeometryComponent;
import org.geysermc.geyser.api.block.custom.nonvanilla.JavaBlockState;
import org.geysermc.geyser.api.block.custom.nonvanilla.JavaBoundingBox;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomBlocksEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.item.custom.NonVanillaCustomItemData;

public class GenericBlock extends GenericPolymerBlock {
    private final Identifier id;

    public GenericBlock(String id) {
        super(AbstractBlock.Settings.create(), id);
        this.id = Identifier.of(Main.MOD_ID, id.toLowerCase());;

        Registry.register(Registries.BLOCK, this.id, this);
        Registry.register(Registries.ITEM, this.id, new BlockItem(this, new Item.Settings()));
    }

    public static String convertBlockFormat(String input) {
        return input.replace("Block{", "").replace("}", "");
    }

    public void registerBlock(GeyserDefineCustomBlocksEvent event) {
        CustomBlockComponents components = CustomBlockComponents.builder()
                .collisionBox(BoxComponent.fullBox())
                .selectionBox(BoxComponent.fullBox())
                .geometry(GeometryComponent.builder().identifier("minecraft:geometry.full_block").build())
                .lightDampening(0)
                .lightEmission(0)
                .friction(1f)
                .build();
        NonVanillaCustomBlockData customBlockData = NonVanillaCustomBlockData.builder()
                .name(this.id.getPath())
                .namespace(this.id.getNamespace())
                .components(components)
                .build();

        String blockState = convertBlockFormat(getPolymerBlockState(this.getDefaultState()).toString());

        event.register(customBlockData);
        event.registerOverride(blockState, customBlockData.defaultBlockState());
    }

    public void registerItem(GeyserDefineCustomItemsEvent event) {
        NonVanillaCustomItemData customItemData = NonVanillaCustomItemData.builder()
                .name(this.id.getPath())
                .identifier(this.asItem().getTranslationKey())
                .javaId(Registries.ITEM.getRawIdOrThrow(this.asItem()))
                .creativeCategory(1)
                .build();

        event.register(customItemData);
    }
}