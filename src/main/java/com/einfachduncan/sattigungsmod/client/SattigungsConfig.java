package com.einfachduncan.sattigungsmod.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SattigungsConfig {
	private static SattigungsConfig instance;
	private int red = 255;
	private int green = 0;
	private int blue = 0;
	private final Path configPath;

	private SattigungsConfig() {
		this.configPath = Paths.get("config/sattigungsmod.conf");
		this.load();
	}

	public static SattigungsConfig getInstance() {
		if (instance == null) {
			instance = new SattigungsConfig();
		}
		return instance;
	}

	public void load() {
		try {
			if (Files.exists(this.configPath)) {
				String content = Files.readString(this.configPath);
				String[] lines = content.split("\n");
				for (String line : lines) {
					if (line.startsWith("red=")) {
						this.red = Integer.parseInt(line.substring(4));
					} else if (line.startsWith("green=")) {
						this.green = Integer.parseInt(line.substring(6));
					} else if (line.startsWith("blue=")) {
						this.blue = Integer.parseInt(line.substring(5));
					}
				}
			} else {
				this.save();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			Files.createDirectories(this.configPath.getParent());
			String content = String.format("red=%d\ngreen=%d\nblue=%d\n", this.red, this.green, this.blue);
			Files.writeString(this.configPath, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return this.red;
	}

	public int getGreen() {
		return this.green;
	}

	public int getBlue() {
		return this.blue;
	}

	public int getColorInt() {
		return (this.red << 16) | (this.green << 8) | this.blue;
	}
}