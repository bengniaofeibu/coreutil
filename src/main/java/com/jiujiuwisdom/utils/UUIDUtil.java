package com.jiujiuwisdom.utils;

import java.util.UUID;

public final class UUIDUtil {

    /**
     * 生成UUID
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
