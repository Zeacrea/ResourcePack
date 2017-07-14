package me.AxiusDevelopment.TWoNResourePackHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import me.AxiusDevelopment.TWoNResourePackHandler.Commands.Add;
import me.AxiusDevelopment.TWoNResourePackHandler.Commands.PackHandler;
import me.AxiusDevelopment.TWoNResourePackHandler.Commands.Reload;
import me.AxiusDevelopment.TWoNResourePackHandler.Commands.Send;
import me.AxiusDevelopment.TWoNResourePackHandler.Events.JoinEvent;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Config;
import me.AxiusDevelopment.TWoNResourePackHandler.YAMLHandlers.Messages;

public class TWoNResourcePackHandler extends JavaPlugin {

	private Messages messages;
	private Config config;
	public HashMap<String, String> messageData = new HashMap<String, String>();
	public HashMap<String, String> configData = new HashMap<String, String>();
	
	public void onEnable() {
	    File data = new File(this.getDataFolder().getParentFile() + File.separator + "TWoN");
	    File thisone = new File(data + File.separator + "TWoNResourcePackHandler");
	    if(!data.exists()) {
	    	data.mkdir();
	    	thisone.mkdir();
	    }
	    
	    if(!thisone.exists()) {
	    	thisone.mkdir();
	    }
		this.messages = new Messages(this);
		this.config = new Config(this);
		
	    this.messageData = this.messages.getMessageData();
	    this.configData = this.config.getMessageData();
	    getCommand("Pack").setExecutor(new PackHandler(this));
	    getCommand("Pack-Reload").setExecutor(new Reload(config, messages, this));
	    getCommand("Pack-Send").setExecutor(new Send(config, messages, this));
	    getCommand("Pack-add").setExecutor(new Add(config, messages, this));
	    int i = Integer.parseInt(getUpdate().replaceAll("\\.", ""));
	    if(i > Integer.parseInt(getDescription().getVersion().replaceAll("\\.", ""))) {
	    	System.out.print("[TWoNResourcePackHandler] Update found.");
	    	this.getServer().getPluginManager().registerEvents(new JoinEvent(configData, i + "", getDescription().getVersion()), this);
	    }
	    
	}
	
	public String getUpdate() {
		String v = "";
		System.out.print("[TWoNResourcePackHandler] Checking for updates...");
		try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "http://www.spigotmc.org/api/general.php").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream()
                    .write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=43639")
                            .getBytes("UTF-8"));
            String version = new BufferedReader(new InputStreamReader(
                    con.getInputStream())).readLine();
            v = version;
        } catch (Exception ex) {
            System.out.print("Failed to check for a update on spigot.");
        }
		
		return v;
		
	}
	
}
