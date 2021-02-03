package plugin.midorin.info.dc;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractDisconsolePlugin extends JavaPlugin implements Listener
{
    protected void registerListeners(Listener... listeners)
    {
        for (Listener listener : listeners) getServer().getPluginManager().registerEvents(listener, this);
    }
}