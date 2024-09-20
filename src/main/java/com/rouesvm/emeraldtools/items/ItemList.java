package com.rouesvm.emeraldtools.items;

import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;

public class ItemList {
    public static final GenericItem A_ITEM = new GenericItem("item");

    public static void defineGeyserItems(GeyserDefineCustomItemsEvent event) {
        A_ITEM.registerItem(event);
    }

    public static void initialize() {}
}
