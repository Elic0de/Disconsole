package plugin.midorin.info.dc;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import plugin.midorin.info.dc.commands.Main;
import plugin.midorin.info.dc.listeners.AsyncPlayerChat;
import plugin.midorin.info.dc.listeners.PlayerJoinQuit;
import plugin.midorin.info.dc.listeners.discord.MessageReceived;
import plugin.midorin.info.dc.util.ChatWebhook;
import plugin.midorin.info.dc.util.CustomConfig;

import javax.security.auth.login.LoginException;

public class DisconsolePlugin extends AbstractDisconsolePlugin
{
    public static DisconsolePlugin plugin;
    public static CustomConfig config;

    public static ChatWebhook cw;

    @Override
    public void onEnable()
    {
        super.onEnable();
        plugin = this;
        getCommand("disconsole").setExecutor(new Main());
        registerListeners(
                new AsyncPlayerChat(),
                new PlayerJoinQuit()
        );

        config = new CustomConfig(this);
        config.saveDefault();

        String chat = config.getConfiguration().getString("webhooks.chat");
        if (chat.startsWith("https://discord.com/api/webhooks/"))
        {
            cw = new ChatWebhook(chat);
            cw.sendMessage(
                    config.getConfiguration().getString("username"),
                    config.getConfiguration().getString("server-start"),
                    config.getConfiguration().getString("avatar")
            );
        }

        if (!(config.getConfiguration().getString("token") == null))
        {
            try
            {
                JDABuilder.createDefault(config.getConfiguration().getString("token"))
                        .addEventListeners(new MessageReceived())
                        .setStatus(OnlineStatus.ONLINE)
                        .setActivity(Activity.playing("サーバー絶賛稼動中"))
                        .build();
            }
            catch (LoginException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void onDisable()
    {
        super.onDisable();
        if (cw.getWebhook().startsWith("https://discord.com/api/webhooks/"))
            cw.sendMessage(
                    config.getConfiguration().getString("username"),
                    config.getConfiguration().getString("server-stop"),
                    config.getConfiguration().getString("avatar")
            );
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
    }

    public static DisconsolePlugin getPlugin() { return plugin; }
}
