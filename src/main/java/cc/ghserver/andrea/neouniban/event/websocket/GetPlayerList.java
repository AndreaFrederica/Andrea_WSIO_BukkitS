package cc.ghserver.andrea.neouniban.event.websocket;

import cc.ghserver.andrea.tools.JsonStringBuilder;
import cc.ghserver.andrea.tools.ProjectTools;

public class GetPlayerList {
    static public void getPlayerList(){
        JsonStringBuilder message_builder = new JsonStringBuilder("dict");
        message_builder.append("type", "return_player_list");
        message_builder.append("data",ProjectTools.getPlayersInfoJsonListBuilder());
        ProjectTools.broadcast(message_builder.toString());
    }
}
