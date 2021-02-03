package plugin.midorin.info.dc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import plugin.midorin.info.dc.DisconsolePlugin;
import plugin.midorin.info.dc.util.ChatWebhook;

public class AsyncPlayerChat implements Listener
{

    @EventHandler (priority = EventPriority.MONITOR)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
    {
        if (!getChatWebhook().getWebhook().startsWith("https://discord.com/api/webhooks/") || e.getPlayer() == null || e.getMessage() == null) return;
        if (getConfiguration().getInt("display-format") == 1)
        {
            getChatWebhook().sendMessage(
                    e.getPlayer().getName(),
                    e.getMessage(),
                    "https://mc-heads.net/avatar/" + e.getPlayer().getName() + ".png"
            );
        }
        else if (getConfiguration().getInt("display-format") == 2)
        {
            getChatWebhook().sendMessage(
                    getConfiguration().getString("username"),
                    e.getPlayer().getName() + ": " + e.getMessage(),
                    getConfiguration().getString("avatar")
            );
        }
    }

    public FileConfiguration getConfiguration()
    {
        return DisconsolePlugin.config.getConfiguration();
    }

    public ChatWebhook getChatWebhook()
    {
        return DisconsolePlugin.cw;
    }
}
