package com.dropbox.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static com.dropbox.config.Constants.PROP_FILE_NAME;

public class Config {

    static Properties props;

    static {
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME)) {
            if (inputStream != null) {
                props = new Properties();
                props.load(inputStream);
            }
            throw new FileNotFoundException(String.format("Property file %s not found", PROP_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key) {
        return props.getProperty(key);
    }

    public static String getString(String key, String defaultValue) {
        return Optional.ofNullable(props.getProperty(key))
                .orElse(defaultValue);
    }

    public static Integer getInt(String key) {
        return Integer.valueOf(props.getProperty(key));
    }
}

class Constants {
    public static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());
    public static final String PROP_FILE_NAME = "config.properties";
}