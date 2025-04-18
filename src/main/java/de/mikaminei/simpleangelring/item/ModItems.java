package de.mikaminei.simpleangelring.item;

import de.mikaminei.simpleangelring.SimpleAngelRing;
import de.mikaminei.simpleangelring.item.custom.AngelRingItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item RING = registerItem("ring", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item ENERGY_CORE = registerItem("energy_core", new Item(new FabricItemSettings()));
    public static final Item ANGEL_RING = registerItem("angel_ring", new AngelRingItem(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(ENERGY_CORE);
    }

    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(RING);
        entries.add(ANGEL_RING);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleAngelRing.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleAngelRing.LOGGER.info("Registering Mod Items for " + SimpleAngelRing.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
    }
}
