package me.AxiusDevelopment.TWoNResourcePackHandler.Events;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

	HashMap<String, String> cfg = new HashMap<String, String>();
	String update;
	String current;
	
	public JoinEvent(HashMap<String, String> configData, String string2, String string) {
		cfg = configData;
		update = string2;
		current = string;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission(cfg.get("viewUpdate"))) {
			p.sendMessage("§c§lResourcePack §7new version available: §c" + update + "§7. current version:§c " + current + "§7.");
		}
	}
	
}
