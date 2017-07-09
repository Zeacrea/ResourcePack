package me.AxiusDevelopment.TWoNResourePackHandler.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNResourePackHandler.TWoNResourcePackHandler;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Config;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Messages;

public class Send implements CommandExecutor {

	Config config;
	Messages messages;
	TWoNResourcePackHandler main;
	
	public Send(Config config, Messages messages, TWoNResourcePackHandler main) {
		this.config = config;
		this.messages = messages;
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		if(!sndr.hasPermission(this.config.configData.get("sendPerm"))) {
			sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("noPermSend")));
			return true;
		}
		else
		{
			if(args.length < 1) sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("sendSyntax")));
			else
			{
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("playerNotFound")));
				else {
					target.chat("/pack");
					target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("packReceived").replaceAll("%sender%", sndr.getName())));
					sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("packSent").replaceAll("%player%", target.getName())));
				}
			}
			return true;
		}
	}

}
