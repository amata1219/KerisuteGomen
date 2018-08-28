package amata1219.kerisute.gomen;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KerisuteGomen extends JavaPlugin{

	private static KerisuteGomen plugin;
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
		getServer().getMessenger().registerIncomingPluginChannel(plugin, "MC|Brand", samurai);
		getServer().getMessenger().registerOutgoingPluginChannel(plugin, "MC|Brand");
		getServer().getMessenger().registerIncomingPluginChannel(plugin, "FML|HS", samurai);
		getServer().getMessenger().registerOutgoingPluginChannel(plugin, "FML|HS");
		getServer().getMessenger().registerIncomingPluginChannel(plugin, "PERMISSIONSREPL", samurai);
		getServer().getMessenger().registerOutgoingPluginChannel(plugin, "PERMISSIONSREPL");
		getServer().getMessenger().registerIncomingPluginChannel(plugin, "5zig_Set", samurai);
		getServer().getMessenger().registerOutgoingPluginChannel(plugin, "5zig_Set");
		//getServer().getMessenger().registerIncomingPluginChannel(plugin, "LABYMOD", samurai);
		//getServer().getMessenger().registerOutgoingPluginChannel(plugin, "LABYMOD");
		getServer().getMessenger().registerIncomingPluginChannel(plugin, "LMC", samurai);
		getServer().getMessenger().registerOutgoingPluginChannel(plugin, "LMC");
		commands.put("kerisute", new KerisuteCommand(plugin));
	}

	@Override
	public void onDisable(){
		getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "MC|Brand", samurai);
		getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "MC|Brand");
		getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "FML|HS", samurai);
		getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "FML|HS");
		getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "PERMISSIONSREPL", samurai);
		getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "PERMISSIONSREPL");
		getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "5zig_Set", samurai);
		getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "5zig_Set");
		//getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "LABYMOD", samurai);
		//getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "LABYMOD");
		getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "LMC", samurai);
		getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "LMC");
		PlayerRegisterChannelEvent.getHandlerList().unregister(samurai);
		ClientLoginEvent.getHandlerList().unregister(samurai);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		return commands.get(command.getName()).onCommand(sender, command, label, args);
	}

	public static KerisuteGomen getPlugin(){
		return plugin;
	}

	public CustomConfig getMainConfig(){
		return config;
	}

	public KerisuteSamurai getKerisuteSamurai(){
		return samurai;
	}

}