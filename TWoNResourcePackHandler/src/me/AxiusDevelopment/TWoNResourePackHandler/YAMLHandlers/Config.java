package me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNResourePackHandler.TWoNResourcePackHandler;
import net.md_5.bungee.api.ChatColor;

public class Config {
	
	TWoNResourcePackHandler plugin;
	HashMap<String, String> messages;
	public HashMap<String, String> configData;
	
	public Config(TWoNResourcePackHandler instance) {
		plugin = instance;
		this.configData = new HashMap<String, String>();
		this.messages = instance.messageData;
	}
	
	public HashMap<String, String> getMessageData()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNResourcePackHandler" + File.separator + "config.yml");
	    if (!f.exists()) {
	      saveMessages();
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
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNResourcePackHandler" + File.separator + "config.yml");
	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(true)) {
	      this.configData.put(message, config.getString(message));
	    }
	    return this.configData;
	  }
	  
	  private void saveMessages()
	  {
	    setMessage("reloadPerm", "TWoNRPH.reload");
	    setMessage("sendPerm", "TWoNRPH.send");
	    setMessage("addPerm", "TWoNRPH.add");
	    setMessage("viewUpdate", "TWoNRPH.updates");
	    setMessage("URL.Default", "https://www.dropbox.com/s/wu5psei4yca6st4/0.9.1.zip?dl=0");
	  }
	  
	  public void addWorld(Player p, String world, String url) {
		  File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNResourcePackHandler" + File.separator + "config.yml");
		  FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		  config.set("URL." + world, url);
	      try
	      {
	        config.save(f);
	      }
	      catch (IOException e)
	      {
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("addError")));
	        e.printStackTrace();
	      }
	  }
	  
	  private void setMessage(String name, String message)
	  {
		    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNResourcePackHandler" + File.separator + "config.yml");
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
