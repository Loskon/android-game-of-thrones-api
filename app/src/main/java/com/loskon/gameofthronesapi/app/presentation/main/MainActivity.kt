package com.loskon.gameofthronesapi.app.presentation.main

import androidx.appcompat.app.AppCompatActivity
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.app.base.extension.activity.installTaskDescriptionColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onAttachedToWindow() {
        installTaskDescriptionColor(getColor(android.R.color.white))
        super.onAttachedToWindow()
    }
}