package cc.sirrus.andrea.andreawsio;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 处理玩家输入的指令
        if (label.equalsIgnoreCase("test")) { // 如果指令是/hello
            if (sender instanceof Player) { // 如果发送者是玩家
                Player player = (Player) sender; // 转换为Player对象
                //player.sendMessage("Hello, " + player.getName()); // 向玩家发送一条消息
                return false;
            } else { // 如果发送者不是玩家
                //sender.sendMessage("This command can only be used by players."); // 向发送者发送一条消息
                AndreaWSIO.WS_ServerIO.WebSocketServer.broadcast("test");
                return true;
            }
            //return true; // 返回true表示指令处理成功
        }
        return false; // 返回false表示指令处理失败，会显示plugin.yml中的usage信息
    }
}