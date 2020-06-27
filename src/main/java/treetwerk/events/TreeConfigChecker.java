package treetwerk.events;

import org.bukkit.TreeType;
import org.bukkit.entity.Player;

import treetwerk.main.Main;

public class TreeConfigChecker 
{
	
	Main main;

	public TreeConfigChecker(Main main) 
	{
		this.main = main;
	}
	
	public Boolean treePermissionChecker(Player player, TreeType tree)
	{
		boolean permission = false;
		switch (tree)
		{
			case TREE:
			{
                return(player.hasPermission("treetwerk.trees.OakTree"));
			}					
			case BIG_TREE:
			{
                return(player.hasPermission("treetwerk.trees.BigOakTree"));
			}
			case ACACIA:
			{
                return(player.hasPermission("treetwerk.trees.AcaciaTree"));
			}			
			case BIRCH:
			{
                return(player.hasPermission("treetwerk.trees.BirchTree"));
			}		
			case SMALL_JUNGLE:
			{
                return(player.hasPermission("treetwerk.trees.JungleTree"));
			}		
			case JUNGLE:
			{
                return(player.hasPermission("treetwerk.trees.BigJungleTree"));
			}		
			case DARK_OAK:
			{
                return(player.hasPermission("treetwerk.trees.DarkOakTree"));
			}
			case REDWOOD:
			{
                return(player.hasPermission("treetwerk.trees.SpruceTree"));
			}	
			case MEGA_REDWOOD:
			{
                return(player.hasPermission("treetwerk.trees.BigSpruceTree"));
			}
			case TALL_REDWOOD:
			{
                return(player.hasPermission("treetwerk.trees.TallSpruceTree"));
			}			
			case RED_MUSHROOM:
			{
                return(player.hasPermission("treetwerk.trees.RedMushroomTree"));
			}
			case BROWN_MUSHROOM:
			{
                return(player.hasPermission("treetwerk.trees.BrownMushroomTree"));
			}			
			default:
				permission = false;
				
		}
		return permission;
	}
	
	public Boolean treeConfigChecker(TreeType type)
	{
		boolean check = false;
		switch (type) 
		{
		case TREE:	
			check = main.getConfig().getBoolean("Trees.OakTree");
			break;

		case REDWOOD:
			check = main.getConfig().getBoolean("Trees.SpruceTree");
			break;

		case SMALL_JUNGLE:
			check = main.getConfig().getBoolean("Trees.JungleTree");
			break;

		case JUNGLE:
			check = main.getConfig().getBoolean("Trees.BigJungleTree");
			break;

		case BIRCH:
			check = main.getConfig().getBoolean("Trees.BirchTree");
			break;

		case ACACIA:
			check = main.getConfig().getBoolean("Trees.AcaciaTree");
			break;

		case RED_MUSHROOM:
			check = main.getConfig().getBoolean("Trees.RedMushroomTree");
			break;

		case BROWN_MUSHROOM:
			check = main.getConfig().getBoolean("Trees.BrownMushroomTree");
			break;

		case BIG_TREE:
			check = main.getConfig().getBoolean("Trees.BigOakTree");
			break;

		case MEGA_REDWOOD:
			check = main.getConfig().getBoolean("Trees.BigSpruceTree");
			break;
			
		case DARK_OAK:
			check = main.getConfig().getBoolean("Trees.DarkOakTree");
			break;

		default:
			check = false;
			break;
		}	
		return check;
	}
}
