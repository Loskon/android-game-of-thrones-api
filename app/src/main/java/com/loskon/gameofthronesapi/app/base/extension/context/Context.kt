package com.loskon.gameofthronesapi.app.base.extension.context

import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.color.MaterialColors

fun Context.getColorKtx(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun Context.getFontKtx(@FontRes fontId: Int): Typeface? {
    return ResourcesCompat.getFont(this, fontId)
}

fun Context.getMaterialColorKtx(@AttrRes attrRes: Int): Int {
    return MaterialColors.getColor(this, attrRes, 0)
}

fun Context.getColorControlHighlightKtx(): Int {
    return getMaterialColorKtx(android.R.attr.colorControlHighlight)
}

fun Context.getColorPrimaryKtx(): Int {
    return getMaterialColorKtx(android.R.attr.colorPrimary)
}

fun Context.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}