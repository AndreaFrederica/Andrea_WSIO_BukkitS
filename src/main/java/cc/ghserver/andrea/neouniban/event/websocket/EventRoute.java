package cc.ghserver.andrea.neouniban.event.websocket;

import cc.ghserver.andrea.tools.CommonTools;

import java.util.List;

public class EventRoute {
    static public void eventRoute(String message){
        List<String> command_list = CommonTools.getCommandList(message);
        String commandIndex = command_list.get(0);
        if(commandIndex.equals("kick")){
            // kick <type> <id/uuid> <message>
            KickPlayer.wsKickPlayer(command_list);
        } else if (commandIndex.equals("getPlayerList")) {
            // getPlayerList
            GetPlayerList.getPlayerList();
        }
    }
}
