package com.rouesvm.emeraldtools;

import com.rouesvm.emeraldtools.items.Itemlist;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.event.EventRegistrar;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;

public class Main implements ModInitializer, EventRegistrar {

	public static final String MOD_ID = "emeraldtools";

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTING.register((server) -> {
			GeyserApi.api().eventBus().register(this, this);
		});

		Itemlist.initialize();
	}

	@Subscribe
	public void onGeyserDefineCustomItems(GeyserDefineCustomItemsEvent event) {
		Itemlist.defineGeyserItems(event);
	}
}