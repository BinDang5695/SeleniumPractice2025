package settings.globals;

public class TokenGlobal {

    public static String TOKEN;

    public static String getBearerToken() {
        return "Bearer " + TOKEN;
    }


}
