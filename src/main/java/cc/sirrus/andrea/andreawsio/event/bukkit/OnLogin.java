package cc.sirrus.andrea.andreawsio.event.bukkit;


import cc.sirrus.andrea.andreawsio.Context;
import cc.sirrus.andrea.tools.JsonStringBuilder;
import cc.sirrus.andrea.tools.ProjectTools;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class OnLogin implements Listener {
    @EventHandler
    public void OnPlayerJoin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        // player.chat("/help");
        //String name, uuid, ip;
        //name = player.getName();
        //uuid = player.getUniqueId().toString();
        //ip = String.valueOf(player.getAddress());
        /*
        String message = String.format("{" +
                "\"type\":\"event_login\"," +
                "\"uuid\":\"%s\"," +
                "\"name\":\"%s\"," +
                "\"ip\":\"%s\"" +
                "}",uuid,name,ip);

         */
        JsonStringBuilder message_builder =new JsonStringBuilder("dict");
        message_builder.append("type", "event_login");
        message_builder.append("namespace", Context.namespace);
        message_builder.append("uuid", player.getUniqueId());
        message_builder.append("name",player.getName());
        message_builder.append("ip", player.getAddress());

        //ProjectTools.say(message);
        ProjectTools.broadcast(message_builder.toString());
        //ProjectTools.say("hello " + name);
        //ProjectTools.wsBroadcast(uuid);
        //ProjectTools.wsBroadcast(ip);
        //ProjectTools.wsBroadcast("hello " + name);
    }
}