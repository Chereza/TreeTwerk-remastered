
package treetwerk.events;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import treetwerk.main.Main;

public class Sneakevent implements Listener {
	public static HashMap<Block, Integer> TwerkCount = new HashMap<Block, Integer>();
	public static HashMap<Block, Long> LastTwerk = new HashMap<Block, Long>();
	
	Main main;

	public Sneakevent(Main main) {
		this.main = main;
	}

	private boolean isTreeEnabled(TreeType type, boolean isBigTree) {

		switch (type) {
		case TREE:
			return main.getConfig().getBoolean("Trees.OakTree");

		case REDWOOD:
			return main.getConfig().getBoolean("Trees.SpruceTree");
			
		case SMALL_JUNGLE:
			return main.getConfig().getBoolean("Trees.JungleTree");

		case JUNGLE:
			return main.getConfig().getBoolean("Trees.BigJungleTree");

		case BIRCH:
			return main.getConfig().getBoolean("Trees.BirchTree");

		case ACACIA:
			return main.getConfig().getBoolean("Trees.AcaciaTree");

		case RED_MUSHROOM:
			return main.getConfig().getBoolean("Trees.RedMushroomTree");

		case BROWN_MUSHROOM:
			return main.getConfig().getBoolean("Trees.BrownMushroomTree");
			
		case BIG_TREE:
			return main.getConfig().getBoolean("Trees.BigOakTree");
			
		case MEGA_REDWOOD:
			return main.getConfig().getBoolean("Trees.BigSpruceTree");
			
		case DARK_OAK:
			return main.getConfig().getBoolean("Trees.DarkOakTree");

		default:
			return false;
		}
	}
	
	private TreeType bigToSmallTree(TreeType type) {
		switch (type) {
		case BIG_TREE:
			return TreeType.TREE;
		case MEGA_REDWOOD:
			return TreeType.REDWOOD;
		case JUNGLE:
			return TreeType.SMALL_JUNGLE;
		case DARK_OAK:
		default:
			return null;
		}
	}

	@EventHandler
	public void PlayerEvent(PlayerToggleSneakEvent e) {

		if (!(e.isSneaking()))
			return;

		ArrayList<Block> blocks = getNearbySaplings(e.getPlayer());

		for (Block block : blocks) {

			GrowTree(block, e.getPlayer());

		}

	}

	private ArrayList<Block> getNearbySaplings(Player player) {
		ArrayList<Block> saplings = new ArrayList<Block>();
		int range = main.getConfig().getInt("config.RangeForShifting");
		for (int x = player.getLocation().getBlockX() - range; x <= player.getLocation().getBlockX() + range; x++) {
			for (int y = player.getLocation().getBlockY() - range; y <= player.getLocation().getBlockY() + range; y++) {
				for (int z = player.getLocation().getBlockZ() - range; z <= player.getLocation().getBlockZ()
						+ range; z++) {
					Block block = player.getWorld().getBlockAt(x, y, z);
					if (Tag.SAPLINGS.isTagged(block.getType()) || block.getType().equals(Material.BROWN_MUSHROOM)
							|| block.getType().equals(Material.RED_MUSHROOM)) {
						saplings.add(block);
					}
				}
			}
		}
		return saplings;

	}

	private TreeType getTreeType(Block block, boolean isBigTree) {
		switch (block.getType()) {
		case OAK_SAPLING:
			return TreeType.TREE;
		case SPRUCE_SAPLING:
			return isBigTree ? TreeType.MEGA_REDWOOD : TreeType.REDWOOD;
		case JUNGLE_SAPLING:
			return isBigTree ? TreeType.JUNGLE : TreeType.SMALL_JUNGLE;
		case BIRCH_SAPLING:
			return TreeType.BIRCH;
		case ACACIA_SAPLING:
			return TreeType.ACACIA;
		case DARK_OAK_SAPLING:
			return TreeType.DARK_OAK;
		case RED_MUSHROOM:
			return TreeType.RED_MUSHROOM;
		case BROWN_MUSHROOM:
			return TreeType.BROWN_MUSHROOM;
		default:
			return null;
		}
	}

	@Nullable
	private Block[] getBigTreeBlocks(Block block) {
		// Only Jungle, Spruce and Dark Oak can be big trees
		if(block.getType()!=Material.DARK_OAK_SAPLING
				&& block.getType()!=Material.SPRUCE_SAPLING
				&& block.getType()!=Material.JUNGLE_SAPLING) return null;
		
		Material mat = block.getType();

		BlockFace[][] facesList = {
				{ BlockFace.EAST, BlockFace.NORTH, BlockFace.NORTH_EAST },
				{ BlockFace.EAST, BlockFace.SOUTH, BlockFace.SOUTH_EAST },
				{ BlockFace.WEST, BlockFace.NORTH, BlockFace.NORTH_WEST },
				{ BlockFace.WEST, BlockFace.SOUTH, BlockFace.SOUTH_WEST }
		};
		
		BlockFace[] matchingFaces = null;

		for (BlockFace[] faces : facesList) {
			boolean isPartOfFourSaplings = true;
			for (BlockFace face : faces) {
				if (block.getRelative(face).getType() != mat) {
					isPartOfFourSaplings = false;
					break;
				}
			}
			if (isPartOfFourSaplings) {
				matchingFaces = faces;
				break;
			}
		}
		if(matchingFaces == null) return null;
		
		Block[] result = new Block[4];
		result[0] = block;
		for(int i = 1; i<4; i++) {
			result[i] = block.getRelative(matchingFaces[i-1]);
		}
		return result;
	}

	private Material getSapling(TreeType treeType) {
		switch (treeType) {
		case TREE:
			return Material.OAK_SAPLING;
		case ACACIA:
			return Material.ACACIA_SAPLING;
		case JUNGLE:
		case SMALL_JUNGLE:
			return Material.JUNGLE_SAPLING;
		case BIRCH:
			return Material.BIRCH_SAPLING;
		case BROWN_MUSHROOM:
			return Material.BROWN_MUSHROOM;
		case RED_MUSHROOM:
			return Material.RED_MUSHROOM;
		case REDWOOD:
		case MEGA_REDWOOD:
			return Material.SPRUCE_SAPLING;
		case DARK_OAK:
			return Material.DARK_OAK_SAPLING;
		default:
			return null;
		}
	}
	
	// A big tree has to be spawned at the lowest X and lowest Z coordinates of the 4 saplings
	private Block getRootOfBigTree(Block[] block) {
		int x = block[0].getX();
		int y = block[0].getY();
		int z = block[0].getZ();
		
		for(int i = 1; i<4; i++) {
			if(block[i].getX() < x) x = block[i].getX();
			if(block[i].getY() < y) y = block[i].getY();
		}
		
		return block[0].getWorld().getBlockAt(x, y, z);
	}

	private void GrowTree(Block block, Player player) {
		if (!TwerkCount.containsKey(block)) {
			TwerkCount.put(block, 0);
		}
		
		if (main.getConfig().getBoolean("config.MushroomTreesOnlyOnMycelium")
                && (block.getType().equals(Material.BROWN_MUSHROOM) || block.getType().equals(Material.RED_MUSHROOM))) 
        {
            boolean mycelium = checkForMycelium(block);
            if (mycelium == false)
                return;
        }

		int newtwerk = TwerkCount.get(block) + 1;
		if (newtwerk >= main.getConfig().getInt("config.RequiredTwerkCount")) {
			//System.out.println("debug1");
			
			boolean isBigTree = (getBigTreeBlocks(block)!=null);
			TreeType type = getTreeType(block, isBigTree);
			if(type==null) return;
			//System.out.println("debug2");
			Block[] bigTreeBlocks = getBigTreeBlocks(block);
			
			if(type==TreeType.DARK_OAK && bigTreeBlocks==null) return;
			//System.out.println(type);
			//System.out.println(bigTreeBlocks==null ? "null" : "nonnull");
			//System.out.println("debug3");
			
			// Check if this TreeType is allowed
			if(!isTreeEnabled(type, isBigTree)) {
				//System.out.println(type.name()+" (big: "+isBigTree+") is not allowed! Trying small variant...");
				isBigTree=false;
				bigTreeBlocks=null;
				type = bigToSmallTree(type);
				if(type==null) return;
				if(!isTreeEnabled(type,false)) {
					//System.out.println(type.name()+ " is also not allowed! Quitting.");
					return;
				}
			}
			
			if(isBigTree) {
				for(Block b : bigTreeBlocks) {
					b.setType(Material.AIR);
				}
				block = getRootOfBigTree(bigTreeBlocks);
			} else {
				block.setType(Material.AIR);
			}
			

			
			
			if(!(!isBigTree && type == TreeType.DARK_OAK)) {
				block.getWorld().generateTree(block.getLocation(), type);
			}
			TwerkCount.remove(block);

			if (block.getType().equals(Material.AIR)) {

				Material material = getSapling(type);

				if (material != null) {
					if(isBigTree) {
						for(Block b : bigTreeBlocks) {
							b.setType(material);
						}
					} else {
						block.setType(material);
					}
				}
			}
		} else {
			TwerkCount.put(block, newtwerk);
			//LastTwerk.put(block, System.currentTimeMillis());
		}

		if (main.getConfig().getBoolean("config.GrowingParticle")) {
			player.spawnParticle(Particle.SPELL, block.getLocation(), 20, 1D, 0D, 1D);
		}

	}
	
	private Boolean checkForMycelium(Block block)
	{
		boolean check = false;
		if (block.getRelative(BlockFace.DOWN).getType() == Material.MYCELIUM)
			check = true;
		return check;
	}
}