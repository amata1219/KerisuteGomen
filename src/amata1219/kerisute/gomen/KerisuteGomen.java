package amata1219.kerisute.gomen;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

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
	 * & - Forge Mod
	 * @(Mod Loader Name) - Other Mod
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
	 * & Better Sprinting: BSprint, l:bsm, l:bsprint
	 * ? Hyperium: hyperium
	 * & JourneyMap: journeymap_channel, l:world_info
	 * & Waila: waila
	 * @(LiteLoader) World Downloader: + wdl
	 * @(LiteLoader) World Downloader: * WorldDownloader
	 * @(LiteLoader) World Downloader: ""(empty), WDL|INIT, WDL|CONTROL, WDL|REQUEST
	 * ? Easy Minecraft Client: * Subsystem
	 * & XaeroMinimap: XaeroMinimap
	 * & InventoryTweaks: InventoryTweaks
	 * @(LiteLoader) World Edit CUI: WECUI
	 * ? Vape: LOLIMAHCKER
	 * ? Pixel Client: MC|Pixel
	 * ? Winterware: LC|Brand
	 * & JEI: JEI, jei
	 * & NEI: NEI
	 * ? Jigsaw: * Vanilla
	 * & Schematica: + schematica
	 * ? Badlion Client: ??? (S→C: badlion:mods, badlion:cps)
	 * ? Console Client: * Minecraft-Console-Client
	 * ? PvPLounge Client 1.8: * PLC18
	 * ! CheatBreaker: CB|INIT, CB-Binary
	 * & Advanced Capes Mod: advancedcapes
	 * & Quick Hotbar: quickhotbar
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
	 * 		if (1.13以前) {
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
		}

		if (1.13以降) {
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
