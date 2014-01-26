package com.carlgo11.banhammer;

import com.carlgo11.banhammer.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public class BanHammer extends JavaPlugin{
    
    public void onEnable(){
        System.out.println("Domse");
        getServer().getPluginManager().registerEvents(new InteractEntity(), this);
    }

}
