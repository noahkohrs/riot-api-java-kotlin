import com.noahkohrs.riot.api.RiotApi;
import com.noahkohrs.riot.api.account.Account;
import com.noahkohrs.riot.api.values.Platform;

public class JavaExampleMain {

    private static class Player {
        String name;
        String tag;

        Player(String name, String tag) {
            this.name = name;
            this.tag = tag;
        }
    }

    public static void main(String[] args) {
        Player DnsFloppa = new Player("DnsFloppa", "007");
        String apiKey = System.getenv("RIOT_API_KEY");
        if (apiKey == null) {
            throw new RuntimeException("RIOT_API_KEY environment variable is not set");
        }
        RiotApi riotApi = new RiotApi(apiKey, Platform.EUW1);
        try {
            riotApi.lol.champion.getChampionRotations();
            Account DnsFloppaAccount = riotApi.account.getAccountByRiotId(DnsFloppa.name, DnsFloppa.tag);
            String DnsFloppaPuuid = DnsFloppaAccount.puuid;
            riotApi.lol.championMastery.getMasteryScoreByPuuid(DnsFloppaPuuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

