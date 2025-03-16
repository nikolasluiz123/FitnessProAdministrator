package br.com.administrator.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	private static String CONFIG_FOLDER = "config/";
	private static String LOCAL_APPLICATION_FILE = "application_local.properties";
	private static String REMOTE_APPLICATION_FILE = "application_remote.properties";

	private static String ENVIRONMENT = "FITNESS_PRO_ADMINISTRATOR_ENV";
	private static String LOCAL_ENV = "local";
	private static String REMOTE_ENV = "remote";

	private Properties properties;

	public ConfigProperties() throws Exception {
		String env = System.getenv(ENVIRONMENT);

		if (StringUtils.isNullOrEmpty(env)) {
			throw new IllegalArgumentException("Invalid value for environment variable named " + ENVIRONMENT);
		}

		Properties properties = new Properties();
		InputStream input = null;

		if (env.equals(LOCAL_ENV)) {
			input = ConfigProperties.class.getClassLoader().getResourceAsStream(CONFIG_FOLDER + LOCAL_APPLICATION_FILE);
		} else if (env.equals(REMOTE_ENV)) {
			input = ConfigProperties.class.getClassLoader().getResourceAsStream(CONFIG_FOLDER + REMOTE_APPLICATION_FILE);
		} else {
			throw new IllegalArgumentException("Invalid value for environment variable named " + ENVIRONMENT);
		}

		properties.load(input);

		this.properties = properties;
	}

	public String getPropertyValue(String key) {
		return this.properties.getProperty(key);
	}

}
