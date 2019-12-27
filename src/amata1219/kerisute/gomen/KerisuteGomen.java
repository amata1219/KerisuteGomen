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
	 * Listeners
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
	 * ? Remix Client: * Lunar-Client
	 * ? Labymod: LABYMOD
	 * ? Labymod V3: LMC
	 * ? 5zig Mod: 5zig_Set, l:5zig_Set
	 * ? PX Mod: PX|Version
	 * @ Better Sprinting: BSprint, l:bsm, l:bsprint
	 * ? Hyperium: hyperium
	 * @ JourneyMap: journeymap_channel, l:world_info
	 * ? Remix: BLC|M
	 * @ Waila - waila
	 * @ World Downloader: + * WorldDownloader
	 * @ World Downloader: WDL|INIT, WDL|CONTROL
	 * ? Easy Minecraft Client: * Subsystem
	 * @ XaeroMinimap: XaeroMinimap
	 * @ InventoryTweaks: InventoryTweaks
	 * @ World Edit CUI: WECUI
	 * ? Vape: LOLIMAHACKER
	 * ? Pixel Client: MC|Pixel
	 * ? Winterware: LC|Brand
	 * @ JEI: JEI
	 * ? Jigsaw: * Vanilla
	 * @ Schematica: + schematica
	 * ? Badlion Client: ??? (S→C: badlion:mods, badlion:cps)
	 * ? Console Client: * Minecraft-Console-Client
	 * ? PvPLounge Client 1.8: * PLC18
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
