package com.rouesvm.emeraldtools.items;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;

public class Itemlist {
    public static final GenericItem A_ITEM = new GenericItem("item", "pickaxe", "diamond");

    public static void defineGeyserItems(GeyserDefineCustomItemsEvent event) {
        event.register(A_ITEM.getGeyserItem());
    }

    public static void initialize() {
    }
}
