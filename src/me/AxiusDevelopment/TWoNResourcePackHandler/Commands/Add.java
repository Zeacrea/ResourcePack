package me.AxiusDevelopment.TWoNResourcePackHandler.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNResourcePackHandler.TWoNResourcePackHandler;
import me.AxiusDevelopment.TWoNResourcePackHandler.YAMLHandlers.Config;
import me.AxiusDevelopment.TWoNResourcePackHandler.YAMLHandlers.Messages;

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
				if(args[1].contains(".")) {
					config.addWorld((Player) sndr, args[0], args[1]);
					sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("addAdded")));
					return true;
				}
				else
				{
					sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("addError")));
					System.out.print(String.format("[%s] Error when adding URL, Link definined is not a URL and thus wouldn't work properly!", main.getDescription().getName()));
					return true;
				}
			}
		}
	}

}
