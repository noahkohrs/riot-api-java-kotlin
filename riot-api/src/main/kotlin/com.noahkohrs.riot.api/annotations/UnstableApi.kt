package com.noahkohrs.riot.api.annotations

/**
 * Marks a part of the API as unstable. This means that the API might change in soon and is still open to discussion. Feel free to use it, but be aware that it might change in the future.
 */
@Retention(AnnotationRetention.BINARY)
public annotation class UnstableApi()
