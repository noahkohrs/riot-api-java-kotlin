import com.noahkohrs.riot.api.RiotApi;
import com.noahkohrs.riot.api.account.Account;
import com.noahkohrs.riot.api.lol.match.TimelineEvent;
import com.noahkohrs.riot.api.lol.match.TimelineEventType;
import com.noahkohrs.riot.api.values.Platform;

import java.util.HashSet;

public class TimelineTestUsage {
    private static class Player {
        String name;
        String tag;

        Player(String name, String tag) {
            this.name = name;
            this.tag = tag;
        }
    }

    public static void main(String[] args) {
        Player DnsFloppa = new Player("DNS Yotsuba", "EUW");
        String apiKey = System.getenv("RIOT_API_KEY");
        if (apiKey == null) {
            throw new RuntimeException("RIOT_API_KEY environment variable is not set");
        }
        RiotApi riotApi = new RiotApi(apiKey, Platform.EUW1);
        try {
            riotApi.lol.champion.getChampionRotations();
            Account DnsFloppaAccount = riotApi.account.getAccountByRiotId(DnsFloppa.name, DnsFloppa.tag);
            String DnsFloppaPuuid = DnsFloppaAccount.puuid;
            var matchs = riotApi.lol.match.getMatchIdsByPuuid(DnsFloppaPuuid, null, null, 420);
            var timeline = riotApi.lol.match.getMatchTimelineById(matchs.getFirst());
            var events = new HashSet<TimelineEventType>();
            timeline.info.frames.forEach(frame -> {
                events.addAll(frame.events.stream().map(event -> event.type).toList());
            });
            // Print each event type recorded in the match.
            events.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
