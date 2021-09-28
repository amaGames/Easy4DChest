package com.amagames.easy4dchest.config;

import org.bukkit.configuration.ConfigurationSection;

import java.io.*;

public class ResourceLib {

	public static void set(String path, Object data, RewriteConfig configuration) {
		configuration.getConfiguration().set(configuration.getSectionName() + "." + path, data);
		configuration.saveConfiguration();
	}

	public static Object get(String path, RewriteConfig configuration) {
		return configuration.getConfiguration().get(configuration.getSectionName() + "." + path);
	}

	public static Object get(String path, RewriteConfig configuration, Object defaultObject) {
		Object result = get(path, configuration);
		if (result == null) {
			result = defaultObject;
		}
		return result;
	}

	public static ConfigurationSection getConfigurationSection(String path, RewriteConfig configuration) {
		return configuration.getConfiguration().getConfigurationSection(configuration.getSectionName() + path);
	}

	public static byte[] serializeObject(Serializable serializable) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(serializable);
			bytes = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			objectOutputStream.close();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		return bytes;
	}

	public static Serializable deserializeObject(byte[] bytes) {
		Serializable serializable = null;
		try {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			serializable = (Serializable) objectInputStream.readObject();
			byteArrayInputStream.close();
			objectInputStream.close();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		return serializable;
	}

	public static Serializable deserializeObject(byte[] bytes, Serializable defaultObject) {
		Serializable serializable = deserializeObject(bytes);
		if (serializable == null) {
			serializable = defaultObject;
		}
		return serializable;
	}

}
