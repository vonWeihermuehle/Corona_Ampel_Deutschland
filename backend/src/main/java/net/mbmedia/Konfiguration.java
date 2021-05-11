package net.mbmedia;

public class Konfiguration {

    //key that uses the android client
    public final static String API_KEY = "";

    //Port auf welchem Spring lauschen soll
    public final static int PORT = 8085;

    //DB User
    public final static String USER = "corona";
    //DB JDBC String
    public final static String JDBC = "jdbc:mysql://localhost:3306/corona?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    //DB Passwort
    public final static String PASSWORD = "";

    //offizielle API URL für Corona Zahlen in Deutschland
    public static final String RKI_URL = "https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/RKI_Landkreisdaten/FeatureServer/0/query?where=1%3D1&outFields=OBJECTID,GEN,BEZ,AGS_0,EWZ,KFL,death_rate,cases,deaths,cases_per_100k,cases_per_population,BL,BL_ID,county,last_update,cases7_per_100k,recovered&returnGeometry=false&outSR=4326&f=json";
}
