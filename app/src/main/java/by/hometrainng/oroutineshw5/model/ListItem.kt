package by.hometrainng.oroutineshw5.model

sealed class ListItem {

    data class Item(
        val id: Int = 0
    ): ListItem()

    object Loading: ListItem()
}