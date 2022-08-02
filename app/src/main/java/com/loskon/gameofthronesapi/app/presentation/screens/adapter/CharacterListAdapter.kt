package com.loskon.gameofthronesapi.app.presentation.screens.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.databinding.ItemCharacterBinding
import com.loskon.gameofthronesapi.domain.model.Character
import com.loskon.gameofthronesapi.utils.ImageLoader
import com.loskon.gameofthronesapi.viewbinding.viewBinding

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private var list: List<Character> = emptyList()
    private var onItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(parent.viewBinding(ItemCharacterBinding::inflate))
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val character = list[position]

        holder.binding.apply {
            ImageLoader.load(ivAvatarCard, character.imageUrl)
            tvNameCard.text = character.fullName
            tvFamilyCard.text = itemCardView.context.getString(R.string.item_character_family, character.family)
            tvTitleCard.text = itemCardView.context.getString(R.string.item_character_title, character.title)
            itemCardView.setOnClickListener { onItemClick?.invoke(character) }
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterList(list: List<Character>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(newClickListener: ((Character) -> Unit)?) {
        onItemClick = newClickListener
    }

    class CharacterListViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)
}