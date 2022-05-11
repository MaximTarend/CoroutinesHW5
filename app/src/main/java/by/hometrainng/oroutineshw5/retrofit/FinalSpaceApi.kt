package by.hometrainng.oroutineshw5.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import by.hometrainng.oroutineshw5.model.Character

interface FinalSpaceApi {

    @GET("character")
    suspend fun getCharacters(): List<Character>

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Character


}