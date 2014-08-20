package com.carlgo11.banhammer.player;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InteractEntity implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e)
    {
        if (e.getRightClicked() instanceof Player) {
            Player p = (Player) e.getRightClicked();
            if (e.getPlayer().getItemInHand().getType().equals(Material.GOLD_AXE)) {
                if (e.getPlayer().hasPermission("banhammer.kick")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + p.getName() + " 1d");
                }
            } else if (e.getPlayer().getItemInHand().getType().equals(Material.STONE_AXE)) {
                if (e.getPlayer().hasPermission("banhammer.ban")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " The KickHammer has spoken! Literally...");
                }
            } else if (e.getPlayer().getItemInHand().getType().equals(Material.IRON_AXE)) {
                if (e.getPlayer().hasPermission("banhammer.posion")) {
                    p.awardAchievement(Achievement.THE_END);
                    p.getWorld().playSound(p.getLocation(), Sound.VILLAGER_DEATH, 1, 1);
                    p.setHealth(p.getHealth() / 2);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 2000, 20));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2000, 5));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2000, 5));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 2000, 5));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 2000, 5));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1000, 5));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1000, 5));

                }
            }
        }
    }
}
