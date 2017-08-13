package me.AxiusDevelopment.TWoNResourcePackHandler.Commands;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;

import me.AxiusDevelopment.TWoNResourcePackHandler.TWoNResourcePackHandler;
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
			Player p = Bukkit.getPlayer(sndr.getName());
			if(this.plugin.configData.get("Method").equalsIgnoreCase("CHAT")) {
				String resourcePack = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("messageBegin"));
				String resourcePack2 = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("messageEnd"));
				String hereText = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("hereText"));
				String hereHover = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("hereHover"));
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
			}
			else
			{
				if(this.plugin.configData.get("Method").equalsIgnoreCase("PROMPT")) {
					String url = "";
					if(this.plugin.configData.containsKey("URL." + p.getWorld().getName())) {
						url = this.plugin.configData.get("URL." + p.getWorld().getName());
					}
					else
					{
						url = this.plugin.configData.get("URL.Default");
					} 
					
					String hash = null;
					
					PacketContainer packet = new PacketContainer(PacketType.Play.Server.RESOURCE_PACK_SEND);
			        packet.getStrings().write(0, url);
			        packet.getStrings().write(1, hash);
			        try {
			            ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
			        } catch (InvocationTargetException e) {
			            e.printStackTrace();
			        }
			        
			        return true;
				}
				else
				{
					System.out.print("The \"Method\" field in the Config.YML doesn't have an applicable method type, types: PROMPT, CHAT");
					System.out.print("Using default \"CHAT\" Method.");
					
					String resourcePack = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("messageBegin"));
					String resourcePack2 = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("messageEnd"));
					String hereText = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("hereText"));
					String hereHover = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("hereHover"));
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
					
				}
			}
			
			return true;
		}
		else
		{
			System.out.print("YOU MUST BE A PLAYER TO USE THE RESOURCE PACK!");
			return true;
		}
	}

}
