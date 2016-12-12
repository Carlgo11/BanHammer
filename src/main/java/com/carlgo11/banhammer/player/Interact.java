package com.carlgo11.banhammer.player;

import com.carlgo11.banhammer.BanHammer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Entity interaction-related functions
 *
 * @since 1.1
 */
public class Interact implements Listener {

    private final BanHammer BanHammer;

    public Interact(BanHammer plugin)
    {
        this.BanHammer = plugin;
    }

    /**
     * Function to be called upon player interaction.
     *
     * @param e PlayerInteractIntityEvent.
     * @since 1.0
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e)
    {
        if (e.getRightClicked() instanceof Player) {
            Player p = (Player) e.getRightClicked();
            if (e.getPlayer().getItemInHand().getType().equals(Material.GOLD_AXE)) {
                if (e.getPlayer().hasPermission("banhammer.ban")) {
                    String command = BanHammer.commands.get("banhammer-command").toString().replaceAll("<player>", p.getName());
                    Bukkit.dispatchCommand(e.getPlayer(), command);
                }
            } else if (e.getPlayer().getItemInHand().getType().equals(Material.STONE_AXE)) {
                if (e.getPlayer().hasPermission("banhammer.kick")) {
                    String command = BanHammer.commands.get("kickhammer-command").toString().replaceAll("<player>", p.getName());
                    Bukkit.dispatchCommand(e.getPlayer(), command);
                }
            } else if (e.getPlayer().getItemInHand().getType().equals(Material.IRON_AXE)) {
                if (e.getPlayer().hasPermission("banhammer.ebola")) {
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_VILLAGER_DEATH, 1, 1);
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
