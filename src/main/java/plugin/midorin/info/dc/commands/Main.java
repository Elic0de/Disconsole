package plugin.midorin.info.dc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import plugin.midorin.info.dc.DisconsolePlugin;
import plugin.midorin.info.dc.listeners.AsyncPlayerChat;
import plugin.midorin.info.dc.util.ChatWebhook;
import plugin.midorin.info.dc.util.Logs;

public class Main implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args[0].equalsIgnoreCase("reload"))
        {
            DisconsolePlugin.config.reload();
            String chat = getConfiguration().getString("webhooks.chat");
            if (chat.startsWith("https://discord.com/api/webhooks/"))
                DisconsolePlugin.cw = new ChatWebhook(chat);
            sender.sendMessage("Reloaded config file.");
        }
        else if (args[0].equals("1"))
        {
            sender.sendMessage(DisconsolePlugin.config.getConfiguration().getString("webhooks.chat"));
        }
        else if (args[0].equals("2"))
        {
            for (int i=0;i<Logs.getLogList().size();i++)
            {
                sender.sendMessage(Logs.getLogList().get(i));
            }
        }
        return true;
    }

    public FileConfiguration getConfiguration()
    {
        return DisconsolePlugin.config.getConfiguration();
    }
}
