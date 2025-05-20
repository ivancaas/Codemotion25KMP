package com.kingmakers.codemotion25kmp.domain.models

import com.kingmakers.codemotion25kmp.data.models.RandomUserDTO

data class RandomUser(
    val results: List<Result>
)

data class Result(
    val cell: String,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val phone: String,
    val picture: Picture,
)

data class Id(
    val name: String,
    val value: String
)

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Street(
    val name: String,
    val number: Int
)

data class Timezone(
    val description: String,
    val offset: String
)

data class Login(
    val password: String,
    val username: String,
    val uuid: String
)

data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

fun RandomUserDTO.Result.toDomain(): Result {
    return Result(
        cell = this.cell ?: "",
        email = this.email ?: "",
        gender = this.gender ?: "",
        id = this.id?.toDomain() ?: Id(name = "", value = ""),
        location = this.location?.toDomain() ?: Location(
            city = "",
            coordinates = Coordinates(latitude = "", longitude = ""),
            country = "",
            state = "",
            street = Street(name = "", number = 0),
            timezone = Timezone(description = "", offset = "")
        ),
        login = this.login?.toDomain() ?: Login(password = "", username = "", uuid = ""),
        name = this.name?.toDomain() ?: Name(first = "", last = "", title = ""),
        phone = this.phone ?: "",
        picture = this.picture?.toDomain() ?: Picture(large = "", medium = "", thumbnail = ""),
    )
}

fun RandomUserDTO.Result.Id.toDomain(): Id {
    return Id(
        name = this.name ?: "",
        value = this.value ?: ""
    )
}

fun RandomUserDTO.Result.Location.toDomain(): Location {
    return Location(
        city = this.city ?: "",
        coordinates = this.coordinates?.toDomain() ?: Coordinates(latitude = "", longitude = ""),
        country = this.country ?: "",
        state = this.state ?: "",
        street = this.street?.toDomain() ?: Street(name = "", number = 0),
        timezone = this.timezone?.toDomain() ?: Timezone(description = "", offset = "")
    )
}

fun RandomUserDTO.Result.Location.Coordinates.toDomain(): Coordinates {
    return Coordinates(
        latitude = this.latitude ?: "",
        longitude = this.longitude ?: ""
    )
}

fun RandomUserDTO.Result.Location.Street.toDomain(): Street {
    return Street(
        name = this.name ?: "",
        number = this.number ?: 0
    )
}

fun RandomUserDTO.Result.Location.Timezone.toDomain(): Timezone {
    return Timezone(
        description = this.description ?: "",
        offset = this.offset ?: ""
    )
}

fun RandomUserDTO.Result.Login.toDomain(): Login {
    return Login(
        password = this.password ?: "",
        username = this.username ?: "",
        uuid = this.uuid ?: ""
    )
}

fun RandomUserDTO.Result.Name.toDomain(): Name {
    return Name(
        first = this.first ?: "",
        last = this.last ?: "",
        title = this.title ?: ""
    )
}

fun RandomUserDTO.Result.Picture.toDomain(): Picture {
    return Picture(
        large = this.large ?: "",
        medium = this.medium ?: "",
        thumbnail = this.thumbnail ?: ""
    )
}

fun RandomUserDTO.toDomain(): RandomUser {
    return RandomUser(
        results = this.results?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
    )
}