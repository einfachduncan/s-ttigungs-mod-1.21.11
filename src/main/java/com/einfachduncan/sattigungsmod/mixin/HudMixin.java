package com.einfachduncan.sattigungsmod.mixin;

import com.einfachduncan.sattigungsmod.client.SattigungsConfig;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InGameHud.class)
public class HudMixin {
	@ModifyArgs(method = "renderHotbarAndAttackIndicator", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderGuiItemDecorations(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V"))
	private void modifySaturation(Args args) {
		// Sättigungsfarbe wird hier angepasst
		// Dieser Hook wird für die Saturation Bar Farbe verwendet
	}

	@ModifyArgs(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"))
	private void modifyStatusBarColor(Args args) {
		// Hier kann die Farbe der Saturation Bar angepasst werden
		int color = SattigungsConfig.getInstance().getColorInt();
		args.set(4, color | 0xFF000000);
	}
}