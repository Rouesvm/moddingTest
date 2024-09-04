package com.rouesvm.emeraldtools;

import com.rouesvm.emeraldtools.blocks.BlockList;
import com.rouesvm.emeraldtools.items.ItemList;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.event.EventRegistrar;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomBlocksEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;

public class Main implements ModInitializer, EventRegistrar {

	public static final String MOD_ID = "emeraldtools";
	public static final String NEW_ID = MOD_ID + ":";

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTING.register((server) -> {
			GeyserApi.api().eventBus().register(this, this);
		});

		ItemList.initialize();
		BlockList.initialize();
	}

	@Subscribe
	public void onGeyserDefineCustomItems(GeyserDefineCustomItemsEvent event) {
		ItemList.defineGeyserItems(event);
	}

	@Subscribe
	public void onGeyserDefineCustomBlocks(GeyserDefineCustomBlocksEvent event) {
		BlockList.defineGeyserBlocks(event);
	}
}