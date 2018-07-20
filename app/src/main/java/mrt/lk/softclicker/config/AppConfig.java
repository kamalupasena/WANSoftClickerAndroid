package mrt.lk.softclicker.config;

/**
 * Created by Kamal on 7/19/18.
 */

public class AppConfig {

    private static final String TCP_IP_PORT = ":8080/";

    public static final String GET_QUECTIONS = TCP_IP_PORT + "api/quections/search/findAllByActive?a=true";
    public static final String SUBMIT_ANSWERS = TCP_IP_PORT + "/api/answers";

}
