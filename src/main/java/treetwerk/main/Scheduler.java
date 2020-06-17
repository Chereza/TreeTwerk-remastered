package treetwerk.main;

import java.io.File;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import treetwerk.events.Sneakevent;


public class Scheduler 
{

	public void HashMapCleaner()
    {
		new BukkitRunnable() 
        {
              @Override
              public void run() 
              {
            //  	for (Map.Entry<Block, Integer> me : Sneakevent.TwerkCount.entrySet())
            //	{        		
            //  		if (System.currentTimeMillis() > Sneakevent.LastTwerk.get(me.getKey()) + 60000)
            //  		{
            //  			Sneakevent.TwerkCount.remove(me.getKey());
            //  			Sneakevent.LastTwerk.remove(me.getKey());
            //  			org.bukkit.Bukkit.broadcastMessage(me.getKey() + " deleted");
            //  		}                                                  
            //	  }
            	  Sneakevent.TwerkCount.clear();
              	}
            }.runTaskTimer(Main.getInstance(), 20*60*3L, 20*60*3L);
    }
}