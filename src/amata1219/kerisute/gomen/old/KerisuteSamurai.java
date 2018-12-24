package amata1219.kerisute.gomen.old;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import amata1219.kerisute.gomen.CustomConfig;
import amata1219.kerisute.gomen.ModOld;
import amata1219.receiving.packets.on.spigot.PacketInjector;
import net.eq2online.permissions.ReplicatedPermissionsContainer;

public class KerisuteSamurai implements Listener, PluginMessageListener{

	//private boolean DEBUG = true;

	private KerisuteGomenOld plugin;
	private static PacketInjector injector;
	private CustomConfig config;

	private boolean isEnableLogging = true;
	private String kickMessage = "You were kicked by KerisuteGomen\nBlocked Mods: [mods]";
	private List<String> kickMessageReceiver = new ArrayList<String>();
	private boolean allowForgeClientLogin, forgeModCheck, isEnableForgeModIDWhitelist;
	private List<String> forgeModIDWhitelist = new ArrayList<String>();
	private boolean allowLiteLoaderClientLogin, liteLoaderModCheck, isEnableLiteLoaderModIDWhitelist;
	private List<String> liteLoaderModIDWhitelist = new ArrayList<String>();
	private boolean allowLabyModClientLogin;
	private boolean allowNonForge5zigClientLogin;
	private List<String> customClients = new ArrayList<String>();
	private boolean allowOtherClientLogin;

	private HashMap<UUID, ClientData> clients = new HashMap<UUID, ClientData>();
	private HashMap<UUID, BukkitRunnable> remove = new HashMap<UUID, BukkitRunnable>();

	private final String MCBrand = "MC|Brand";
	private final String FORGE = "FORGE";
	private final String FMLHS = "FML|HS";
	private final String PERMISSIONSREPL = "PERMISSIONSREPL";
	private final String ZigSet = "5zig_Set";
	//modname:  5zig
	//private final String LABYMOD = "LABYMOD";
	private final String LMC = "LMC";
	private final String WECUI = "WECUI";
	//modname:  WorldEditCUI

	private final List<String> ignoreMods = new ArrayList<String>(Arrays.asList("minecraft", "FML", "forge", "mcp", "Minecraft Forge", "LiteLoader", "LabyMod"));

	public KerisuteSamurai(KerisuteGomenOld plugin){
		this.plugin = plugin;
		config = plugin.getMainConfig();
		loadValues();
	}

	public void loadValues(){
		FileConfiguration c = config.getConfig();
		isEnableLogging = c.getBoolean("EnableLogging");
		kickMessage = c.getString("KickMessage");
		kickMessageReceiver = c.getStringList("KickMessageReceiver");
		allowForgeClientLogin = c.getBoolean("Forge.CanLogin");
		forgeModCheck = c.getBoolean("Forge.ModCheck");
		isEnableForgeModIDWhitelist = c.getBoolean("Forge.EnableModIDWhitelist");
		forgeModIDWhitelist = c.getStringList("Forge.Whitelist");
		allowLiteLoaderClientLogin = c.getBoolean("LiteLoader.CanLogin");
		liteLoaderModCheck = c.getBoolean("LiteLoader.ModCheck");
		isEnableLiteLoaderModIDWhitelist = c.getBoolean("LiteLoader.EnableModIDWhitelist");
		liteLoaderModIDWhitelist = c.getStringList("LiteLoader.Whitelist");
		allowLabyModClientLogin = c.getBoolean("LabyMod.CanLogin");
		allowNonForge5zigClientLogin = c.getBoolean("NonForge5zig.CanLogin");
		customClients = c.getStringList("RegisterCustomClients");
		allowOtherClientLogin = c.getBoolean("OtherClient.CanLogin");
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e){
		injector.addPlayer(e.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		UUID uuid = e.getPlayer().getUniqueId();
		clients.remove(uuid);
		if(remove.containsKey(uuid))remove.get(uuid).cancel();
		remove.remove(uuid);
	}

	@EventHandler
	public void onRegisterChannel(PlayerRegisterChannelEvent e){
		Player p = e.getPlayer();
		String c = e.getChannel();
		System.out.println(c);
		//if(DEBUG)print(c);
		if(clients.containsKey(p.getUniqueId())){
			clients.get(p.getUniqueId()).getUsingChannels().add(c);
		}
		if(c.equals(FORGE)){
			ClientData d = getClientData(p);
			d.getUsingChannels().add(c);
			p.sendPluginMessage(plugin, FMLHS, new byte[] {-2, 0});
			p.sendPluginMessage(plugin, FMLHS, new byte[] {0, 2, 0, 0, 0, 0});
			p.sendPluginMessage(plugin, FMLHS, new byte[] {2, 0, 0, 0, 0});
		}else if(c.equals(PERMISSIONSREPL)){
			ClientData d = getClientData(p);
			d.getUsingChannels().add(c);
			p.sendPluginMessage(plugin, PERMISSIONSREPL, new byte[] {-2, 0});
			p.sendPluginMessage(plugin, PERMISSIONSREPL, new byte[] {0, 2, 0, 0, 0, 0});
			p.sendPluginMessage(plugin, PERMISSIONSREPL, new byte[] {2, 0, 0, 0, 0});
		}else if(c.equals(ZigSet)){
			ClientData d = getClientData(p);
			d.getUsingChannels().add(c);
			if(d.isUsing5zig())return;
			d.setUsing5zig(true);
			ModOld mod = new ModOld("5zig", "[cannot get version]");
			d.getForgeMods().add(mod);
			if(!allowNonForge5zigClientLogin && !d.isIgnoreModCheck()){
				d.getBlockedMods().add(mod);
				d.setShouldKick(true);
			}
		}else if(c.equals(WECUI)){
			ClientData d = getClientData(p);
			d.getUsingChannels().add(c);
			ModOld mod = new ModOld("WorldEditCUI", "[cannot get version]");
			d.getLiteLoaderMods().add(mod);
			if(isEnableLiteLoaderModIDWhitelist && !liteLoaderModIDWhitelist.contains(mod.getId())){
				d.getBlockedMods().add(mod);
				d.setShouldKick(true);
			}
		}
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] data){
		UUID uuid = player.getUniqueId();
		//if(DEBUG)print("P: " + channel);
		if(clients.containsKey(uuid)){
			clients.get(uuid).getUsingChannels().add(channel);
		}
		if(channel.equals(MCBrand)){
			ClientData d = getClientData(player);
			d.getUsingChannels().add(channel);
			String brand = null;
			try{
				brand = new String(data, "UTF-8");
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			List<String> brands = Arrays.asList(brand.split(","));
			StringBuilder sb = new StringBuilder();
			boolean f = false, l = false;
			for(String name : brands){
				if(name.hashCode() == -452127340){
					sb.append(",vanilla");
					continue;
				}else if(name.endsWith("fml")){
					f = true;
					sb.append(",ForgeModLoader");
					continue;
				}if(name.equals("forge")){
					f = true;
					sb.append(",Minecraft Forge");
					d.setForgeClient(true);
					if(!allowForgeClientLogin && !d.isIgnoreModCheck()){
						d.getBlockedMods().add(new ModOld("Minecraft Forge", ""));
						d.setShouldKick(true);
					}
				}else if(name.equals("LiteLoader")){
					l = true;
					sb.append(",LiteLoader");
					d.setLiteLoaderClient(true);
					if(!allowLiteLoaderClientLogin && !d.isIgnoreModCheck()){
						d.getBlockedMods().add(new ModOld("LiteLoader", ""));
						d.setShouldKick(true);
					}
				}else if(name.hashCode() == -1616516597){
					l = true;
					sb.append(",LiteLoader");
					d.setLiteLoaderClient(true);
					if(!allowLiteLoaderClientLogin && !d.isIgnoreModCheck()){
						d.getBlockedMods().add(new ModOld("LiteLoader", ""));
						d.setShouldKick(true);
					}
				}else{
					sb.append("," + name);
					d.getOtherClients().add(name);
					if(!customClients.contains(name) && allowOtherClientLogin){
						d.getBlockedMods().add(new ModOld(name, ""));
						d.setShouldKick(true);
					}
				}
			}
			d.setClientName(sb.toString().substring(1));
			if(f && !l){
				new BukkitRunnable(){
					int i = 0;
					public void run(){
						if(d.isFcheck()||!player.isOnline()||i == 60){
							ClientLoginEventOld event  = new ClientLoginEventOld(d);
							if(!player.isOnline())event.setInvalid(true);
							plugin.getServer().getPluginManager().callEvent(event);
							cancel();
						}
						i++;
					}
				}.runTaskTimer(plugin, 0, 20);
			}else if((!f && l) || (f && l)){
				new BukkitRunnable(){
					int i = 0;
					public void run(){
						if(d.isLcheck()||!player.isOnline()||i == 60){
							ClientLoginEventOld event  = new ClientLoginEventOld(d);
							if(!player.isOnline())event.setInvalid(true);
							plugin.getServer().getPluginManager().callEvent(event);
							cancel();
						}
						i++;
					}
				}.runTaskTimer(plugin, 0, 20);
			}else{
				new BukkitRunnable(){
					public void run(){
						ClientLoginEventOld event  = new ClientLoginEventOld(d);
						if(!player.isOnline())event.setInvalid(true);
						plugin.getServer().getPluginManager().callEvent(event);
					}
				}.runTaskLater(plugin, 50);
			}
		}else if(channel.equals(FMLHS)){
			ClientData d = getClientData(player);
			d.getUsingChannels().add(channel);
			List<ModOld> mods = getMods(data);
			if(mods.isEmpty())return;
			d.setForgeMods(mods);
			if(forgeModCheck && isEnableForgeModIDWhitelist){
				for(ModOld mod : mods){
					String id = mod.getId();
					if(!forgeModIDWhitelist.contains(id)){
						if(ignoreMods.contains(id))continue;
						d.getBlockedMods().add(mod);
						d.setShouldKick(true);
					}
				}
			}
			d.setFcheck(true);
		}else if(channel.equals(PERMISSIONSREPL)){
			ClientData d = getClientData(player);
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
				ReplicatedPermissionsContainer rpc = (ReplicatedPermissionsContainer) inputStream.readObject();
				ModOld mod = new ModOld(rpc.modName, String.valueOf(rpc.modVersion));
				d.getLiteLoaderMods().add(mod);
				if(liteLoaderModCheck && isEnableLiteLoaderModIDWhitelist){
					if(!liteLoaderModIDWhitelist.contains(mod.getId())){
						d.getBlockedMods().add(mod);
						d.setShouldKick(true);
					}
				}
				new BukkitRunnable(){
					public void run(){
						d.setLcheck(true);
					}
				}.runTaskLater(plugin, 20);
			}catch(IOException e){
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}else if(channel.equals(LMC)){
			ClientData d = getClientData(player);
			d.setLabyModClient(true);
			if(!allowLabyModClientLogin){
				d.getBlockedMods().add(new ModOld("LabyMod", ""));
				d.setShouldKick(true);
			}
		}
	}

	@EventHandler
	public void onClientLoginEvent(ClientLoginEventOld e){
		ClientData d = e.getClientData();
		if(e.isInvalid() && isEnableLogging){
			Player p = d.getPlayer();
			print(p.getName() + "(" + p.getUniqueId().toString() + ") quited the game before doing client check.");
			return;
		}
		if(isEnableLogging){
			print(toClientInfo(d));
		}
		if(!d.isIgnoreModCheck() && d.shouldKick()){
			StringBuilder sb = new StringBuilder();
			for(ModOld mod : d.getBlockedMods()){
				if(ignoreMods.contains(mod.getId())){
					sb.append(", " + ChatColor.RED + mod.getId() + ChatColor.RESET);
					continue;
				}else if(mod.getId().equals("forgewurst")){
					sb.append(", " + ChatColor.DARK_PURPLE + mod.getId() + "-" + mod.getVersion() + ChatColor.RESET);
					continue;
				}
				sb.append(", " + ChatColor.RED + mod.getId() + "-" + mod.getVersion() + ChatColor.RESET);
			}
			String message = replaceAll(replaceAll(kickMessage, "[player]", e.getPlayer().getName()), "[mods]", sb.toString().equals("") ? "null" : sb.toString().substring(2));
			e.getPlayer().kickPlayer(message);
			for(String name : kickMessageReceiver){
				if(plugin.getServer().getPlayer(name) != null){
					plugin.getServer().getPlayer(name).sendMessage(message);
				}
			}
		}
	}

	private String toClientInfo(ClientData d){
		Player p = d.getPlayer();
		StringBuilder sb = new StringBuilder();
		String forgeMods = d.isForgeClient() ? "Forge: true" : "Forge: false";
		if(d.isForgeClient()){
			for(ModOld mod : d.getForgeMods()){
				if(ignoreMods.contains(mod.getId())){
					sb.append(", " + mod.getId() + ChatColor.RESET);
					continue;
				}
				sb.append(", " + ((d.getBlockedMods().contains(mod) && !d.isIgnoreModCheck()) ? ChatColor.RED + "*" : "") + mod.getId() + "-" + mod.getVersion() + "" + ChatColor.RESET);
			}
			forgeMods = forgeMods + "(Mods: " + (sb.toString().equals("") ? "null" : sb.toString().substring(2)) + ")" + ChatColor.RESET;
			sb.setLength(0);
		}
		String liteLoaderMods = d.isLiteLoaderClient() ? "LiteLoader: true" : "LiteLoader: false";
		if(d.isLiteLoaderClient()){
			for(ModOld mod : d.getLiteLoaderMods()){
				sb.append(", " + ((d.getBlockedMods().contains(mod) && !d.isIgnoreModCheck()) ? ChatColor.RED + "*" : "") + mod.getId() + "-" + mod.getVersion() + "" + ChatColor.RESET);
			}
			liteLoaderMods = liteLoaderMods + "(Mods: " + (sb.toString().equals("") ? "null" : sb.toString().substring(2)) + ")" + ChatColor.RESET;
			sb.setLength(0);
		}
		String otherClients = d.isOtherClient() ? "OtherClient: true" : "OtherClient: false";
		if(d.isOtherClient()){
			for(String name : d.getOtherClients()){
				sb.append(", " + (!customClients.contains(name) && !d.isIgnoreModCheck() && !allowOtherClientLogin ? ChatColor.RED + "*" : "") + name + ChatColor.RESET);
			}
			otherClients = otherClients + "(Mods: " + (sb.toString().equals("") ? "null" : sb.toString().substring(2)) + ")";
		}
		String info = "[KerisuteGomen]: " + (d.isIgnoreModCheck() ? ChatColor.AQUA + "!" + p.getName() + ChatColor.RESET: p.getName()) + " is joining(uuid: " + p.getUniqueId().toString() + ", Vanilla: "
		+ d.isVanillaClient() + ", " + (!allowForgeClientLogin && !d.isIgnoreModCheck() && d.isForgeClient() ? ChatColor.RED + replaceAll(forgeMods, ChatColor.RESET + "", "") : forgeMods)
		+ ChatColor.RESET + ", " + (!allowLiteLoaderClientLogin && !d.isIgnoreModCheck() && d.isLiteLoaderClient()? ChatColor.RED + replaceAll(liteLoaderMods, ChatColor.RESET + "", "") : liteLoaderMods)
		+ ChatColor.RESET + ", " + (d.isNonForge5zigClient() && !d.isIgnoreModCheck() &&!allowNonForge5zigClientLogin ? ChatColor.RED + "NonForge5zig: true" : "NonForge5zig: false")
		+ ChatColor.RESET + ", " + (d.isLabyModClient() && !d.isIgnoreModCheck() && !allowLabyModClientLogin ? ChatColor.RED + "LabyMod: true" : "LabyMod: false")
		+ ChatColor.RESET + ", " + (d.isForgeWurstClient() && !d.isIgnoreModCheck()	 ? ChatColor.DARK_PURPLE + "ForgeWurst: true" : "ForgeWurst: false")
		+ ChatColor.RESET + ", " + otherClients + ")";
		return info;
	}

	private void print(String s){
		plugin.getServer().getConsoleSender().sendMessage(s);
	}

	private ClientData getClientData(Player player){
		UUID uuid = player.getUniqueId();
		if(clients.containsKey(uuid)){
			return clients.get(uuid);
		}
		clients.put(uuid, new ClientData(player));
		DataRemoveTask task = new DataRemoveTask(clients, uuid);
		task.runTaskLater(plugin, 1210);
		remove.put(uuid, task);
		return clients.get(uuid);
	}

	private List<ModOld> getMods(byte[] data){
		List<ModOld> mods = new ArrayList<ModOld>();
		boolean store = false;
		String name = null;
		for(int i = 2; i < data.length; store = !store){
			int end = i + data[i] + 1;
			byte[] range = Arrays.copyOfRange(data, i + 1, end);
			String version = new String(range, StandardCharsets.UTF_8);
			if(store){
				mods.add(new ModOld(name, version));
			}else{
				name = version;
			}
			i = end;
		}
		return mods;
	}

	private String replaceAll(final String s, final String regex, String replacement){
		StringBuilder replace = new StringBuilder();
		final int sl = s.length();
		final int rl = regex.length();
		replace.append(s);
		boolean m = false;
		for(int i = 0; i < sl; i++){
			int start = replace.indexOf(regex, i);
			if(start == -1){
				if(start == 0){
					return s;
				}
				return replace.toString();
			}
			replace = replace.replace(start, start + rl, replacement);
			m = true;
		}
		if(!m){
			return s;
		}else{
			return replace.toString();
		}
	}

	public boolean isEnableLogging() {
		return isEnableLogging;
	}

	public void setEnableLogging(boolean isEnableLogging) {
		this.isEnableLogging = isEnableLogging;
	}

	public String getKickMessage() {
		return kickMessage;
	}

	public void setKickMessage(String kickMessage) {
		this.kickMessage = kickMessage;
	}

	public List<String> getKickMessageReceiver() {
		return kickMessageReceiver;
	}

	public void setKickMessageReceiver(List<String> kickMessageReceiver) {
		this.kickMessageReceiver = kickMessageReceiver;
	}

	public boolean allowForgeClientLogin() {
		return allowForgeClientLogin;
	}

	public void setAllowForgeClientLogin(boolean allowForgeClientLogin) {
		this.allowForgeClientLogin = allowForgeClientLogin;
	}

	public boolean isForgeModCheck() {
		return forgeModCheck;
	}

	public void setForgeModCheck(boolean forgeModCheck) {
		this.forgeModCheck = forgeModCheck;
	}

	public boolean isEnableForgeModIDWhitelist() {
		return isEnableForgeModIDWhitelist;
	}

	public void setEnableForgeModIDWhitelist(boolean isEnableForgeModIDWhitelist) {
		this.isEnableForgeModIDWhitelist = isEnableForgeModIDWhitelist;
	}

	public List<String> getForgeModIDWhitelist() {
		return forgeModIDWhitelist;
	}

	public void setForgeModIDWhitelist(List<String> forgeModIDWhitelist) {
		this.forgeModIDWhitelist = forgeModIDWhitelist;
	}

	public boolean allowLiteLoaderClientLogin() {
		return allowLiteLoaderClientLogin;
	}

	public void setAllowLiteLoaderClientLogin(boolean allowLiteLoaderClientLogin) {
		this.allowLiteLoaderClientLogin = allowLiteLoaderClientLogin;
	}

	public boolean isLiteLoaderModCheck() {
		return liteLoaderModCheck;
	}

	public void setLiteLoaderModCheck(boolean liteLoaderModCheck) {
		this.liteLoaderModCheck = liteLoaderModCheck;
	}

	public boolean isEnableLiteLoaderModIDWhitelist() {
		return isEnableLiteLoaderModIDWhitelist;
	}

	public void setEnableLiteLoaderModIDWhitelist(boolean isEnableLiteLoaderModIDWhitelist) {
		this.isEnableLiteLoaderModIDWhitelist = isEnableLiteLoaderModIDWhitelist;
	}

	public List<String> getLiteLoaderModIDWhitelist() {
		return liteLoaderModIDWhitelist;
	}

	public void setLiteLoaderModIDWhitelist(List<String> liteLoaderModIDWhitelist) {
		this.liteLoaderModIDWhitelist = liteLoaderModIDWhitelist;
	}

	public boolean allowNonForge5zigClientLogin() {
		return allowNonForge5zigClientLogin;
	}

	public void setAllowNonForge5zigClientLogin(boolean allowNonForge5zigClientLogin) {
		this.allowNonForge5zigClientLogin = allowNonForge5zigClientLogin;
	}

	public List<String> getCustomClients() {
		return customClients;
	}

	public void setCustomClients(List<String> customClients) {
		this.customClients = customClients;
	}

	public boolean allowOtherClientLogin() {
		return allowOtherClientLogin;
	}

	public void setAllowOtherClientLogin(boolean allowOtherClientLogin) {
		this.allowOtherClientLogin = allowOtherClientLogin;
	}

}