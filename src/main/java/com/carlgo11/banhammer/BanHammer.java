package com.carlgo11.banhammer;

import com.carlgo11.banhammer.player.Interact;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for BanHammer.
 * @since 1.0
 */
public class BanHammer extends JavaPlugin {

    /**
     * List of all commands in the config.yml.
     * @since 1.1
     */
    public Map commands = new HashMap();
    
    /**
     * Functions to be called upon boot.
     * @since 1.0
     */
    @Override
    public void onEnable()
    {
        this.commands.put("banhammer-command", this.getConfig().getString("banhammer-command"));
        this.commands.put("kickhammer-command", this.getConfig().getString("kickhammer-command"));
        checkConfig();
        getServer().getPluginManager().registerEvents(new Interact(this), this);
    }

    /**
     * Create a config if none exists already.
     * @since 1.1
     */
    public void checkConfig()
    {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);

            System.out.println("[BanHammer] No config.yml detected, config.yml created");
        }
    }
}
