package cc.sirrus.andrea.andreawsio.event.websocket;

import cc.sirrus.andrea.andreawsio.Context;
import cc.sirrus.andrea.tools.CommonTools;
import cc.sirrus.andrea.tools.ProjectTools;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.lang.reflect.Method;
import java.util.List;

public class EventRoute {
    static public void eventRoute(String message){
        // 类型判断
        String temp = String.valueOf(message.charAt(0)) + message.charAt(message.length()-1);
        if(temp.equals("{}") || temp.equals("[]")){
            // 类型为Json
            JSONObject jsonObject = JSONUtil.parseObj(message);
            try{
                Method route_method = (Method) Context.json_route2method.get((String) jsonObject.get("type"));
                ProjectTools.say((String) jsonObject.get("type"));
                route_method.invoke(null,jsonObject);
            } catch (Exception e){
                ProjectTools.say("ERROR IN JSON ROUTE");
                ProjectTools.say(e.getMessage());
                e.printStackTrace();
            }

        }else{
            // 类型为指令
            List<String> command_list = CommonTools.getCommandList(message);
            String commandIndex = command_list.get(0);
            if(commandIndex.equals("kick")) {
                // kick <type> <id/uuid> <message>
                KickPlayer.wsKickPlayer(command_list);
            }else if (commandIndex.equals("getPlayerList")) {
                // getPlayerList
                GetPlayerList.getPlayerList();
            }
        }
    }
}
