import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

internal object TestInternal {
    val apiKey by lazy {
        System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
    }

    val dnsFloppa = Player("DNS Floppa", "007")
    val dnsYotsuba = Player("DNS Yotsuba", "EUW")

    val somePlayers = listOf(dnsFloppa, dnsYotsuba)
}

/**
 * Returns a test executor with the given number of threads.
 *
 * Typical usage:
 * ```
 *         val testMatchs = getTestMatchsPool()
 *         val threadPool = getTestExecutor()
 *         testMatchs.forEach { matchId ->
 *             threadPool.submit {
 *                 val match = riotApi.lol.match.getMatchById(matchId)
 *                 assertNotNull(match)
 *             }
 *         }
 *         threadPool.waitForCompletion()
 *         ```
 *
 */
fun ThreadPoolExecutor.waitForCompletion() {
    shutdown()
    awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES)
}

/**
 * Returns a test executor with the given number of threads.
 *
 * Typical usage:
 * ```
 *         val testMatchs = getTestMatchsPool()
 *         val threadPool = getTestExecutor(16)
 *         testMatchs.forEach { matchId ->
 *             threadPool.submit {
 *                 val match = riotApi.lol.match.getMatchById(matchId)
 *                 assertNotNull(match)
 *             }
 *         }
 *         threadPool.waitForCompletion()
 *         ```
 *
 */
fun getTestExecutor(numberOfThreads: Int): ThreadPoolExecutor {
    return (Executors.newFixedThreadPool(numberOfThreads) as ThreadPoolExecutor).apply { prestartAllCoreThreads() }
}

/**
 * Returns a test executor with the given number of threads.
 *
 * Typical usage:
 * ```
 *         val testMatchs = getTestMatchsPool()
 *         val threadPool = getTestExecutor()
 *         testMatchs.forEach { matchId ->
 *             threadPool.submit {
 *                 val match = riotApi.lol.match.getMatchById(matchId)
 *                 assertNotNull(match)
 *             }
 *         }
 *         threadPool.waitForCompletion()
 *         ```
 *
 */
fun getTestExecutor(): ThreadPoolExecutor {
    return getTestExecutor(16)
}

internal data class Player(
    val name: String,
    val tag: String,
)
