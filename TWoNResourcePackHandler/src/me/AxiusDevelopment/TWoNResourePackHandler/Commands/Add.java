package me.AxiusDevelopment.TWoNResourePackHandler.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNResourePackHandler.TWoNResourcePackHandler;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Config;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Messages;

public class Add implements CommandExecutor {

	Config config;
	Messages messages;
	TWoNResourcePackHandler main;
	
	public Add(Config config, Messages messages, TWoNResourcePackHandler main) {
		this.config = config;
		this.messages = messages;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		if(!sndr.hasPermission(main.configData.get("addPerm"))) {
			sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("noPermAdd")));
			return true;
		}
		else
		{
			if(args.length < 2) {
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("addSyntax")));
				return true;
			}
			else
			{
				
				config.addWorld((Player) sndr, args[0], args[1]);
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("addAdded")));
				return true;
			}
		}
	}

}
