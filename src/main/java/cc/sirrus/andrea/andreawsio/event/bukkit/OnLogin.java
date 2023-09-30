package cc.sirrus.andrea.andreawsio.event.bukkit;


import cc.sirrus.andrea.andreawsio.Context;
import cc.sirrus.andrea.tools.JsonStringBuilder;
import cc.sirrus.andrea.tools.ProjectTools;
import cn.hutool.core.lang.Dict;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Timer;
import java.util.TimerTask;

public class OnLogin implements Listener {
    public static Dict AdvanceCheckRequests = new Dict();
    public static Dict AdvanceCheckReasons = new Dict();
    public static boolean AdvanceLoginCheck = false;
    @EventHandler
    public void OnPlayerLogin(PlayerLoginEvent event) throws InterruptedException {
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

        //ProjectTools.say("hello " + name);
        //ProjectTools.wsBroadcast(uuid);
        //ProjectTools.wsBroadcast(ip);
        //ProjectTools.wsBroadcast("hello " + name);
        if(AdvanceLoginCheck){
            message_builder.append("players",ProjectTools.getPlayersInfoJsonListBuilder());
            //? 提前发送包含验证信息的事件
            ProjectTools.broadcast(message_builder.toString());
            String uuid = String.valueOf(player.getUniqueId());
            AdvanceCheckRequests.set(uuid,"false");
            // false true error
            Timer timer = new Timer();
            timer.schedule(timeOut(uuid),1000);
            while (true) {
                if (AdvanceCheckRequests.get(uuid).equals("true")) {
                    event.allow();
                    break;
                } else if (AdvanceCheckRequests.get(uuid).equals("error")) {
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, (String) AdvanceCheckReasons.get(uuid));
                    break;
                } else {
                    Thread.sleep(1);
                }
            }
        }else{
            ProjectTools.broadcast(message_builder.toString());
        }
    }

    public static TimerTask timeOut(String uuid) {
        // 创建一个TimerTask对象，继承并重写run方法
        return new TimerTask() {
            @Override
            public void run() {
                AdvanceCheckRequests.set(uuid, "error");
                eventDisallow(uuid, "CheckServer Timeout");
            }
        };
    }
    public static void eventDisallow(String uuid, String reason) {
        AdvanceCheckReasons.set(uuid, reason);
    }
}