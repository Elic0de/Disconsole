package plugin.midorin.info.dc.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.midorin.info.dc.DisconsolePlugin;
import plugin.midorin.info.dc.util.ChatWebhook;

public class PlayerJoinQuit implements Listener
{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if (getConfiguration().getString("username") == null)
        {
            System.out.println("ユーザー名を指定してください。");
            return;
        }
        if (!getChatWebhook().getWebhook().startsWith("https://discord.com/api/webhooks/")) return;
        getChatWebhook().sendMessage(
                getConfiguration().getString("username"),
                getConfiguration().getString("messages.join")
                        .replaceAll("%player", e.getPlayer().getName()),
                getConfiguration().getString("avatar")
        );
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        if (getConfiguration().getString("username") == null)
        {
            System.out.println("ユーザー名を指定してください。");
            return;
        }
        if (!getChatWebhook().getWebhook().startsWith("https://discord.com/api/webhooks/")) return;
        getChatWebhook().sendMessage(
                getConfiguration().getString("username"),
                getConfiguration().getString("messages.quit")
                        .replaceAll("%player", e.getPlayer().getName()),
                getConfiguration().getString("avatar")
        );
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
