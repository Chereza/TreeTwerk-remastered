package treetwerk.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {  
    
    private void registerCommands(String[] cmds, CommandExecutor cmdExecutor)
    {
        for (String cmd : cmds)
        {
            getCommand(cmd).setExecutor(cmdExecutor);
        }
    }

    public void onEnable()
    {        
    	saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new treetwerk.events.Sneakevent(this), this);
        registerCommands(new String[]{ "TreeTwerk" }, new treetwerk.main.Commands(this));

        
        getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk is enabling!");

        getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk is enabled!");
    }

    public void onDisable()
    {    	
        getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk disabled!");
    }
	
}
