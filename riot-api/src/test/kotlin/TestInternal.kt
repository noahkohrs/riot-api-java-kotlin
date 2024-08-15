internal object TestInternal {
    val apiKey by lazy {
        System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
    }

    val dnsFloppa = Player("DNS Floppa", "007")
    val dnsYotsuba = Player("DNS Yotsua", "EUW")

    val somePlayers = listOf(dnsFloppa, dnsYotsuba)
}

internal data class Player(
    val name: String,
    val tag: String,
)
