package com.slk.workstask.framework.util;

import java.util.Random;

public final class TaskCodeUtil {
    public static String createCode(){
        StringBuilder  code=new StringBuilder("Kl");
        Random random=new Random();
        for(int i=0;i<8;i++){
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
