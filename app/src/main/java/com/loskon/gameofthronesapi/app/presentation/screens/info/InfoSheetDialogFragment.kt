package com.loskon.gameofthronesapi.app.presentation.screens.info

import android.os.Bundle
import android.view.View
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.app.base.extension.view.setDebounceClickListener
import com.loskon.gameofthronesapi.app.base.presentation.sheetdialogfragment.BaseBottomSheetDialogFragment
import com.loskon.gameofthronesapi.databinding.SheetDialogInfoBinding
import com.loskon.gameofthronesapi.viewbinding.viewBinding

class InfoSheetDialogFragment : BaseBottomSheetDialogFragment(R.layout.sheet_dialog_info) {

    private val binding by viewBinding(SheetDialogInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCloseInfo.setDebounceClickListener { dismiss() }
    }
}