package by.hometrainng.oroutineshw5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.hometrainng.oroutineshw5.databinding.ListItemBinding
import by.hometrainng.oroutineshw5.databinding.LoadingItemBinding
import by.hometrainng.oroutineshw5.model.ListItem

class ItemAdapter(
    context: Context,
    private val onClicked: (ListItem.Item) -> Unit
): ListAdapter<ListItem, RecyclerView.ViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is ListItem.Item -> TYPE_CHARACTER
            ListItem.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_CHARACTER -> {
                ItemViewHolder(
                    binding = ListItemBinding.inflate(layoutInflater, parent, false),
                    onClicked = onClicked
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder(binding = LoadingItemBinding.inflate(layoutInflater, parent, false))
            }
            else -> {
                error("Unknown View Type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as? ItemViewHolder ?: return
        val item = getItem(position) as? ListItem.Item ?: return

        itemViewHolder.bind(item)
    }

    companion object {

        private const val TYPE_CHARACTER = 0
        private const val TYPE_LOADING = 1

        private val  DIFF_UTIL = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem // TODO уточнить проверку
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class ItemViewHolder(
    private val binding: ListItemBinding,
    private val onClicked: (ListItem.Item) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind (item: ListItem.Item) {
        with(binding) {


         root.setOnClickListener {
             onClicked(item)
         }
        }
    }
}

class LoadingViewHolder(
    binding: LoadingItemBinding
) : RecyclerView.ViewHolder(binding.root)