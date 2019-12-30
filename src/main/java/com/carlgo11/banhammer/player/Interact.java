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

import java.util.Arrays;
import java.util.Collection;

/**
 * Entity interaction-related functions
 *
 * @since 1.1
 */
public class Interact implements Listener {

    private final BanHammer BanHammer;
    private Collection<PotionEffect> effects = Arrays.asList(new PotionEffect(PotionEffectType.CONFUSION, 2000, 20),
            new PotionEffect(PotionEffectType.SLOW, 2000, 5),
            new PotionEffect(PotionEffectType.BLINDNESS, 2000, 5),
            new PotionEffect(PotionEffectType.WEAKNESS, 2000, 5),
            new PotionEffect(PotionEffectType.WEAKNESS, 2000, 5),
            new PotionEffect(PotionEffectType.POISON, 1000, 5),
            new PotionEffect(PotionEffectType.HUNGER, 1000, 5));

    public Interact(BanHammer plugin) {
        this.BanHammer = plugin;
    }

    /**
     * Function to be called upon player interaction.
     *
     * @param e PlayerInteractEntityEvent.
     * @since 1.0
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            Player user = e.getPlayer();
            Player target = (Player) e.getRightClicked();
            Material itemType = user.getInventory().getItemInMainHand().getType();

            if (itemType == Material.GOLDEN_AXE) {
                banHammer(user, target);
            } else if (itemType == Material.STONE_AXE) {
                kickHammer(user, target);
            } else if (itemType == Material.IRON_AXE) {
                killHammer(user, target);
            }
        }
    }

    private void banHammer(Player user, Player target) {
        if (user.hasPermission("banhammer.ban")) {
            Bukkit.dispatchCommand(user, formatCommand(BanHammer.getConfig().getString("banhammer-command"), target));
        }
    }

    private void kickHammer(Player user, Player target) {
        if (user.hasPermission("banhammer.kick")) {
            Bukkit.dispatchCommand(user, formatCommand(BanHammer.getConfig().getString("kickhammer-command"), target));
        }
    }

    private void killHammer(Player user, Player target) {
        if (user.hasPermission("banhammer.kill")) {
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_VILLAGER_DEATH, 1, 1);
            target.setHealth(target.getHealth() / 2);
            target.addPotionEffects(effects);
        }
    }

    private String formatCommand(String command, Player target) {
        return command.replaceAll("<player>", target.getName());
    }
}
