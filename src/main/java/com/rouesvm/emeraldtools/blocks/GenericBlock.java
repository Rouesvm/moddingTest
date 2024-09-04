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
import org.geysermc.geyser.api.block.custom.NonVanillaCustomBlockData;

public class GenericBlock implements PolymerBlock, PolymerKeepModel, PolymerClientDecoded {
    private final Block moddedBlock;
    private final String id;

    public GenericBlock(String id) {
        Block newBlock = new Block(AbstractBlock.Settings.create());
        Identifier identifier = Identifier.of(Main.MOD_ID, id.toLowerCase());

        BlockItem blockItem = new BlockItem(newBlock, new Item.Settings());
        Registry.register(Registries.ITEM, identifier, blockItem);

        this.id = id;
        this.moddedBlock = Registry.register(Registries.BLOCK, identifier, newBlock);
    }

    public NonVanillaCustomBlockData getGeyserBlock() {
        return NonVanillaCustomBlockData.builder()
                .name(id.toLowerCase())
                .namespace(Main.NEW_ID + id.toLowerCase())
                .build();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return this.moddedBlock.getDefaultState();
    }
}
