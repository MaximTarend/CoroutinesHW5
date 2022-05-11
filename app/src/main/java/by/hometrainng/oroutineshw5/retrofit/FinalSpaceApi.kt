package by.hometrainng.oroutineshw5.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface FinalSpaceApi {

    @GET("character")
    fun getCharacters(): retrofit2.Call<List<Character>>

    @GET("character/{id}")
    fun getCharacterDetails(
        @Path("id") id: String
    ): retrofit2.Call<Character>


}