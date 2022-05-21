package by.hometrainng.oroutineshw5.retrofit

import by.hometrainng.oroutineshw5.repository.CharacterRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object FinalSpaceService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://finalspaceapi.com/api/v0/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()

    private val finalSpaceApi by lazy { retrofit.create<FinalSpaceApi>() }

    fun provideRepository(): CharacterRepository {
        return CharacterRepository(finalSpaceApi)
    }
}