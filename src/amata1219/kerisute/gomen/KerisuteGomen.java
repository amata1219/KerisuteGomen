package amata1219.kerisute.gomen;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.ChannelNotRegisteredException;

public class KerisuteGomen extends JavaPlugin {

	/*
	 * Clients
	 * 
	 * Notchian
	 * Forge
	 * LiteLoader
	 * Fabric
	 * Rift
	 * LabyMod
	 * 5zig Mod
	 * Hyperium
	 * Badlion
	 * Vape Cracked
	 * PX Mod
	 * Lunar Client
	 * CheatBreaker
	 * Named Original
	 * 
	 * Yet to be validated
	 * 
	 * LabyMod Ghost Client
	 * CheatBreakerHUD v3
	 * Sigma
	 * Wolfram Client
	 * Jigsaw
	 * Impact Client
	 * CustomNPCs
	 * Console Client
	 * Schematica Mod
	 * JEI
	 * Winterware
	 * Pixel Client
	 * Cosmic Client
	 * Aristois Client (powered by Easy Minecraft Client framework)
	 * 
	 * Undetectable
	 * 
	 * Optifine
	 * Wurst
	 * LiquidBounce
	 * 
	 * Mods
	 * 
	 * Forge
	 * LiteLoader
	 * Fabric
	 * Rift
	 * 
	 */
	
	/*
	 * Channels
	 * 
	 * ! - Mod Loader
	 * ? - Client
	 * @ - Mod
	 * 
	 * +: REGISTER, minecraft:register, register
	 * *: MC|Brand, minecraft:brand
	 * 
	 * Forge Handshake: FML|HS, l:fmlhs
	 * Forge ModPack: + FML|MP
	 * 
	 * ? Notchian: + vanilla
	 * ! Forge: + FML|HS, l:fmlhs
	 * ! LiteLoader: + * LiteLoader
	 * ! Rift: + * rift
	 * ! Fabric: + * fabric
	 * ? Lunar Client: * Lunar-Client
	 * ? Remix : * BLC|M
	 * ? Labymod: LABYMOD
	 * ? Labymod V3: LMC
	 * ? 5zig Mod: 5zig_Set, l:5zig_Set
	 * ? PX Mod: PX|Version
	 * @ Better Sprinting: BSprint, l:bsm, l:bsprint
	 * ? Hyperium: hyperium
	 * @ JourneyMap: journeymap_channel, l:world_info
	 * @ Waila: waila
	 * @ World Downloader: + wdl
	 * @ World Downloader: * WorldDownloader
	 * @ World Downloader: ""(empty), WDL|INIT, WDL|CONTROL
	 * ? Easy Minecraft Client: * Subsystem
	 * @ XaeroMinimap: XaeroMinimap
	 * @ InventoryTweaks: InventoryTweaks
	 * @ World Edit CUI: WECUI
	 * ? Vape: LOLIMAHCKER
	 * ? Pixel Client: MC|Pixel
	 * ? Winterware: LC|Brand
	 * @ JEI: JEI
	 * ? Jigsaw: * Vanilla
	 * @ Schematica: + schematica
	 * ? Badlion Client: ??? (S→C: badlion:mods, badlion:cps)
	 * ? Console Client: * Minecraft-Console-Client
	 * ? PvPLounge Client 1.8: * PLC18
	 * ! CheatBreaker: CB|INIT, CB-Binary
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
	
	private static KerisuteGomen plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
	}
	
	@Override
	public void onDisable(){
		HandlerList.unregisterAll(this);
	}
	
	public static KerisuteGomen plugin(){
		return plugin;
	}
	
}
