package com.rouesvm.emeraldtools.items;

import com.rouesvm.emeraldtools.Main;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.geysermc.geyser.api.item.custom.CustomItemOptions;
import org.geysermc.geyser.api.item.custom.NonVanillaCustomItemData;

public class GenericItem {

    private final Item moddedItem;

    private final String id;
    private final String toolTier;
    private final String toolType;

    public GenericItem(String id, String toolType, String toolTier) {
        Item.Settings settings = new Item.Settings();

        Item getItemType = switch (toolType) {
            case "pickaxe" -> new PickaxeItem(ToolMaterials.valueOf(toolTier.toUpperCase()), settings);
            case "shovel" -> new ShovelItem(ToolMaterials.valueOf(toolTier.toUpperCase()), settings);
            case "sword" -> new SwordItem(ToolMaterials.valueOf(toolTier.toUpperCase()), settings);
            case "axe" -> new AxeItem(ToolMaterials.valueOf(toolTier.toUpperCase()), settings);
            default -> new Item(settings);
        };

        this.id = id;
        this.toolType = toolType;
        this.toolTier = toolTier;
        this.moddedItem = Registry.register(Registries.ITEM, Identifier.of(Main.MOD_ID, id), getItemType);
    }

    public final NonVanillaCustomItemData getGeyserItem() {
        return NonVanillaCustomItemData.builder()
                .name(id)
                .identifier(Main.MOD_ID + ":" + id)
                .javaId(Registries.ITEM.getRawId(moddedItem))
                .creativeCategory(1)
                .toolTier(toolTier)
                .toolType(toolType)
                .build();
    }
}
