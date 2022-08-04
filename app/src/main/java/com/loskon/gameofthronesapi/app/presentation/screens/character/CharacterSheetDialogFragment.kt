package com.loskon.gameofthronesapi.app.presentation.screens.character

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.app.base.extension.view.setDebounceClickListener
import com.loskon.gameofthronesapi.app.base.presentation.sheetdialogfragment.BaseBottomSheetDialogFragment
import com.loskon.gameofthronesapi.databinding.SheetDialogCharacterBinding
import com.loskon.gameofthronesapi.utils.ImageLoader
import com.loskon.gameofthronesapi.viewbinding.viewBinding

class CharacterSheetDialogFragment : BaseBottomSheetDialogFragment(R.layout.sheet_dialog_character) {

    private val binding by viewBinding(SheetDialogCharacterBinding::bind)
    private val args: CharacterSheetDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ImageLoader.load(binding.ivAvatarCharacter, args.character.imageUrl)
        binding.tvNameCharacter.text = args.character.fullName
        binding.tvFamilyCharacter.text = args.character.family
        binding.tvTitleCharacter.text = args.character.title
        binding.btnCloseCharacter.setDebounceClickListener { dismiss() }
    }
}