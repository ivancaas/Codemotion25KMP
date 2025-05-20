package com.kingmakers.codemotion25kmp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class RandomUserDTO(
    val results: List<Result?>?
) {
    @Serializable
    data class Result(
        val cell: String?,
        val email: String?,
        val gender: String?,
        val id: Id?,
        val location: Location?,
        val login: Login?,
        val name: Name?,
        val phone: String?,
        val picture: Picture?,
    ) {
        @Serializable
        data class Id(
            val name: String?,
            val value: String?
        )

        @Serializable
        data class Location(
            val city: String?,
            val coordinates: Coordinates?,
            val country: String?,
            val state: String?,
            val street: Street?,
            val timezone: Timezone?
        ) {
            @Serializable
            data class Coordinates(
                val latitude: String?,
                val longitude: String?
            )

            @Serializable
            data class Street(
                val name: String?,
                val number: Int?
            )

            @Serializable
            data class Timezone(
                val description: String?,
                val offset: String?
            )
        }

        @Serializable
        data class Login(
            val password: String?,
            val username: String?,
            val uuid: String?
        )

        @Serializable
        data class Name(
            val first: String?,
            val last: String?,
            val title: String?
        )

        @Serializable
        data class Picture(
            val large: String?,
            val medium: String?,
            val thumbnail: String?
        )

    }
}