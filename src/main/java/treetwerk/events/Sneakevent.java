package treetwerk.events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import treetwerk.main.Main;

public class Sneakevent implements Listener {
	public HashMap<Block, Integer> TwerkCount = new HashMap<Block, Integer>();
	Main main;

	public Sneakevent(Main main) {
		this.main = main;
	}

	private boolean isTreeEnabled(Material material) {

		switch (material) {
		case OAK_SAPLING:
			return main.getConfig().getBoolean("Trees.OakTree");

		case SPRUCE_SAPLING:
			return main.getConfig().getBoolean("Trees.SpruceTree");

		case JUNGLE_SAPLING:
			return main.getConfig().getBoolean("Trees.JungleTree");

		case BIRCH_SAPLING:
			return main.getConfig().getBoolean("Trees.BirchTree");

		case ACACIA_SAPLING:
			return main.getConfig().getBoolean("Trees.AcaciaTree");

		case RED_MUSHROOM:
			return main.getConfig().getBoolean("Trees.RedMushroomTree");

		case BROWN_MUSHROOM:
			return main.getConfig().getBoolean("Trees.BrownMushroomTree");

		default:
			return false;
		}
	}

	@EventHandler
	public void PlayerEvent(PlayerToggleSneakEvent e) {

		if (!(e.isSneaking()))
			return;

		ArrayList<Block> blocks = getNearbySaplings(e.getPlayer());

		for (Block block : blocks) {
			TreeType type = getTreeType(block);

			if (isTreeEnabled(block.getType()))
				GrowTree(block, type, e.getPlayer());

		}

	}

	private ArrayList<Block> getNearbySaplings(Player player) {
		ArrayList<Block> saplings = new ArrayList<Block>();
		int range = main.getConfig().getInt("config.RangeForShifting");
		for (int x = player.getLocation().getBlockX() - range; x <= player.getLocation().getBlockX() + range; x++) {
			for (int y = player.getLocation().getBlockY() - range; y <= player.getLocation().getBlockY() + range; y++) {
				for (int z = player.getLocation().getBlockZ() - range; z <= player.getLocation().getBlockZ() + range; z++) {
					Block block = player.getWorld().getBlockAt(x, y, z);
					if (Tag.SAPLINGS.isTagged(block.getType())
							|| block.getType().equals(Material.BROWN_MUSHROOM)
							|| block.getType().equals(Material.RED_MUSHROOM)) {
						saplings.add(block);
					}
				}
			}
		}
		return saplings;

	}

	private TreeType getTreeType(Block block) {
		switch(block.getType()) {
		case OAK_SAPLING:
			return TreeType.TREE;
		case SPRUCE_SAPLING:
			return TreeType.REDWOOD;
		case JUNGLE_SAPLING:
			return TreeType.SMALL_JUNGLE;
		case BIRCH_SAPLING:
			return TreeType.BIRCH;
		case ACACIA_SAPLING:
			return TreeType.ACACIA;
		case RED_MUSHROOM:
			return TreeType.RED_MUSHROOM;
		case BROWN_MUSHROOM:
			return TreeType.BROWN_MUSHROOM;
		default:
				return null;
		}
	}
	
	private Material getSapling(TreeType treeType) {
		switch(treeType) {
		case TREE:
			return Material.OAK_SAPLING;
		case ACACIA:
			return Material.ACACIA_SAPLING;
		case JUNGLE:
			return Material.JUNGLE_SAPLING;
		case BIRCH:
			return Material.BIRCH_SAPLING;
		case BROWN_MUSHROOM:
			return Material.BROWN_MUSHROOM;
		case RED_MUSHROOM:
			return Material.RED_MUSHROOM;
			default:
				return null;
		}
	}

	private void GrowTree(Block block, TreeType type, Player player) {
		if (!TwerkCount.containsKey(block)) {
			TwerkCount.put(block, 0);
		}

		int newtwerk = TwerkCount.get(block) + 1;
		
		if (newtwerk >= main.getConfig().getInt("config.RequiredTwerkCount")) {
			block.setType(Material.AIR);
			block.getWorld().generateTree(block.getLocation(), type);
			TwerkCount.remove(block);

			if (block.getType().equals(Material.AIR)) {
				
				Material material = getSapling(type);
				
				if(material!=null) {
					block.setType(material);
				}
			}
		} else {
			TwerkCount.put(block, newtwerk);
		}

		if (main.getConfig().getBoolean("config.GrowingParticle")) {
			player.spawnParticle(Particle.SPELL, block.getLocation(), 20, 1.2D, 0D, 1.2D);
		}

	}
}