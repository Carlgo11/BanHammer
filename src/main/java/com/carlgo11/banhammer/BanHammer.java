package com.carlgo11.banhammer;

import com.carlgo11.banhammer.player.Interact;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for BanHammer.
 * @since 1.0
 */
public class BanHammer extends JavaPlugin {

    /**
     * Functions to be called upon boot.
     * @since 1.0
     */
    @Override
    public void onEnable()
    {
        checkConfig();
        getServer().getPluginManager().registerEvents(new Interact(this), this);
    }

    /**
     * Get a variable in the config.
     * @param name Variable name in config.
     * @param inputvar Variable to be replaced with something.
     * @param outputvar Variable to replace the "inputvar" with.
     * @return Returns content of config variable.
     * @since 1.1
     */
    public String getConfigVar(String name, String inputvar, String outputvar)
    {
        String output = this.getConfig().getString(name);
        if (output.toLowerCase().contains(inputvar.toLowerCase())) {
            return output.replaceAll(inputvar, outputvar);
        }
        return "";
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
