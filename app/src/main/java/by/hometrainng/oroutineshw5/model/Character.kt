package by.hometrainng.oroutineshw5.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int = 0,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val hair: String,
    @SerializedName("img_url")
    val imageURL: String
)