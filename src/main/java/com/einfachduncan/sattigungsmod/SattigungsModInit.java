package com.einfachduncan.sattigungsmod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SattigungsModInit implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sattigungsmod");

	@Override
	public void onInitialize() {
		LOGGER.info("Sättigungs Mod initialisiert!");
	}
}