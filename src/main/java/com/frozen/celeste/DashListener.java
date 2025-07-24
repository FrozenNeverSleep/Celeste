package com.frozen.celeste;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.UUID;

public class DashListener implements Listener {
    private final HashSet<UUID> dashedPlayers = new HashSet<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() != Material.STICK) return;
        if (player.isOnGround()) {
            player.setVelocity(player.getLocation().getDirection().normalize().multiply(1.5));
            return;
        }
        if (dashedPlayers.contains(player.getUniqueId())) return;
        player.setVelocity(player.getLocation().getDirection().normalize().multiply(1.5));
        dashedPlayers.add(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        dashedPlayers.remove(event.getPlayer().getUniqueId());
    }
}
