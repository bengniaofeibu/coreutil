package com.jiujiuwisdom.utils;


import com.jiujiuwisdom.base.BaseEnum;

public class ResultUtil {
    public static AppletResult success(Object object){

        AppletResult result = new AppletResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static AppletResult success(){
        AppletResult result = new AppletResult();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static AppletResult success(BaseEnum baseEnum){
        AppletResult result = new AppletResult();
        result.setCode(baseEnum.getCode());
        result.setMsg(baseEnum.getMsg());
        return result;
    }

    public static AppletResult error(BaseEnum baseEnum){
        AppletResult result = new AppletResult();
        result.setCode(baseEnum.getCode());
        result.setMsg(baseEnum.getMsg());
        return result;
    }
}
