import com.noahkohrs.riot.api.RiotApi;
import com.noahkohrs.riot.api.values.Region;

public class JavaExampleMain {
    public static void main(String[] args) {
        String apiKey = System.getenv("RIOT_API_KEY");
        if (apiKey == null) {
            throw new RuntimeException("RIOT_API_KEY environment variable is not set");
        }
        // Create Riot Api
        RiotApi riotApi = new RiotApi(apiKey, Region.EUW1);

        // Get Champion Rotation
        var championRotation = riotApi.lol.champion.getChampionRotations();
        System.out.println(championRotation);
    }
}
