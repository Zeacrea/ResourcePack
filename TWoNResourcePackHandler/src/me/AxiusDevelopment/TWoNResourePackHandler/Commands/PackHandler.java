package me.AxiusDevelopment.TWoNResourePackHandler.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNResourePackHandler.TWoNResourcePackHandler;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class PackHandler implements CommandExecutor {

	private TWoNResourcePackHandler plugin;
	
	public PackHandler(TWoNResourcePackHandler main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] arg3) {
		if(sndr instanceof Player) {
			String resourcePack = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("messageBegin"));
			String resourcePack2 = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("messageEnd"));
			String hereText = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("hereText"));
			String hereHover = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("hereHover"));
			Player p = Bukkit.getPlayer(sndr.getName());
			String url = "";
			if(this.plugin.configData.containsKey("URL." + p.getWorld().getName())) {
				url = this.plugin.configData.get("URL." + p.getWorld().getName());
			}
			else
			{
				url = this.plugin.configData.get("URL.Default");
			} 
			TextComponent here = new TextComponent(hereText);
			here.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
			here.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hereHover).create()));
			TextComponent a = new TextComponent(resourcePack);
			a.addExtra(here);
			a.addExtra(resourcePack2);
			p.spigot().sendMessage(a);
			return true;
		}
		else
		{
			System.out.print("YOU MUST BE A PLAYER TO USE THE RESOURCE PACK!");
			return true;
		}
	}

}
