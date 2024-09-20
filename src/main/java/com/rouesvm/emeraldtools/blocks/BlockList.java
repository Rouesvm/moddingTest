package com.rouesvm.emeraldtools.blocks;

import com.rouesvm.emeraldtools.items.GenericItem;
import org.geysermc.geyser.api.block.custom.NonVanillaCustomBlockData;
import org.geysermc.geyser.api.block.custom.nonvanilla.JavaBlockState;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomBlocksEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;

public class BlockList {
    public static final GenericBlock A_BLOCK = new GenericBlock("Block");

    public static void defineGeyserBlocks(GeyserDefineCustomBlocksEvent event) {
        A_BLOCK.registerBlock(event);
    }

    public static void defineGeyserBlockItems(GeyserDefineCustomItemsEvent event) {
        A_BLOCK.registerItem(event);
    }

    public static void initialize() {}
}
