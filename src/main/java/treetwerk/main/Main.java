package treetwerk.main;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {  
    
	UpdateChecker updateChecker;

    private static Main instance;
    
    public static Main getInstance()
    {
        return instance;
    }

    private void registerCommands(String[] cmds, CommandExecutor cmdExecutor)
    {
        for (String cmd : cmds)
        {
            getCommand(cmd).setExecutor(cmdExecutor);
        }
    }
    @Override
    public void onLoad() 
    {
        instance = this;
    }
    
    public void onEnable()
    {               
    	getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk is enabling!");
    	
    	saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new treetwerk.main.Event(this), this);
        
        registerCommands(new String[]{ "TreeTwerk" }, new treetwerk.main.Commands(this));

        @SuppressWarnings("unused")
		Metrics metrics = new Metrics(this, 7882);
        
        treetwerk.main.Scheduler Scheduler = new treetwerk.main.Scheduler();
        Scheduler.HashMapCleaner();
        
        getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk is enabled!");
        
        new UpdateChecker(this, 80213).getVersion(version -> 
        {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) 
            {
                getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "There is not a new update available.");
            } else {
                getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.RED + "There is a new update available.");
            }
        });
    }

    public void onDisable()
    {    	
        getLogger().info(ChatColor.DARK_PURPLE + "TreeTwerk disabled!");
    }
	
}
