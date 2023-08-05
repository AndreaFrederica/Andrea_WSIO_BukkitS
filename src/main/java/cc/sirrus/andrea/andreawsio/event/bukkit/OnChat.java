package cc.sirrus.andrea.andreawsio.event.bukkit;

import cc.sirrus.andrea.andreawsio.Context;
import cc.sirrus.andrea.tools.JsonStringBuilder;
import cc.sirrus.andrea.tools.ProjectTools;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class OnChat implements Listener {
    @EventHandler
    public void OnPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        JsonStringBuilder message_builder = new JsonStringBuilder("dict");
        message_builder.append("type","event_chat");
        message_builder.append("namespace", Context.namespace);
        message_builder.append("uuid", player.getUniqueId());
        message_builder.append("name",player.getName());
        message_builder.append("ip", player.getAddress());
        message_builder.append("message",message);
        ProjectTools.broadcast(message_builder.toString());
    }
}
