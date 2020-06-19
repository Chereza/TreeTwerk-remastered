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
				if (player.hasPermission("treetwerk.trees.OakTree"))
					permission = true;
			}					
			case BIG_TREE:
			{
				if (player.hasPermission("treetwerk.trees.BigOakTree"))
					permission = true;
			}
			case ACACIA:
			{
				if (player.hasPermission("treetwerk.trees.AcaciaTree"))
					permission = true;
			}			
			case BIRCH:
			{
				if (player.hasPermission("treetwerk.trees.BirchTree"))
					permission = true;
			}		
			case SMALL_JUNGLE:
			{
				if (player.hasPermission("treetwerk.trees.JungleTree"))
					permission = true;
			}		
			case JUNGLE:
			{
				if (player.hasPermission("treetwerk.trees.BigJungleTree"))
					permission = true;
			}		
			case DARK_OAK:
			{
				if (player.hasPermission("treetwerk.trees.DarkOakTree"))
					permission = true;
			}
			case REDWOOD:
			{
				if (player.hasPermission("treetwerk.trees.SpruceTree"))
					permission = true;
			}	
			case MEGA_REDWOOD:
			{
				if (player.hasPermission("treetwerk.trees.BigSpruceTree"))
					permission = true;
			}
			case TALL_REDWOOD:
			{
				if (player.hasPermission("treetwerk.trees.TallSpruceTree"))
					permission = true;
			}			
			case RED_MUSHROOM:
			{
				if (player.hasPermission("treetwerk.trees.RedMushroomTree"))
					permission = true;
			}
			case BROWN_MUSHROOM:
			{
				if (player.hasPermission("treetwerk.trees.BrownMushroomTree"))
					permission = true;
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
