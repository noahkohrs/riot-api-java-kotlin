import com.noahkohrs.riot.api.RiotApi;
import com.noahkohrs.riot.api.values.Region;

public class JavaExampleMain {
    public static String DnsFloppaPuuid = "OPl5MMfUFkGFwdVrXaFz22kgUmANTcQcFlEDDOPvo3Hd9M00PLeY-4gPjzq8XSP6bb1mzT-iWC3ZzQ";
    public static void main(String[] args) {
        String apiKey = System.getenv("RIOT_API_KEY");
        if (apiKey == null) {
            throw new RuntimeException("RIOT_API_KEY environment variable is not set");
        }
        RiotApi riotApi = new RiotApi(apiKey, Region.EUW1);
        try {
            riotApi.lol.champion.getChampionRotations();
            riotApi.lol.championMastery.getMasteryScoreByPuuid(DnsFloppaPuuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

