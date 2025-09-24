package com.anhtester.test.api.model.data;

import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.test.api.model.LoginPOJO;

public class LoginPOJO_Builder {

    public static LoginPOJO getDataLogin(){
        return LoginPOJO.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }

}