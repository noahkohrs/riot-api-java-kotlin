internal object TestInternal {
    val apiKey by lazy {
        System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
    }

    val dnsFloppa = Player("DNS Floppa", "007")
    val dnsYotsuba = Player("DNS Yotsuba", "EUW")
    val random = Player("Hantera", "DOUZE") // added to test spectator api, he was in a game at the time of testing

    val somePlayers = listOf(dnsFloppa, dnsYotsuba, random)
}

internal data class Player(
    val name: String,
    val tag: String,
)
