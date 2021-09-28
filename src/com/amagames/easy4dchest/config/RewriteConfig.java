package com.amagames.easy4dchest.config;

import com.amagames.easy4dchest.Easy4DChest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RewriteConfig {

	private FileConfiguration fileConfiguration;
	private File file;
	private String name;
	private String sectionName;

	public void initializeInstance(String name, String sectionName) {
		this.name = name;
		this.sectionName = sectionName;
		this.file = new File(Easy4DChest.getInstance().getDataFolder(), this.name);
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void reloadConfiguration() {
		this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
		InputStream inputStream = Easy4DChest.getInstance().getResource(this.name);
		if (!(inputStream == null)) {
			this.fileConfiguration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream, StandardCharsets.UTF_8)));
		}
	}

	public FileConfiguration getConfiguration() {
		if (this.fileConfiguration == null) {
			this.reloadConfiguration();
		}
		return this.fileConfiguration;
	}

	public void saveConfiguration() {
		if (!(this.fileConfiguration == null)) {
			try {
				this.getConfiguration().save(this.file);
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

}
