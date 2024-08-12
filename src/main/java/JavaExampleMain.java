import values.GlobalRegion;
import values.Region;

public class JavaExampleMain {
    public static void main(String[] args) {
        // Create Riot Api
        var riotApi = new RiotApi("RGAPI-3dba4106-7b2b-4485-870a-065481d5e310", Region.EUW1, GlobalRegion.EUROPE);

        // Get Champion Rotation
        var championRotation = riotApi.getChampion().getChampionRotations();
        System.out.println(championRotation);
    }
}
