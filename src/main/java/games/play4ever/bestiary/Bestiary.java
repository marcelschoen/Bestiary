package games.play4ever.bestiary;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bestiary extends JavaPlugin implements CommandExecutor {

    private List<String> completions = new ArrayList<>();

    private final String CONFIG_FILENAME = "bestiary.properties";

    @Override
    public void onEnable() {
        // Plugin startup logic

        completions = new ArrayList<>(Arrays.asList("reload"));

        readConfig();
    }

    public static void logInfo(String message) {
        PluginLogger.getLogger(Bestiary.class.getName()).info("[Bestiary] " + message);
    }

    public static void logWarn(String message) {
        PluginLogger.getLogger(Bestiary.class.getName()).warning("[Bestiary] " + message);
    }

    public static void logError(String message) {
        PluginLogger.getLogger(Bestiary.class.getName()).severe("[Bestiary] " + message);
    }

    private void readConfig() {
        File targetDirectory = getDataFolder();
        if(!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }
        File placeholderConfig = new File(targetDirectory, CONFIG_FILENAME);
        if(!placeholderConfig.exists()) {
            File targetFile = new File(getDataFolder(), CONFIG_FILENAME);
            if(!targetFile.exists()) {
                getLogger().info("((Bestiary)) Creating Bestiary configuration file: " + CONFIG_FILENAME);
                saveResource(CONFIG_FILENAME, false);
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return completions;
    }

    @Override
    public void onDisable() {
    }

}
