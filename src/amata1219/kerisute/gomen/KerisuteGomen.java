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
