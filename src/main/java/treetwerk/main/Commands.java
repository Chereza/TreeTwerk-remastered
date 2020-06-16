package treetwerk.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


import net.md_5.bungee.api.ChatColor;


public class Commands implements CommandExecutor 
{
	
	private Main main;
	
	Commands(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("TreeTwerk"))
		{
			sender.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "version 0.1.2");
			sender.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "started to reload");				
			main.reloadConfig();
			sender.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "reloaded");		
		}	
		return false;
	}
}