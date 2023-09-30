package cc.sirrus.andrea.register;

import cc.sirrus.andrea.andreawsio.event.websocket.AdvanceCheckReturn;
import cc.sirrus.andrea.andreawsio.event.websocket.KickPlayer;
import cc.sirrus.andrea.andreawsio.event.websocket.SendMessage;

public class Config {
    static public Class<?>[] class_list = {
            KickPlayer.class,
            SendMessage.class,
            AdvanceCheckReturn.class
    };
}
