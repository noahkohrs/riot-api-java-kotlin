package com.noahkohrs.riot.api

/**
 * This class is a replacement for the data class in Kotlin.
 *
 * We don't want to use data classes since componentN() functions:
 * - Appear in Java (should on our opinion be annotated with @JvmSynthetic)
 * - Flood intelligent code completion in IDEs
 * - Make the Api less readable
 *
 * To still benefit from the data class features, we have to implement them manually.
 *
 * > Note: This implementation only covers our use case, which is only really making sense for Immutable data classes.
 * This is an implementation choice we made for Responses.
 */
public abstract class Response {
    /**
     * This function is used to copy the current object cast it to the correct type.
     * The type should nearly always be the same as the original object.
     */
    public fun <T : Response> copyAndCastTo(): T {
        val constructor = this::class.constructors.first()
        val parameters =
            constructor.parameters.map { param ->
                this::class.members.first { it.name == param.name }.call(this)
            }
        return (constructor.call(*parameters.toTypedArray()) as? T)
            ?: throw IllegalArgumentException("The copy block must return the same type as the original object.")
    }

    /**
     * This function is used to copy the current object.
     *
     * > Note: This function is not able to return a proper instance of the object with its correct type and requires casting to access the properties.
     *
     * @see copyAndCastTo
     */
    public fun copy(): Response {
        return copyAndCastTo()
    }

    override fun hashCode(): Int {
        return this::class.members
            .filterIsInstance<kotlin.reflect.KProperty<*>>()
            .map { it.getter.call(this)?.hashCode() ?: 0 }
            .fold(1) { acc, hash -> 31 * acc + hash }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        return this::class.members
            .filterIsInstance<kotlin.reflect.KProperty<*>>()
            .all { it.getter.call(this) == it.getter.call(other as Response) }
    }

    override fun toString(): String {
        val propertiesString =
            this::class.members
                .filterIsInstance<kotlin.reflect.KProperty<*>>()
                .joinToString(", ") { "${it.name}=${it.getter.call(this)}" }
        return "${this::class.simpleName}($propertiesString)"
    }

    /**
     * This function is used to print the object in a more readable format.
     */
    public fun toStringSpaced(): String {
        val propertiesString =
            this::class.members
                .filterIsInstance<kotlin.reflect.KProperty<*>>()
                .joinToString(",\n  ") { "${it.name}=${it.getter.call(this)}" }
        return "${this::class.simpleName}(\n  $propertiesString\n)"
    }
}
