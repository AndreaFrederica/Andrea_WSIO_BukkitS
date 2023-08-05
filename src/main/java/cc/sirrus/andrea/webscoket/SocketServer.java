package cc.sirrus.andrea.webscoket;

import cc.sirrus.andrea.tools.ProjectTools;
import cc.sirrus.andrea.andreawsio.event.websocket.EventRoute;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class SocketServer extends WebSocketServer {



    public SocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public SocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); // This method sends a message to the new client
        ProjectTools.broadcast("new WS connection: " + handshake
                .getResourceDescriptor()); // This method sends a message to all clients connected
        ProjectTools.broadcast(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the server!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        ProjectTools.broadcast(conn + " has left the server!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {

        //broadcast(message);
        ProjectTools.say(conn + ": " + message);
        //ProjectTools.broadcast(conn + ": " + message);
        EventRoute.eventRoute(message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific
            // websocket
        }

    }

    @Override
    public void onStart() {
        ProjectTools.say("WebSocketServer started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);

    }

}
