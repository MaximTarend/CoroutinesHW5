package by.hometrainng.oroutineshw5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Character(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "hair")
    val hair: String,
    @ColumnInfo(name = "imageURL")
    @SerializedName("img_url")
    val imageURL: String
)