package de.papiertuch.utils.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Config {

    private File file;
    private Path path;
    private Configuration configuration;
    private HashMap<String, Object> cache;

    public Config(String config) {
        this.cache = new HashMap<>();
        this.file = new File("plugins/BanSystem");
        this.path = Paths.get("plugins/BanSystem/" + config);

        if (!file.exists()) file.mkdirs();
        try (InputStream localInputStream = Config.class.getClassLoader().getResourceAsStream(config)) {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(path.toFile());
            if (Files.exists(path)) return;
            Files.copy(localInputStream, this.path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getInt(String type) {
        if (this.cache.containsKey(type)) {
            return (int) this.cache.get(type);
        }
        return (int) this.cache.put(type, this.configuration.getInt(type));
    }

    public boolean getBoolean(String type) {
        if (this.cache.containsKey(type)) {
            return (boolean) this.cache.get(type);
        }
        return (boolean) this.cache.put(type, this.configuration.getBoolean(type));
    }

    public String getString(String type) {
        if (this.cache.containsKey(type)) {
            return (String) this.cache.get(type);
        }
        return (String) this.cache.put(type, this.configuration.getString(type)
                .replace("%prefix%", this.configuration.getString("messages.prefix")));
    }
}