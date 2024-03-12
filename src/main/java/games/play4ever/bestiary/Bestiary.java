package games.play4ever.bestiary;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
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

    public static void doStuff() {
        Player player = null;

        // Create book
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) is.getItemMeta();
        bm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lWelcome to the server!"));
        bm.addPage("Hey, you just opened a book\nAs you can see there are 2 lines in this book");
        bm.addPage("Welocome to the second page\n \nThis is on the third line.");
        bm.setAuthor("My Server");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("This book contains " + bm.getPageCount() + " pages.");
        bm.setLore(lore);
        is.setItemMeta(bm);

        // Give book to player
        player.getInventory().addItem(is);

        // Open book / 1.15+
        //        player.openBook(ItemStack book);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("book")) {
            Player p = (Player) sender;
//            CustomItem.giveItem(p);
        }
        return true;
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
