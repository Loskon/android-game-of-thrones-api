package com.loskon.gameofthronesapi.app.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.app.Character
import com.loskon.gameofthronesapi.databinding.ItemCharacterBinding
import com.loskon.gameofthronesapi.viewbinding.viewBinding

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var list: List<Character> = emptyList()
    private var onItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(parent.viewBinding(ItemCharacterBinding::inflate))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = list[position]

        holder.binding.apply {
            // ImagePicassoLoader.loadImage(character.imageUrl, ivAvatarCard)
            tvNameCard.text = character.fullName
            tvFacultyCard.text = itemCardView.context.getString(R.string.item_character_family, character.family)
            tvActorCard.text = itemCardView.context.getString(R.string.item_character_title, character.title)
            itemCardView.setOnClickListener { onItemClick?.invoke(character) }
        }
    }

    override fun getItemCount(): Int = list.size

    fun setCharacterList(newList: List<Character>) {
        list = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(newClickListener: ((Character) -> Unit)?) {
        onItemClick = newClickListener
    }

    class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)
}