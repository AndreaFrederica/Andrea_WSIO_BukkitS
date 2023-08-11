package cc.sirrus.andrea.tools;

import cc.sirrus.andrea.andreawsio.AndreaWSIO;
import cn.hutool.core.lang.Dict;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.smartboot.http.common.utils.Constant;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class ProjectTools {
    static public void displayLogo(){
        final char tag = '※';
        String raw_text = ProjectResource.logo_raw_text;
        raw_text += tag;
        char[] text_arr = raw_text.toCharArray();
        StringBuilder line = new StringBuilder();
        for (char i : text_arr){
            if(Objects.equals(i, tag)){
                say(line.toString());
                line = new StringBuilder();
            }else{
                line.append(i);
            }
        }
    }

    static public void say(String message) {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(message);
    }

    static public void wsBroadcast(String message) {
        AndreaWSIO.WS_ServerIO.WebSocketServer.broadcast(message);
    }

    static public void broadcast(String message){
        say(message);
        wsBroadcast(message);
    }

    // 当玩家不存在时返回值为null
    static public Player getPlayerByUuid(Object i_uuid) {
        UUID uuid;
        if (CommonTools.type(i_uuid).equals("java.util.UUID")) {
            uuid = (UUID) i_uuid;
        } else if (CommonTools.type(i_uuid).equals("java.lang.String")) {
            uuid = UUID.fromString(i_uuid.toString());
        } else {
            uuid = null;
        }
        Player neo_player = null;
        if (CommonTools.type(uuid).equals("null")) {
            return null;
        }
        {
            for (Player player : getServer().getOnlinePlayers()) {
                if (player.getUniqueId().equals(uuid)) {
                    neo_player = player;
                }
            }
            return neo_player;
        }
    }

    static public Player getPlayerByName(String name) {
        Player neo_player = null;
        for (Player player : getServer().getOnlinePlayers()) {
            if (player.getName().equals(name)) {
                neo_player = player;
            }
        }
        return neo_player;
    }
    static public Collection<Player> getOnlinePlayers(){
        if(getServer().getOnlinePlayers() != null){
            return (Collection<Player>) getServer().getOnlinePlayers();
        } else {
            return null;
        }
    }
    static public List<Dict> getPlayersInfoList(){
        List<Dict> output = null;
        Collection<Player> players = getOnlinePlayers();
        Dict player_info;
        if(players != null){
            for (Player player : players){
                player_info = new Dict();
                player_info.set("name",player.getName());
                player_info.set("uuid",player.getUniqueId());
                player_info.set("ip",player.getAddress());
            output.add(player_info);
            }
        }
        return output;
    }
    static public String getPlayersInfoJsonListString(){
        JsonStringBuilder output = new JsonStringBuilder("list");
        Collection<Player> players = getOnlinePlayers();
        JsonStringBuilder player_info;
        if (players != null) {
            for (Player player : players){
                player_info = new JsonStringBuilder("dict");
                player_info.append("name",player.getName());
                player_info.append("uuid",player.getUniqueId());
                player_info.append("ip",player.getAddress());
                output.append(player_info);
            }
        }
        return output.toString();
    }
    static public JsonStringBuilder getPlayersInfoJsonListBuilder(){
        JsonStringBuilder output = new JsonStringBuilder("list");
        Collection<Player> players = getOnlinePlayers();
        JsonStringBuilder player_info;
        if (players != null) {
            for (Player player : players){
                player_info = new JsonStringBuilder("dict");
                player_info.append("name",player.getName());
                player_info.append("uuid",player.getUniqueId());
                player_info.append("ip", player.getAddress());
                output.append(player_info);
            }
        }
        return output;
    }
}
