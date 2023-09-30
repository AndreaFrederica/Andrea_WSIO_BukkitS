package cc.sirrus.andrea.andreawsio.event.websocket;

import cc.sirrus.andrea.andreawsio.event.bukkit.OnLogin;
import cc.sirrus.andrea.register.annotation.WSJsonRouteRegister;
import cc.sirrus.andrea.tools.ProjectTools;
import cn.hutool.json.JSONObject;

public class AdvanceCheckReturn {
    @WSJsonRouteRegister(route = "event_advance_check_return")
    static public void jsonAdvanceCheckReturn(JSONObject json_obj){
        boolean result = (Boolean) json_obj.get("result");
        String uuid = (String) json_obj.get("uuid");
        String message = (String) json_obj.get("message");
        //try{
            if(OnLogin.AdvanceCheckRequests.get(uuid).equals("false")){
                if(result){
                    OnLogin.AdvanceCheckRequests.set(uuid,"true");
                }else{
                    OnLogin.AdvanceCheckReasons.set(uuid,message);
                    OnLogin.AdvanceCheckRequests.set(uuid,"error");
                }
            }
        //}catch (Exception e){
        //    ProjectTools.broadcast("AdvanceCheck Error");
        //}
    }
}
