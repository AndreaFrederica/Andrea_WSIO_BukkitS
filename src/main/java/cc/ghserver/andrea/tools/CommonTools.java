package cc.ghserver.andrea.tools;

import java.util.ArrayList;
import java.util.List;

public class CommonTools {
    public static List<String> getCommandList(String command){
        // 简单的基于空格划分指令
        command += " ";
        StringBuilder temp = new StringBuilder();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < command.length(); i++) {
            if(command.charAt(i) == ' '){
                output.add(temp.toString());
                temp = new StringBuilder();
            }else{
                temp.append(command.charAt(i));
            }
        }
        return output;
    }

    public static String type(Object obj) {
        /*
         *  1. 通过反射获取传来参数的JavaClass对象
         *  2. 获取到JavaClass对象的类型名称
         *  3. 将参数的类型名称返回
         */
        if (obj != null) {
            return obj.getClass().getTypeName();
        } else {
            return "null";
        }
    }
}
