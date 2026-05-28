package com.einfachduncan.sattigungsmod.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class SattigungsConfigScreen extends Screen {
	private final Screen parent;
	private TextFieldWidget redField;
	private TextFieldWidget greenField;
	private TextFieldWidget blueField;
	private int currentRed = 255;
	private int currentGreen = 0;
	private int currentBlue = 0;

	public SattigungsConfigScreen(Screen parent) {
		super(Text.literal("Sättigungs Farbe Einstellungen"));
		this.parent = parent;
		this.currentRed = SattigungsConfig.getInstance().getRed();
		this.currentGreen = SattigungsConfig.getInstance().getGreen();
		this.currentBlue = SattigungsConfig.getInstance().getBlue();
	}

	@Override
	protected void init() {
		super.init();

		int centerX = this.width / 2;
		int centerY = this.height / 2;

		// Red Field
		this.redField = new TextFieldWidget(this.textRenderer, centerX - 150, centerY - 40, 100, 20, Text.literal("Red"));
		this.redField.setText(String.valueOf(this.currentRed));
		this.addRenderableWidget(this.redField);

		// Green Field
		this.greenField = new TextFieldWidget(this.textRenderer, centerX - 25, centerY - 40, 100, 20, Text.literal("Green"));
		this.greenField.setText(String.valueOf(this.currentGreen));
		this.addRenderableWidget(this.greenField);

		// Blue Field
		this.blueField = new TextFieldWidget(this.textRenderer, centerX + 100, centerY - 40, 100, 20, Text.literal("Blue"));
		this.blueField.setText(String.valueOf(this.currentBlue));
		this.addRenderableWidget(this.blueField);

		// Save Button
		this.addDrawableChild(ButtonWidget.builder(Text.literal("Speichern"), button -> {
			this.saveConfig();
			this.onClose();
		}).dimensions(centerX - 50, centerY + 20, 100, 20).build());

		// Cancel Button
		this.addDrawableChild(ButtonWidget.builder(Text.literal("Abbrechen"), button -> {
			this.onClose();
		}).dimensions(centerX - 50, centerY + 50, 100, 20).build());
	}

	private void saveConfig() {
		try {
			int red = Math.min(255, Math.max(0, Integer.parseInt(this.redField.getText())));
			int green = Math.min(255, Math.max(0, Integer.parseInt(this.greenField.getText())));
			int blue = Math.min(255, Math.max(0, Integer.parseInt(this.blueField.getText())));

			SattigungsConfig.getInstance().setColor(red, green, blue);
			SattigungsConfig.getInstance().save();
		} catch (NumberFormatException e) {
			// Invalid input, ignore
		}
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

		int centerX = this.width / 2;
		int centerY = this.height / 2;

		// Titel
		guiGraphics.drawCenteredString(this.textRenderer, this.title, centerX, centerY - 80, 0xFFFFFF);

		// Labels
		guiGraphics.drawString(this.textRenderer, "Rot:", centerX - 150, centerY - 55, 0xFFFFFF);
		guiGraphics.drawString(this.textRenderer, "Grün:", centerX - 25, centerY - 55, 0xFFFFFF);
		guiGraphics.drawString(this.textRenderer, "Blau:", centerX + 100, centerY - 55, 0xFFFFFF);

		// Farb-Vorschau
		int previewSize = 40;
		int previewX = centerX - previewSize / 2;
		int previewY = centerY + 80;
		int color = (this.currentRed << 16) | (this.currentGreen << 8) | this.currentBlue;
		guiGraphics.fill(previewX, previewY, previewX + previewSize, previewY + previewSize, color | 0xFF000000);

		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontal, double vertical) {
		return super.mouseScrolled(mouseX, mouseY, horizontal, vertical);
	}

	@Override
	public void close() {
		this.client.setScreen(this.parent);
	}
}