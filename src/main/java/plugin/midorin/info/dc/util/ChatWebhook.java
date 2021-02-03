package plugin.midorin.info.dc.util;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class ChatWebhook
{
    private String webhook;
    private WebhookClient client = null;

    public ChatWebhook(String webhook)
    {
        this.webhook = webhook;
        client = WebhookClient.withUrl(webhook);
    }

    public void sendMessage(String username, String message, String avatar_url)
    {
        if (client == null) return;
        if (avatar_url.equals("url")) avatar_url = "https://discordapp.com/assets/322c936a8c8be1b803cd94861bdfa868.png";
        String finalAvatar_url = avatar_url;
        new Thread(() ->
        {
            client.send(
                    new WebhookMessageBuilder()
                            .setUsername(username)
                            .setContent(message)
                            .setAvatarUrl(finalAvatar_url)
                            .build()
            );
        }
        ).start();
    }

    public void senEmbed(String username, String avatar_url)
    {
        if (client == null) return;
        new Thread(() ->
        {
            client.send(
                    new WebhookMessageBuilder()
                            .setUsername(username)
                            .setAvatarUrl(avatar_url)
                            .addEmbeds(
                                    new WebhookEmbedBuilder()
                                            .setTitle(new WebhookEmbed.EmbedTitle("a", ""))
                                            .build()
                            )
                            .build()
            );
        }
        ).start();
    }

    public String getWebhook()
    {
        return webhook;
    }
}
