package cc.sirrus.andrea.andreawsio;

import cc.sirrus.andrea.andreawsio.event.bukkit.OnChat;
import cc.sirrus.andrea.andreawsio.event.bukkit.OnLogin;
import cc.sirrus.andrea.register.Register;
import cc.sirrus.andrea.tools.ProjectTools;
import cc.sirrus.andrea.webscoket.WebSocketIO;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AndreaWSIO extends JavaPlugin {
    public static WebSocketIO WS_ServerIO;
    int ws_port = 23080;
    @Override
    public void onEnable() {
        // DisPlayProjectLogo
        ProjectTools.displayLogo();
        // GetConfig
        saveDefaultConfig();
        ws_port = getConfig().getInt("wsport");
        Context.namespace = getConfig().getString("namespace");
        // Context
        Context.context = this;
        // Commands
        Bukkit.getPluginCommand("test").setExecutor(new CommandHandler());
        // Register
        Register register = new Register();
        // Listeners
        Bukkit.getPluginManager().registerEvents(new OnLogin(), this);
        Bukkit.getPluginManager().registerEvents(new OnChat(), this);
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
