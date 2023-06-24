package cc.ghserver.andrea.neouniban;

import cc.ghserver.andrea.neouniban.event.bukkit.OnLogin;
import cc.ghserver.andrea.tools.ProjectTools;
import cc.ghserver.andrea.webscoket.WebSocketIO;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Neouniban extends JavaPlugin {
    public static WebSocketIO WS_ServerIO;
    int ws_port = 23080;
    @Override
    public void onEnable() {
        // Context
        Context.context = this;
        // Commands
        Bukkit.getPluginCommand("test").setExecutor(new CommandHandler());
        // Listeners
        Bukkit.getPluginManager().registerEvents(new OnLogin(), this);
        // WebSocketsServer
        WS_ServerIO = new WebSocketIO(ws_port);
        WS_ServerIO.start();
        ProjectTools.say("WebSocketServer Load -> done port:" + ws_port);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WS_ServerIO.stop();
        ProjectTools.say("WebSocketServer End");
    }
}
