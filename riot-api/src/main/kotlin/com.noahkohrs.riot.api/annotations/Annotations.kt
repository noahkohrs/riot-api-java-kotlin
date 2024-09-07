package com.noahkohrs.riot.api.annotations

/**
 * Marks a part of the API as unstable. This means that the API might change in soon and is still open to discussion. Feel free to use it, but be aware that it might change in the future.
 */
@Retention(AnnotationRetention.BINARY)
public annotation class UnstableApi

/**
 * Indicate can be replaced by a static API call.
 *
 * This annotation is used to indicate that the annotated field can be replaced by a static API call whenever the static API is available.
 *
 * It's a way to keep track of the change implied by the static API.
 */
public annotation class LinkToStaticApi
