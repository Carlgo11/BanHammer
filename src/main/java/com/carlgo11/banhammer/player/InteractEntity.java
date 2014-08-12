package com.carlgo11.banhammer.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractEntity implements Listener {
    
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e){
        if(e.getRightClicked() instanceof Player){
            Player p = (Player) e.getRightClicked();
        if(e.getPlayer().getItemInHand().getType().equals(Material.GOLD_AXE)){
            if(e.getPlayer().hasPermission("lain.banhammer")){
             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban "+p.getName()+" 1d");
            }
        }else if(e.getPlayer().getItemInHand().getType().equals(Material.STONE_AXE)){
            if(e.getPlayer().hasPermission("lain.banhammer")){
             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick "+p.getName()+" The KickHammer has spoken! Literally...");
            }
        }
        }
    }
    

}
