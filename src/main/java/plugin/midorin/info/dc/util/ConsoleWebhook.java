package plugin.midorin.info.dc.util;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class ConsoleWebhook
{
    private String webhook;
    private WebhookClient client = null;

    public ConsoleWebhook(String webhook)
    {
        this.webhook = webhook;
        client = WebhookClient.withUrl(webhook);
    }

    public void sendMessage()
    {
        new Thread(() ->
        {
            WebhookMessageBuilder wmb = new WebhookMessageBuilder();
            /*
            wmb
                .setUsername(user)
                .setContent(message);
             */
            client.send(wmb.build());
        }
        ).start();
    }
}
