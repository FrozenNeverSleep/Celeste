package com.frozen.celeste;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class WallJumpListener implements Listener {

    public Block getClosestSolidBlock(Player player, int radius) {
        Location playerLoc = player.getLocation();
        World world = playerLoc.getWorld();
        Block closestBlock = null;
        double closestDistance = Double.MAX_VALUE;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = world.getBlockAt(playerLoc.clone().add(x, y, z));
                    if (!block.getType().isAir()) {
                        Location blockCenter = block.getLocation().add(0.5, 0.5, 0.5);
                        double distance = playerLoc.distance(blockCenter);

                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closestBlock = block;
                        }
                    }
                }
            }
        }

        return closestBlock;
    }


    @EventHandler
    public void onPlayerHit(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Block closest = getClosestSolidBlock(player, 2);
        if (closest == null) return;
        player.sendRawMessage(closest.getLocation().toString());
    }
}
