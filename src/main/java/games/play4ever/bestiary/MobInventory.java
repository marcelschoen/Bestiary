package games.play4ever.bestiary;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MobInventory {

    public void create() {
        Player player = Bukkit.getPlayer("gaga");
        Inventory inventory = Bukkit.createInventory(player, 27, "Mobs");

//        inventory.setStorageContents();
    }
}
