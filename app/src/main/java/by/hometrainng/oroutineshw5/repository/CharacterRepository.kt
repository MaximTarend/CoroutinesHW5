package by.hometrainng.oroutineshw5.repository

import by.hometrainng.oroutineshw5.retrofit.FinalSpaceApi
import by.hometrainng.oroutineshw5.retrofit.FinalSpaceService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val finalSpaceApi: FinalSpaceApi) {

    suspend fun getCharacters() = withContext(Dispatchers.IO) {
        FinalSpaceService.provideFinalSpaceApi().getCharacters()
    }

    suspend fun getCharacterDetails(id: Int) = withContext(Dispatchers.IO) {
        FinalSpaceService.provideFinalSpaceApi().getCharacterDetails(id)
    }
}