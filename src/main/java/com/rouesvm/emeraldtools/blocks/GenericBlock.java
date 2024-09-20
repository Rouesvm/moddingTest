package com.rouesvm.emeraldtools.blocks;

import com.rouesvm.emeraldtools.Main;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import eu.pb4.polymer.core.api.utils.PolymerClientDecoded;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.geysermc.geyser.api.block.custom.CustomBlockData;
import org.geysermc.geyser.api.block.custom.CustomBlockState;
import org.geysermc.geyser.api.block.custom.NonVanillaCustomBlockData;
import org.geysermc.geyser.api.block.custom.component.BoxComponent;
import org.geysermc.geyser.api.block.custom.component.CustomBlockComponents;
import org.geysermc.geyser.api.block.custom.component.GeometryComponent;
import org.geysermc.geyser.api.block.custom.component.MaterialInstance;
import org.geysermc.geyser.api.block.custom.nonvanilla.JavaBlockState;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomBlocksEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.item.custom.NonVanillaCustomItemData;
import org.geysermc.geyser.api.util.CreativeCategory;

public class GenericBlock implements PolymerBlock, PolymerKeepModel, PolymerClientDecoded {
    private final Block block;
    private final BlockItem blockItem;

    private final Identifier id;

    public GenericBlock(String id) {
        this.id = Identifier.of(Main.MOD_ID, id.toLowerCase());;

        this.block = Registry.register(Registries.BLOCK, this.id, new Block(AbstractBlock.Settings.create()));
        this.blockItem = Registry.register(Registries.ITEM, this.id, new BlockItem(this.block, new Item.Settings()));
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

        JavaBlockState javaBlockState = JavaBlockState.builder()
                .javaId(Block.getRawIdFromState(this.block.getDefaultState()))
                .identifier(this.id.toString())
                .pickItem(this.id.toString())
                .stateGroupId(Registries.BLOCK.getRawId(this.block))
                .build();

        event.register(customBlockData);
        event.registerOverride(javaBlockState, customBlockData.defaultBlockState());
    }

    public void registerItem(GeyserDefineCustomItemsEvent event) {
        NonVanillaCustomItemData customItemData = NonVanillaCustomItemData.builder()
                .name(this.id.getPath())
                .identifier(this.id.toString())
                .javaId(Registries.ITEM.getRawIdOrThrow(this.blockItem))
                .creativeCategory(1)
                .build();

        event.register(customItemData);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return this.block.getDefaultState();
    }
}
