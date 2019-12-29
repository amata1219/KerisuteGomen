package amata1219.kerisute.gomen;

import java.io.UnsupportedEncodingException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class KerisuteGomen extends JavaPlugin implements PluginMessageListener {

	/*
	 * Channels
	 * 
	 * ! - Mod Loader
	 * ? - Client
	 * & - Forge Mod
	 * @(Mod Loader Name) - Other Mod
	 * 
	 * +: REGISTER, minecraft:register, register
	 * *: MC|Brand, minecraft:brand
	 * 
	 * Forge Handshake: FML|HS, l:fmlhs
	 * Forge ModPack: + FML|MP
	 * 
	 * ! Forge: + FML|HS, l:fmlhs
	 * ! LiteLoader: + * LiteLoader
	 * ! Rift: + * rift
	 * ! Fabric: + * fabric
	 * 
	 * ? Notchian: + vanilla
	 * ? Osmium: * vanilla/remake in C
	 * ? Lunar Client: * Lunar-Client
	 * ? Remix : * BLC|M
	 * ? Labymod: LABYMOD
	 * ? Labymod V3: LMC
	 * ? 5zig Mod: 5zig_Set, l:5zig_Set
	 * ? PX Mod: PX|Version
	 * ? Hyperium: hyperium
	 * ? Easy Minecraft Client: * Subsystem
	 * ? Aristois: * Subsystem
	 * ? Vape: LOLIMAHCKER
	 * ? Pixel Client: MC|Pixel
	 * ? Winterware: LC|Brand
	 * ? Jigsaw: * Vanilla
	 * ? Badlion Client: ??? (S→C: badlion:mods, badlion:cps)
	 * ? Minecraft Console Client: * Minecraft-Console-Client
	 * ? PvPLounge Client 1.8: * PLC18
	 * ? CheatBreaker: CB|INIT, CB-Binary
	 * 
	 * & Better Sprinting: BSprint, l:bsm, l:bsprint
	 * & JourneyMap: journeymap_channel, l:world_info
	 * & Waila: waila
	 * & XaeroMinimap: XaeroMinimap
	 * & InventoryTweaks: InventoryTweaks
	 * & JEI: JEI, jei
	 * & NEI: NEI
	 * & Schematica: + schematica
	 * & Advanced Capes Mod: advancedcapes
	 * & Quick Hotbar: quickhotbar
	 * 
	 * @(LiteLoader) World Downloader: + wdl
	 * @(LiteLoader) World Downloader: * WorldDownloader
	 * @(LiteLoader) World Downloader: ""(empty), WDL|INIT, WDL|CONTROL, WDL|REQUEST
	 * @(LiteLoader) World Edit CUI: WECUI
	 * 
	 * Forge Mod はチャンネルを利用する必要無し
	 * 
	 */
	
	/*
		if (!Bukkit.getServer().getVersion().contains("1.13")
			&& !Bukkit.getServer().getVersion().contains("1.14")
			&& !Bukkit.getServer().getVersion().contains("1.15")) {
				this.b.sendPluginMessage(this.a, "FML|HS", new byte[] { -2, 0 });
				this.b.sendPluginMessage(this.a, "FML|HS", new byte[] { 0, 2, 0, 0, 0, 0 });
				this.b.sendPluginMessage(this.a, "FML|HS", new byte[] { 2, 0, 0, 0, 0 });
		} else {
				this.b.sendPluginMessage(this.a, arg0, new byte[] { -2, 0 });
				this.b.sendPluginMessage(this.a, arg0, new byte[] { 0, 2, 0, 0, 0, 0 });
				this.b.sendPluginMessage(this.a, arg0, new byte[] { 2, 0, 0, 0, 0 });
		}
		
		if (arg0.equals("PERMISSIONSREPL")) {
			this.b.sendPluginMessage(this.a, "PERMISSIONSREPL", new byte[] { -2 });
			this.b.sendPluginMessage(this.a, "PERMISSIONSREPL", new byte[] { 0, 2 });
			this.b.sendPluginMessage(this.a, "PERMISSIONSREPL", new byte[] { 2 });
		}
	 */
	
	/*
	 *  1.13以前
			ZIG = "5zig_Set";
			BSPRINT = "BSprint";
			BSM = "BSM";
			WDLINIT = "WDL|INIT";
			WDLCONTROL = "WDL|CONTROL";
			MCBRAND = "MC|Brand";
			WDLREQ = "WDL|REQUEST";
			SCHEMATICA = "schematica";
			FML = "FML";
			FMLHS = "FMLHS";

		1.13以降
			ZIG = "l:5zig_set";
			BSM = "l:bsm";
			BSM2 = "l:bsprint";
			MCBRAND = "minecraft:brand";
			WDLREQ = "wdl:request";
			SCHEMATICA = "l:schematica";
			WDLINIT = "wdl:init";
			WDLCONTROL = "wdl:control";
			FML = "l:fml";
			FMLHS = "l:fmlhs";
	 */
	
	private static KerisuteGomen plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
		getServer().getMessenger().registerIncomingPluginChannel(this, "minecraft:brand", this);
		getServer().getMessenger().registerIncomingPluginChannel(this, "l:fmlhs", this);
		//getServer().getMessenger().registerIncomingPluginChannel(this, "minecraft:register", this);
		//CraftServer
	}
	
	@Override
	public void onDisable(){
		HandlerList.unregisterAll(this);
	}
	
	public static KerisuteGomen plugin(){
		return plugin;
	}

	@EventHandler
	public void onRegisterChannel(PlayerRegisterChannelEvent event) {
		System.out.println("Registered -> " + event.getChannel());
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player sender, byte[] data) {
		System.out.println(channel);
		switch(channel){
		case "minecraft:brand":
			try {
				String str = new String(data, "UTF-8");
				System.out.println(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			break;
		case "l:fmlhs":
			System.out.println("l:fmlhs");
			break;
		default:
			break;
		}
	}
	
}
