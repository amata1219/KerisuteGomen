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
	 * LabyMod Ghost Client x
	 * CheatBreakerHUD v3 x
	 * Sigma x
	 * Wolfram Client x
	 * Jigsaw
	 * Impact Client
	 * CustomNPCs
	 * Console Client
	 * Schematica Mod
	 * JEI
	 * Winterware
	 * Pixel Client
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
	 * Labymod - LABYMOD
	 * Labymod V3 - LMC
	 * 5zig Mod - 5zig_Set
	 * PX Mod - PX|Version
	 * Better Sprinting - BSprint, l:bsm, l:bsprint
	 * Hyperium - hyperium
	 * JourneyMap - journeymap_channel, l:world_info
	 * Reamix - BLC|M
	 * Waila - waila
	 * World Downloader - REGISTER, minecraft:register
	 * Easy Minecraft Client - MC|Brand, minecraft:brand
	 * Rift - MC|Brand, minecraft: brand
	 * Fabric - MC|Brand, minecraft brand
	 * Forge - MC|Brand, minecraft:brand
	 * World Downloader - MC|Brand, minecraft:brand
	 * Forge Mod Loader - FML|HS, l:fmlhs
	 * XaeroMinimap - XaeroMinimap
	 * InventoryTweaks - InventoryTweaks
	 * World Downloader - WDL|INIT, WDL|CONTROL
	 * World Edit CUI - WECUI
	 * Vape - LOLIMAHCKER
	 * Pixel Client - MC|Pixel
	 * Winterware - LC|Brand
	 * JEI - JEI
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
