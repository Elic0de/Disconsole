package plugin.midorin.info.dc.listeners.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import plugin.midorin.info.dc.DisconsolePlugin;

public class MessageReceived extends ListenerAdapter
{

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e)
    {
        super.onMessageReceived(e);
        User author = e.getAuthor();
        if (!author.isBot() && !e.isWebhookMessage())
        {
            Message message = e.getMessage();
            TextChannel channel = e.getTextChannel();
            for (Webhook webhook : channel.retrieveWebhooks().complete())
                if (webhook.getUrl().replaceAll("v8/", "").replaceAll("v7/", "").replaceAll("v6/", "").replaceAll("v5/", "").replaceAll("v4/", "").replaceAll("v3/", "").equals(getConfiguration().getString("webhooks.chat")))
                    broadcastMessageToMinecraft(author.getName(), message.getContentRaw());
        }
    }

    public void broadcastMessageToMinecraft(String name, String message)
    {
        Bukkit.getScheduler().runTask(DisconsolePlugin.plugin, () -> {
            Bukkit.broadcastMessage(
                    getConfiguration().getString("messages.chat")
                            .replaceAll("%user", name)
                            .replaceAll("%message", message)
            );
        });
    }

    public FileConfiguration getConfiguration()
    {
        return DisconsolePlugin.config.getConfiguration();
    }
}
