package by.hometrainng.oroutineshw5.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import by.hometrainng.oroutineshw5.model.Character
import retrofit2.http.Header
import retrofit2.http.Query

interface FinalSpaceApi {

    @GET("character")
    suspend fun getCharacters(
//        @Header("access_key") access_key: String
    ): List<Character>

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Character

}