package com.example.shemajamebeli_10.ui.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shemajamebeli_10.R
import com.example.shemajamebeli_10.databinding.ItemBinding
import com.example.shemajamebeli_10.ui.UIItem


class ItemAdapter() : ListAdapter<UIItem, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    inner class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val source = getItem(absoluteAdapterPosition)
            Glide.with(this.binding.avatar)
                .load(source.avatarImage)
                .into(binding.avatar)
                binding.apply {
                    tvFirstName.text = source.firstName
                    tvLastName.text =source.lastName
                    when(source.messageType){
                        "text" -> {
                            messageTypeImage.visibility = View.GONE
                            tvMesssage.text = source.message
                        }
                        "voice" -> {
                            Glide.with(messageTypeImage)
                                .load(R.drawable.ic_voice)
                                .into(messageTypeImage)
                            messageTypeImage.visibility = View.VISIBLE
                            tvMesssage.text = "           Sent a voice message"
                        }
                        "attachment" -> {
                            Glide.with(messageTypeImage)
                                .load(R.drawable.ic_attachment)
                                .into(messageTypeImage)
                            messageTypeImage.visibility = View.VISIBLE
                            tvMesssage.text = "          Sent an Attachment"
                        }
                    }
                    unreadNumber.text = source.unreadAmount.toString()
                    if(source.unreadAmountVisibility)
                    {unreadNumber.visibility = View.VISIBLE
                        unreadNumberBackground.visibility = View.VISIBLE
                    }
                    else  { unreadNumber.visibility = View.GONE
                    unreadNumberBackground.visibility = View.GONE
                    }
                    if(source.typingVisibility){
                        isTyping1.visibility = View.VISIBLE
                        isTyping2.visibility = View.VISIBLE
                    }
                    else {
                        isTyping1.visibility = View.GONE
                        isTyping2.visibility = View.GONE
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }


}

private class ItemDiffCallback : DiffUtil.ItemCallback<UIItem>() {
    override fun areItemsTheSame(oldItem: UIItem, newItem: UIItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UIItem, newItem: UIItem): Boolean =
        oldItem == newItem

}

