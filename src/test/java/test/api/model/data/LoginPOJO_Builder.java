package test.api.model.data;

import settings.globals.ConfigsGlobal;
import test.api.model.LoginPOJO;

public class LoginPOJO_Builder {

    public static LoginPOJO getDataLogin(){
        return LoginPOJO.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }

}