package cc.ghserver.andrea.neouniban.event.websocket;


import cc.ghserver.andrea.neouniban.Context;
import cc.ghserver.andrea.tools.ProjectTools;
import cc.ghserver.andrea.tools.CommonTools;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class KickPlayer {
    static public void kickPlayer(String i_type, Object obj, String... i_message){
        String message = i_message.length > 0 ? i_message[0] : "null";
        // type = name / uuid 只在传入对象为String时生效
        // 传入对象不是String时会根据类型判断
        Player player = null;
        if(CommonTools.type(obj).equals("java.lang.String")){
            //ProjectTools.broadcast(i_type);
            if(i_type.equals("name")){
                player = ProjectTools.getPlayerByName((String) obj);
                //ProjectTools.broadcast("type=name");
            }else{
                player = ProjectTools.getPlayerByUuid((String) obj);
                //ProjectTools.broadcast("type=uuid");
            }
        }else if(CommonTools.type(obj).equals("java.util.UUID")){
            player = ProjectTools.getPlayerByUuid((UUID) obj);
            //ProjectTools.broadcast("type=uuid");
        }
        if (player != null) {
            ProjectTools.broadcast("Find player player_name -> " + player.getName() + " player_uuid-> " + player.getUniqueId());
            //ProjectTools.broadcast(message);
            if (player.isOnline()) {
                // 踢掉玩家，并显示消息
                realKickPlayer(player, message);
            }
        }
    }
    static public void wsKickPlayer(List<String> command_list){
        try {
            kickPlayer(command_list.get(1),command_list.get(2), command_list.get(3));
            //ProjectTools.broadcast(command_list.toString() + " -> done");
        }catch(Exception e){
            ProjectTools.broadcast("Error in kick player");
        }

    }
    public static void realKickPlayer(Player player, String message){
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                player.kickPlayer(message);
            }
        }.runTask(Context.context);
    }
}
