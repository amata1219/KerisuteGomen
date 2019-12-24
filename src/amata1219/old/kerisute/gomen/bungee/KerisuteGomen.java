package amata1219.old.kerisute.gomen.bungee;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class KerisuteGomen extends Plugin implements Listener {

	private static KerisuteGomen plugin;

	@Override
	public void onEnable(){
		plugin = this;
	}

	@Override
	public void onDisable(){

	}

	public static KerisuteGomen getPlugin(){
		return plugin;
	}

}
