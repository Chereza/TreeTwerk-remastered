package treetwerk.main;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Event implements Listener 
{
	Main main;

	public Event(Main main) 
	{
		this.main = main;
	}
	
	@EventHandler
	public void PlayerSneakEvent(PlayerToggleSneakEvent e)
	{
		if (!(e.isSneaking()))
			return;
		
		treetwerk.events.NewSneakEvent sneakEvent = new treetwerk.events.NewSneakEvent(main);
		sneakEvent.PlayerSneakEvent(e);	
	}
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e)
	{
        new UpdateChecker(main, 80213).getVersion(version -> 
        {
        	Player p = e.getPlayer();
        	
        	if (!(p.hasPermission("TreeTwerk.*")))
        		return;
        	
            if (main.getDescription().getVersion().equalsIgnoreCase(version)) 
            {
                p.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "There is not a new update available.");
            } else {
                p.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.RED + "There is a new update available.");
            }
        });
	}
}
