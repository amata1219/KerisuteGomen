package amata1219.kerisute.gomen.old;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.java.JavaPlugin;

import amata1219.kerisute.gomen.CustomConfig;
import amata1219.kerisute.gomen.KerisuteCommand;

public class KerisuteGomenOld extends JavaPlugin {

	private static KerisuteGomenOld plugin;
	private CustomConfig config;
	private KerisuteSamurai samurai;

	private HashMap<String, TabExecutor> commands = new HashMap<String, TabExecutor>();

	@Override
	public void onEnable(){
		plugin = this;
		config = new CustomConfig(plugin);
		config.saveDefaultConfig();
		samurai = new KerisuteSamurai(plugin);
		getServer().getPluginManager().registerEvents(samurai, plugin);
		registerPluginChannel("MC|Brand");
		registerPluginChannel("FML|HS");
		registerPluginChannel("PERMISSIONSREPL");
		registerPluginChannel("5zig_Set");
		registerPluginChannel("LMC");
		//registerPluginChannel("LABYMOD");
		registerPluginChannel("WECUI");
		commands.put("kerisute", new KerisuteCommand(plugin));
	}

	@Override
	public void onDisable(){
		unregisterPluginChannel("MC|Brand");
		unregisterPluginChannel("FML|HS");
		unregisterPluginChannel("PERMISSIONSREPL");
		unregisterPluginChannel("5zig_Set");
		unregisterPluginChannel("LMC");
		//unregisterPluginChannel("LABYMOD");
		unregisterPluginChannel("WECUI");
		PlayerRegisterChannelEvent.getHandlerList().unregister(samurai);
		ClientLoginEventOld.getHandlerList().unregister(samurai);
	}

	private void registerPluginChannel(String channel){
		getServer().getMessenger().registerIncomingPluginChannel(plugin, channel, samurai);
		getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
	}

	private void unregisterPluginChannel(String channel){
		getServer().getMessenger().unregisterIncomingPluginChannel(plugin, channel, samurai);
		getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, channel);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		return commands.get(command.getName()).onCommand(sender, command, label, args);
	}

	public static KerisuteGomenOld getPlugin(){
		return plugin;
	}

	public CustomConfig getMainConfig(){
		return config;
	}

	public KerisuteSamurai getKerisuteSamurai(){
		return samurai;
	}

}