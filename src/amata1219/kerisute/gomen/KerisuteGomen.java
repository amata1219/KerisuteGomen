package amata1219.kerisute.gomen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class KerisuteGomen extends JavaPlugin implements Listener, PluginMessageListener {

	private static KerisuteGomen plugin;

	@Override
	public void onEnable(){
		plugin = this;

		registerChannels("MC|Brand", "FML|HS", "PERMISSIONSREPL", "5zig_Set", "LMC", "WECUI");
	}

	@Override
	public void onDisable(){
		unregisterChannels("MC|Brand", "FML|HS", "PERMISSIONSREPL", "5zig_Set", "LMC", "WECUI");

		HandlerList.unregisterAll((JavaPlugin) this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		onCommand(sender, args);
		return true;
	}

	public void onCommand(CommandSender sender, String[] args){

	}

	public String getElement(String[] args, int index){
		if(args.length <= index)
			return null;

		return args[index];
	}

	public static KerisuteGomen getPlugin(){
		return plugin;
	}

	public void registerChannels(String... channels){
		Messenger messenger = getServer().getMessenger();

		for(String channel : channels){
			messenger.registerIncomingPluginChannel(this, channel, this);
			messenger.registerOutgoingPluginChannel(this, channel);
		}
	}

	public void unregisterChannels(String... channels){
		Messenger messenger = getServer().getMessenger();

		for(String channel : channels){
			messenger.unregisterIncomingPluginChannel(this, channel, this);
			messenger.unregisterOutgoingPluginChannel(this, channel);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){

	}

	@EventHandler
	public void onQuti(PlayerQuitEvent e){

	}

	@EventHandler
	public void onRegister(PlayerRegisterChannelEvent e){

	}

	@Override
	public void onPluginMessageReceived(String tag, Player player, byte[] data) {
		if(tag.equals("BungeeCord") || tag.equals("bungeecord:main"))
			return;
	}

}
