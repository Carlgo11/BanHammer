package com.carlgo11.banhammer;

import com.carlgo11.banhammer.player.Interact;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Main class for BanHammer.
 *
 * @since 1.0
 */
public class BanHammer extends JavaPlugin {

    /**
     * Functions to be called upon boot.
     *
     * @since 1.0
     */
    @Override
    public void onEnable() {

        checkConfig();
        getServer().getPluginManager().registerEvents(new Interact(this), this);
    }

    /**
     * Create a config if none exists already.
     *
     * @since 1.1
     */
    public void checkConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);

            System.out.println("[BanHammer] No config.yml detected, config.yml created");
        }
    }
}
