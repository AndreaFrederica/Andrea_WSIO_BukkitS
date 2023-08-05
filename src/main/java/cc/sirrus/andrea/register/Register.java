package cc.sirrus.andrea.register;

import cc.sirrus.andrea.andreawsio.Context;
import cc.sirrus.andrea.register.annotation.WSJsonRouteRegister;

import java.lang.reflect.Method;


public class Register {
    public Register() {
        for(Class<?> c : Config.class_list){
            Method[] methods = c.getMethods();
            for(Method method : methods) {
                // WSRouteRegister
                try{
                    WSJsonRouteRegister annotation = method.getAnnotation(WSJsonRouteRegister.class);
                    String route = annotation.route();
                    Context.json_route2method.set(route, method);
                }catch (Exception e){
                    // pass
                }
            }
        }
    }

}