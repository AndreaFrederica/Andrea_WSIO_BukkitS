package cc.sirrus.andrea.andreawsio.event.websocket;

import cc.sirrus.andrea.register.annotation.WSJsonRouteRegister;
import cn.hutool.json.JSONObject;
import org.bukkit.Bukkit;

public class SendMessage {
    @WSJsonRouteRegister(route = "event_send_message")
    static public void jsonSendMessage(JSONObject json_obj){
        String message = (String) json_obj.get("message");
        Bukkit.broadcastMessage(message);
    }
}
