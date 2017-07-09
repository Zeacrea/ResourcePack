package me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.AxiusDevelopment.TWoNResourePackHandler.TWoNResourcePackHandler;

public class Messages {
	
	TWoNResourcePackHandler plugin;
	public HashMap<String, String> messageData;
	
	public Messages(TWoNResourcePackHandler instance) {
		plugin = instance;
		this.messageData = new HashMap<String, String>();
	}
	
	public HashMap<String, String> getMessageData()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNResourcePackHandler" + File.separator + "messages.yml");

        saveMessages();
	    if (!f.exists()) {
	      try
	      {
	        f.createNewFile();
	      }
	      catch (IOException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return loadMessages();
	  }

	public HashMap<String, String> loadMessages()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNResourcePackHandler" + File.separator + "messages.yml");
	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(false)) {
	      this.messageData.put(message, config.getString(message));
	    }
	    return this.messageData;
	  }
	  
	  private void saveMessages()
	  {
	    setMessage("messageBegin", "&c&lResourcePack&7 Click ");
	    setMessage("messageEnd", "&7 to receive the resource pack.");
	    setMessage("hereText", "&cHERE");
	    setMessage("hereHover", "&7Click to receive link.");
	    setMessage("noPermReload", "&c&lResourcePack&7 You have to be &c&lADMIN&7 or above to use this command!");
	    setMessage("noPermSend", "&c&lResourcePack&7 You have to be &c&lADMIN&7 or above to use this command!");
	    setMessage("noPermAdd", "&c&lResourcePack&7 You have to be &c&lADMIN&7 or above to use this command!");
	    setMessage("reloadConfig", "&c&lResourcePack&7 Successfully reloaded &cConfig.yml&7.");
	    setMessage("reloadBoth", "&c&lResourcePack&7 Successfully relaoded &cConfig.yml&7 and &cMessages.yml&7.");
	    setMessage("reloadMessages", "&c&lResourcePack&7 successfully reloaded &cMessages.yml&7.");
	    setMessage("reloadSyntax", "&c&lResourcePack&7 Usage: /pack-reload <both|config|messages>");
	    setMessage("sendSyntax", "&c&lResourcePack&7 Usage: /pack-send <player>");
	    setMessage("playerNotFound", "&c&lResourcePack&7 that player could not be located.");
	    setMessage("packReceived", "&c&lResourcePack&7 You have been sent the resource pack by %sender%.");
	    setMessage("packSent", "&c&lResourcePack&7 You have sent the resource pack to %player%.");
	    setMessage("addSyntax", "&c&lResourcePack&7 Usage: /pack-add <world-name> <URL>");
	    setMessage("addAdded", "&c&lResourcePack&7 Successfully added/updated world URL!");
	    setMessage("addError", "&c&lResourcePack&7 Error while adding new URL check console for details.");
	  }
	  
	  private void setMessage(String name, String message)
	  {
		   File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNResourcePackHandler" + File.separator + "messages.yml");
		   FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		   if (!config.isSet(name))
		    {
		      config.set(name, message);
		      try
		      {
		        config.save(f);
		      }
		      catch (IOException e)
		      {
		        e.printStackTrace();
		      }
		    }
	  }
}
