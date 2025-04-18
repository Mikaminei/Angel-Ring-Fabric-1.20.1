package de.mikaminei.simpleangelring;

import de.mikaminei.simpleangelring.event.PlayerTickHandler;
import de.mikaminei.simpleangelring.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAngelRing implements ModInitializer {
	public static final String MOD_ID = "simpleangelring";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		PlayerTickHandler.register();
	}
}