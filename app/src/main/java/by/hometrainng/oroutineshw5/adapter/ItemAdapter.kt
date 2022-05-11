package by.hometrainng.oroutineshw5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.hometrainng.oroutineshw5.databinding.ListItemBinding
import by.hometrainng.oroutineshw5.model.Character
import coil.load

class ItemAdapter(
    context: Context,
    private val onClicked: (Character) -> Unit
): ListAdapter<Character, ItemViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                    binding = ListItemBinding.inflate(layoutInflater, parent, false),
                    onClicked = onClicked
                )
        }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        private val  DIFF_UTIL = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return (oldItem.name == newItem.name && oldItem.imageURL == newItem.imageURL)
            }
        }
    }
}

class ItemViewHolder(
    private val binding: ListItemBinding,
    private val onClicked: (Character) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind (item: Character) {
        with(binding) {
            characterName.text = item.name
            image.load(item.imageURL)

            root.setOnClickListener {
             onClicked(item)
         }
        }
    }
}