package cc.ghserver.andrea.webscoket;

import cc.ghserver.andrea.tools.ProjectTools;

import java.net.UnknownHostException;

public class WebSocketIO {
    public SocketServer WebSocketServer;
    public WebSocketIO(int wsPort) {
        try {
            this.WebSocketServer = new SocketServer(wsPort);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(){
        this.WebSocketServer.start();
        ProjectTools.say("ChatServer started on port: " + WebSocketServer.getPort());
    }
    public void stop(){
        try {
            this.WebSocketServer.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}