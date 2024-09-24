package com.noahkohrs.riot.staticapi.values

public enum class ImageFormat {
    PNG,
    JPG,
    ;

    public companion object {
        public fun parseFormat(url: String): ImageFormat {
            val fileExt = url.substringAfterLast('.').substringBefore('?')
            return when (fileExt) {
                "png" -> PNG
                "jpg" -> JPG
                else -> throw IllegalArgumentException("Unknown image format: $url")
            }
        }
    }
}

public data class Image(
    val url: String,
    val format: ImageFormat,
    val bytes: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (url != other.url) return false
        if (format != other.format) return false
        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + format.hashCode()
        result = 31 * result + bytes.contentHashCode()
        return result
    }
}
