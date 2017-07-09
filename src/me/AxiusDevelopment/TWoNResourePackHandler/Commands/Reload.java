package me.AxiusDevelopment.TWoNResourePackHandler.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.AxiusDevelopment.TWoNResourePackHandler.TWoNResourcePackHandler;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Config;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Messages;

public class Reload implements CommandExecutor {

	Messages messages;
	Config config;
	TWoNResourcePackHandler plugin;
	
	public Reload(Config config, Messages messages, TWoNResourcePackHandler main) {
		this.config = config;
		this.messages = messages;
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		String perm = this.config.configData.get("reloadPerm");
		String noPerm = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("noPermReload"));
		if(!sndr.hasPermission(perm)) {
			sndr.sendMessage(noPerm);
			return true;
		}
		else
		{
			if(args.length < 1) {
				String reloadSyntax = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("reloadSyntax"));
				sndr.sendMessage(reloadSyntax);
				return true;
			}
			else
			switch(args[0].toLowerCase()) {
			default:
				String reloadSyntax = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("reloadSyntax"));
				sndr.sendMessage(reloadSyntax);
				break;
			case "both":
				String bothmsg = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("reloadBoth"));
				messages.loadMessages();
				config.loadMessages();
				sndr.sendMessage(bothmsg);
				break;
				
			case "config":
				String configmsg = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("reloadConfig"));
				config.loadMessages();
				sndr.sendMessage(configmsg);
				break;
				
			case "messages":
				String messagesmsg = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("reloadMessages"));
				messages.loadMessages();
				sndr.sendMessage(messagesmsg);
				break;
			}
			return true;
		}
	}

}
