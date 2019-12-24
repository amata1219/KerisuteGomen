package amata1219.old.kerisute.gomen;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import amata1219.old.kerisute.gomen.old.KerisuteGomenOld;
import amata1219.old.kerisute.gomen.old.KerisuteSamurai;

public class KerisuteCommand implements TabExecutor{

	private KerisuteGomenOld plugin;

	public KerisuteCommand(KerisuteGomenOld plugin){
		this.plugin = plugin;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0){
			sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "- KerisuteGomen -");
			sender.sendMessage("");
			sender.sendMessage(ChatColor.WHITE + "Spigot " + plugin.getServer().getBukkitVersion());
			sender.sendMessage(ChatColor.WHITE + "KerisuteGomen " + plugin.getDescription().getVersion());
			sender.sendMessage("");
			sender.sendMessage(ChatColor.WHITE + "/kerisute commands → Display /kerisute command list.");
			sender.sendMessage(ChatColor.GRAY + "Developed by amata1219(Twitter@amata1219).");
			return true;
		}else if(args[0].equalsIgnoreCase("commands")){
			sender.sendMessage(ChatColor.AQUA + "/kerisute");
			sender.sendMessage(ChatColor.WHITE + "Display KerisuteGomen information.");
			sender.sendMessage(ChatColor.AQUA + "/kerisute commands");
			sender.sendMessage(ChatColor.WHITE + "Display /kerisute command list.");
			sender.sendMessage(ChatColor.AQUA + "/kerisute reload");
			sender.sendMessage(ChatColor.WHITE + "Reload the config.");
			sender.sendMessage(ChatColor.AQUA + "/kerisute [true/false]");
			sender.sendMessage(ChatColor.WHITE + "Set whether to display a message when someone is kicked.");
			sender.sendMessage(ChatColor.AQUA + "/kerisute fwhitelist [add/remove/clear] [mod-id]");
			sender.sendMessage(ChatColor.WHITE + "Edit Forge whitelist. If you select [clear], you do not need to enter mod-id.");
			sender.sendMessage(ChatColor.AQUA + "/kerisute lwhitelist [add/remove/clear] [mod-id]");
			sender.sendMessage(ChatColor.WHITE + "Edit LiteLoader whitelist. If you select [clear], you do not need to enter mod-id.");
			return true;
		}else if(args[0].equalsIgnoreCase("reload")){
			plugin.getMainConfig().reloadConfig();
			plugin.getKerisuteSamurai().loadValues();
			sender.sendMessage(ChatColor.AQUA + "Config was reloaded.");
			return true;
		}else if(args[0].equalsIgnoreCase("true")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "Please command in the game.");
				return true;
			}
			Player p = (Player) sender;
			KerisuteSamurai samurai = plugin.getKerisuteSamurai();
			if(samurai.getKickMessageReceiver().contains(p.getName())){
				sender.sendMessage(ChatColor.RED + "You have been already added to [KickMessageReceiver].");
				return true;
			}
			samurai.getKickMessageReceiver().add(p.getName());
			plugin.getMainConfig().getConfig().set("KickMessageReceiver", samurai.getKickMessageReceiver());
			plugin.getMainConfig().updateConfig();
			sender.sendMessage(ChatColor.AQUA + "Added to [KickMessageReceiver].");
			return true;
		}else if(args[0].equalsIgnoreCase("false")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "Please command in the game.");
				return true;
			}
			Player p = (Player) sender;
			KerisuteSamurai samurai = plugin.getKerisuteSamurai();
			if(!samurai.getKickMessageReceiver().contains(p.getName())){
				sender.sendMessage(ChatColor.RED + "You have not been added to [KickMessageReceiver].");
				return true;
			}
			samurai.getKickMessageReceiver().remove(p.getName());
			plugin.getMainConfig().getConfig().set("KickMessageReceiver", samurai.getKickMessageReceiver());
			plugin.getMainConfig().updateConfig();
			sender.sendMessage(ChatColor.AQUA + "Removed from [KickMessageReceiver].");
			return true;
		}else if(args[0].equalsIgnoreCase("fwhitelist")){
			if(args.length == 1){
				sender.sendMessage(ChatColor.RED + "/kerisute fwhitelist [add/remove/clear] [mod-id]");
				return true;
			}else if(args[1].equalsIgnoreCase("add")){
				if(args.length == 2){
					sender.sendMessage(ChatColor.RED + "/kerisute fwhitelist add [mod-id]");
					return true;
				}else if(args.length >= 3){
					StringBuilder sb = new StringBuilder();
					for(int i = 2; i < args.length; i++){
						sb.append(" " + args[i]);
					}
					String id = sb.toString().trim();
					List<String> list = plugin.getKerisuteSamurai().getForgeModIDWhitelist();
					if(list.contains(id)){
						sender.sendMessage(ChatColor.RED + "You have been already added to [Forge.Whitelist].");
						return true;
					}
					list.add(id);
					plugin.getMainConfig().getConfig().set("Forge.Whitelist", list);
					plugin.getMainConfig().updateConfig();
					sender.sendMessage(ChatColor.AQUA + "Added to [Forge.Whitelist].");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("remove")){
				if(args.length == 2){
					sender.sendMessage(ChatColor.RED + "/kerisute fwhitelist remove [mod-id]");
					return true;
				}else if(args.length >= 3){
					StringBuilder sb = new StringBuilder();
					for(int i = 2; i < args.length; i++){
						sb.append(" " + args[i]);
					}
					String id = sb.toString().trim();
					List<String> list = plugin.getKerisuteSamurai().getForgeModIDWhitelist();
					if(!list.contains(id)){
						sender.sendMessage(ChatColor.RED + "You have not been already added to [Forge.Whitelist].");
						return true;
					}
					list.remove(id);
					plugin.getMainConfig().getConfig().set("Forge.Whitelist", list);
					plugin.getMainConfig().updateConfig();
					sender.sendMessage(ChatColor.AQUA + "Removed from [Forge.Whitelist].");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("clear")){
				List<String> list = plugin.getKerisuteSamurai().getForgeModIDWhitelist();
				list.clear();
				plugin.getMainConfig().getConfig().set("Forge.Whitelist", list);
				plugin.getMainConfig().updateConfig();
				sender.sendMessage(ChatColor.AQUA + "Cleared [Forge.Whitelist].");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("lwhitelist")){
			if(args.length == 1){
				sender.sendMessage(ChatColor.RED + "/kerisute lwhitelist [add/remove/clear] [mod-id]");
				return true;
			}else if(args[1].equalsIgnoreCase("add")){
				if(args.length == 2){
					sender.sendMessage(ChatColor.RED + "/kerisute lwhitelist add [mod-id]");
					return true;
				}else if(args.length >= 3){
					StringBuilder sb = new StringBuilder();
					for(int i = 2; i < args.length; i++){
						sb.append(" " + args[i]);
					}
					String id = sb.toString().trim();
					List<String> list = plugin.getKerisuteSamurai().getLiteLoaderModIDWhitelist();
					if(list.contains(id)){
						sender.sendMessage(ChatColor.RED + "You have been already added to [LiteLoader.Whitelist].");
						return true;
					}
					list.add(id);
					plugin.getMainConfig().getConfig().set("LiteLoader.Whitelist", list);
					plugin.getMainConfig().updateConfig();
					sender.sendMessage(ChatColor.AQUA + "Added to [LiteLoader.Whitelist].");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("remove")){
				if(args.length == 2){
					sender.sendMessage(ChatColor.RED + "/kerisute lwhitelist remove [mod-id]");
					return true;
				}else if(args.length >= 3){
					StringBuilder sb = new StringBuilder();
					for(int i = 2; i < args.length; i++){
						sb.append(" " + args[i]);
					}
					String id = sb.toString().trim();
					List<String> list = plugin.getKerisuteSamurai().getLiteLoaderModIDWhitelist();
					if(!list.contains(id)){
						sender.sendMessage(ChatColor.RED + "You have not been already added to [LiteLoader.Whitelist].");
						return true;
					}
					list.remove(id);
					plugin.getMainConfig().getConfig().set("LiteLoader.Whitelist", list);
					plugin.getMainConfig().updateConfig();
					sender.sendMessage(ChatColor.AQUA + "Removed from [LiteLoader.Whitelist].");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("clear")){
				List<String> list = plugin.getKerisuteSamurai().getLiteLoaderModIDWhitelist();
				list.clear();
				plugin.getMainConfig().getConfig().set("LiteLoader.Whitelist", list);
				plugin.getMainConfig().updateConfig();
				sender.sendMessage(ChatColor.AQUA + "Cleared [LiteLoader.Whitelist].");
				return true;
			}
		}
		/*}else if(args[0].equalsIgnoreCase("Forge")){
			if(args.length == 1){

			}else if(args[1].equalsIgnoreCase("CanLogin")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
					boolean b = args[2].equalsIgnoreCase("true");
					if(samurai.allowForgeClientLogin() == b){
						sender.sendMessage(ChatColor.RED + "The same value is already yet.");
						return true;
					}
					config.getConfig().set("Forge.CanLogin", b);
					config.updateConfig();
					samurai.setAllowForgeClientLogin(b);
					sender.sendMessage(ChatColor.AQUA + "[Forge.CanLogin] has been changed to [" + b + "]");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("ModCheck")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
					boolean b = args[2].equalsIgnoreCase("true");
					if(samurai.isForgeModCheck() == b){
						sender.sendMessage(ChatColor.RED + "it is already set.");
						return true;
					}
					config.getConfig().set("Forge.ModCheck", b);
					config.updateConfig();
					samurai.setForgeModCheck(true);
					sender.sendMessage(ChatColor.AQUA + "[Forge.ModCheck] has been changed to [" + b + "]");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("EnableModIDWhitelist")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
					boolean b = args[2].equalsIgnoreCase("true");
					if(samurai.isEnableForgeModIDWhitelist() == b){
						sender.sendMessage(ChatColor.RED + "it is already set.");
						return true;
					}
					config.getConfig().set("Forge.EnableModIDWhitelist", b);
					config.updateConfig();
					samurai.setEnableForgeModIDWhitelist(true);
					sender.sendMessage(ChatColor.AQUA + "[Forge.EnableModIDWhitelist] has been changed to [" + b + "]");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("Whitelist")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("add")){
					if(args.length == 3){

					}else if(args.length >= 4){
						StringBuilder sb = new StringBuilder();
						for(int i = 3; i < args.length; i++){
							sb.append(args[i] + " ");
						}
						String id = sb.toString().trim();
						if(samurai.getForgeModIDWhitelist().contains(id)){
							sender.sendMessage(ChatColor.RED + "it is already included.");
							return true;
						}
						List<String> list = config.getConfig().getStringList("Forge.Whitelist");
						list.add(id);
						config.getConfig().set("Forge.Whitelist", list);
						config.updateConfig();
						samurai.setForgeModIDWhitelist(list);
						sender.sendMessage(ChatColor.AQUA + "Added [" + id + "] to [Forge.Whitelist].");
						return true;
					}
				}else if(args[2].equalsIgnoreCase("remove")){
					if(args.length == 3){

					}else if(args.length >= 4){
						StringBuilder sb = new StringBuilder();
						for(int i = 3; i < args.length; i++){
							sb.append(args[i] + " ");
						}
						String id = sb.toString().trim();
						if(!samurai.getForgeModIDWhitelist().contains(id)){
							sender.sendMessage(ChatColor.RED + "it is not included.");
							return true;
						}
						List<String> list = config.getConfig().getStringList("Forge.Whitelist");
						list.remove(id);
						config.getConfig().set("Forge.Whitelist", list);
						config.updateConfig();
						samurai.setForgeModIDWhitelist(list);
						sender.sendMessage(ChatColor.AQUA + "Removed [" + id + "] from [Forge.Whitelist],");
						return true;
					}
				}else if(args[2].equalsIgnoreCase("clear")){
					List<String> list = config.getConfig().getStringList("Forge.Whitelist");
					list.clear();
					config.getConfig().set("Forge.Whitelist", list);
					config.updateConfig();
					samurai.setForgeModIDWhitelist(list);
					sender.sendMessage(ChatColor.AQUA + "Cleared [Forge.Whitelist].");
					return true;
				}
			}
		}else if(args[0].equalsIgnoreCase("LiteLoader")){
			if(args.length == 1){

			}else if(args[1].equalsIgnoreCase("CanLogin")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
					boolean b = args[2].equalsIgnoreCase("true");
					if(samurai.allowLiteLoaderClientLogin() == b){
						sender.sendMessage(ChatColor.RED + "The same value is already yet.");
						return true;
					}
					config.getConfig().set("LiteLoader.CanLogin", b);
					config.updateConfig();
					samurai.setAllowLiteLoaderClientLogin(b);
					sender.sendMessage(ChatColor.AQUA + "[LiteLoader.CanLogin] has been changed to [" + b + "]");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("ModCheck")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
					boolean b = args[2].equalsIgnoreCase("true");
					if(samurai.isLiteLoaderModCheck() == b){
						sender.sendMessage(ChatColor.RED + "it is already set.");
						return true;
					}
					config.getConfig().set("LiteLoader.ModCheck", b);
					config.updateConfig();
					samurai.setLiteLoaderModCheck(true);
					sender.sendMessage(ChatColor.AQUA + "[LiteLoader.ModCheck] has been changed to [" + b + "]");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("EnableModIDWhitelist")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
					boolean b = args[2].equalsIgnoreCase("true");
					if(samurai.isEnableLiteLoaderModIDWhitelist() == b){
						sender.sendMessage(ChatColor.RED + "it is already set.");
						return true;
					}
					config.getConfig().set("LiteLoader.EnableModIDWhitelist", b);
					config.updateConfig();
					samurai.setEnableLiteLoaderModIDWhitelist(true);
					sender.sendMessage(ChatColor.AQUA + "[LiteLoader.EnableModIDWhitelist] has been changed to [" + b + "]");
					return true;
				}
			}else if(args[1].equalsIgnoreCase("Whitelist")){
				if(args.length == 2){

				}else if(args[2].equalsIgnoreCase("add")){
					if(args.length == 3){

					}else if(args.length >= 4){
						StringBuilder sb = new StringBuilder();
						for(int i = 3; i < args.length; i++){
							sb.append(args[i] + " ");
						}
						String id = sb.toString().trim();
						if(samurai.getLiteLoaderModIDWhitelist().contains(id)){
							sender.sendMessage(ChatColor.RED + "it is already included.");
							return true;
						}
						List<String> list = config.getConfig().getStringList("LiteLoader.Whitelist");
						list.add(id);
						config.getConfig().set("LiteLoader.Whitelist", list);
						config.updateConfig();
						samurai.setLiteLoaderModIDWhitelist(list);
						sender.sendMessage(ChatColor.AQUA + "Added [" + id + "] to [LiteLoader.Whitelist].");
						return true;
					}
				}else if(args[2].equalsIgnoreCase("remove")){
					if(args.length == 3){

					}else if(args.length >= 4){
						StringBuilder sb = new StringBuilder();
						for(int i = 3; i < args.length; i++){
							sb.append(args[i] + " ");
						}
						String id = sb.toString().trim();
						if(!samurai.getLiteLoaderModIDWhitelist().contains(id)){
							sender.sendMessage(ChatColor.RED + "it is not included.");
							return true;
						}
						List<String> list = config.getConfig().getStringList("LiteLoader.Whitelist");
						list.remove(id);
						config.getConfig().set("LiteLoader,Whitelist", list);
						config.updateConfig();
						samurai.setLiteLoaderModIDWhitelist(list);
						sender.sendMessage(ChatColor.AQUA + "Removed [" + id + "] from [LiteLoader.Whitelist],");
						return true;
					}
				}else if(args[2].equalsIgnoreCase("clear")){
					List<String> list = config.getConfig().getStringList("LiteLoader.Whitelist");
					list.clear();
					config.getConfig().set("LiteLoader.Whitelist", list);
					config.updateConfig();
					samurai.setLiteLoaderModIDWhitelist(list);
					sender.sendMessage(ChatColor.AQUA + "Cleared [LiteLoader.Whitelist].");
					return true;
				}
			}
		}*/
		return true;
	}

}
