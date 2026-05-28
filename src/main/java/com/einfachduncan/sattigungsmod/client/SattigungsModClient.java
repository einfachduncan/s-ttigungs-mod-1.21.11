package com.einfachduncan.sattigungsmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class SattigungsModClient implements ClientModInitializer {
	public static KeyBinding openGuiKey;

	@Override
	public void onInitializeClient() {
		// Keybinding registrieren
		openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.sattigungsmod.open_gui",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_U,
			"category.sattigungsmod"
		));

		// Event Listener für Keybinding
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openGuiKey.wasPressed()) {
				if (client.player != null) {
					client.setScreen(new SattigungsConfigScreen(null));
				}
			}
		});
	}
}