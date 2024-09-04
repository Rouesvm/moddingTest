package com.rouesvm.emeraldtools.blocks;

import com.rouesvm.emeraldtools.items.GenericItem;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomBlocksEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;

public class BlockList {
    public static final GenericBlock A_BLOCK = new GenericBlock("Block");

    public static void defineGeyserBlocks(GeyserDefineCustomBlocksEvent event) {
        event.register(A_BLOCK.getGeyserBlock());
    }

    public static void initialize() {}
}
