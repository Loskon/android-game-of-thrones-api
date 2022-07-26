package com.loskon.gameofthronesapi.app.presentation.screens.characterlist.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.app.base.extension.view.setDebounceClickListener
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
            tvFamilyCard.text = root.context.getString(R.string.item_character_family, character.family)
            tvTitleCard.text = root.context.getString(R.string.item_character_title, character.title)
            root.setDebounceClickListener { onItemClick?.invoke(character) }
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