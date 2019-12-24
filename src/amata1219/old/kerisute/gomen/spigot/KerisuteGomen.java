package amata1219.old.kerisute.gomen.spigot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

import amata1219.old.kerisute.gomen.ClientInfo;

public class KerisuteGomen extends JavaPlugin implements Listener, PluginMessageListener {

	private static KerisuteGomen plugin;

	public String version;
	//vX_XX_RXの形になる

	public static final List<String> IGNORE_TAGS = Collections.unmodifiableList(new ArrayList<>(Arrays.asList("minecraft", "FML", "forge", "mcp", "Minecraft Forge", "LiteLoader", "LabyMod")));

	private final HashMap<UUID, ClientInfo> data = new HashMap<>();

	@Override
	public void onEnable(){
		plugin = this;

		version = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

		registerChannels("MC|Brand", "FML|HS", "PERMISSIONSREPL", "5zig_Set", "LMC", "WECUI");
		/*
		 * 対応チャンネル(左から順に)
		 * ・Minecraft
		 * ・Forge
		 * ・LiteLoader
		 * ・5zigClient
		 * ・LabyModClient
		 * ・WorldEditCUI
		 */
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
		data.put(e.getPlayer().getUniqueId(), new ClientInfo());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		data.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onRegister(PlayerRegisterChannelEvent e){
		Player player = e.getPlayer();
		UUID uuid = player.getUniqueId();

		if(!data.containsKey(uuid))
			return;

		ClientInfo info = data.get(uuid);

		String channel = e.getChannel();
		switch(channel.hashCode()){

		}
	}

	public static void main(String[] args){
		System.out.println("fml".hashCode());
	}

	@Override
	public void onPluginMessageReceived(String tag, Player player, byte[] data) {
		if(tag.equals("BungeeCord") || tag.equals("bungeecord:main"))
			return;
		//BungeeCordは関係無いので戻る


	}

	public void done(UUID uuid){
		if(!data.containsKey(uuid))
			return;

		ClientInfo info = data.get(uuid);
	}

}
