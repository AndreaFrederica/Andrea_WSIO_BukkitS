package cc.ghserver.andrea.register;

import cc.ghserver.andrea.neouniban.Context;
import cc.ghserver.andrea.register.annotation.WSJsonRouteRegister;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;



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