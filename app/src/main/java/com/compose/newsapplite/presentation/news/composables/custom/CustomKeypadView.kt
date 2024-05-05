package com.compose.newsapplite.presentation.news.composables.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.R
import com.compose.newsapplite.ui.theme.NewsTypography
import com.compose.newsapplite.utils.KeypadConstants

@Composable
fun CustomKeypad(
    modifier: Modifier,
    isCapsLockEnabled: Boolean,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // ROW 1
        KeypadRow(
            modifier = modifier.weight(1f),
            keypadItems = KeypadConstants.getKeypadItems(KeypadConstants.KeypadRow.KEYPAD_ROW_1),
            isCapsLockEnabled = isCapsLockEnabled,
            onKeyChanged = { onKeyChanged(it) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ROW 2
        KeypadRow(
            modifier = modifier.weight(1f),
            keypadItems = KeypadConstants.getKeypadItems(KeypadConstants.KeypadRow.KEYPAD_ROW_2),
            isCapsLockEnabled = isCapsLockEnabled,
            onKeyChanged = { onKeyChanged(it) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ROW 3
        KeypadRow(
            modifier = modifier.weight(1f),
            keypadItems = KeypadConstants.getKeypadItems(KeypadConstants.KeypadRow.KEYPAD_ROW_3),
            isCapsLockEnabled = isCapsLockEnabled,
            onKeyChanged = { onKeyChanged(it) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ROW 4
        KeypadRow(
            modifier = modifier.weight(1f),
            keypadItems = KeypadConstants.getKeypadItems(KeypadConstants.KeypadRow.KEYPAD_ROW_4),
            isCapsLockEnabled = isCapsLockEnabled,
            onKeyChanged = { onKeyChanged(it) }
        )

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun KeypadRow(
    modifier: Modifier,
    keypadItems: List<KeypadConstants.KeypadCharacter>,
    isCapsLockEnabled: Boolean,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        keypadItems.forEach {
            when (it.characterRowMap.second) {
                KeypadConstants.KeypadRow.KEYPAD_ROW_1 -> handleKeypadForRow1And2(it, isCapsLockEnabled, onKeyChanged)
                KeypadConstants.KeypadRow.KEYPAD_ROW_2 -> handleKeypadForRow1And2(it, isCapsLockEnabled, onKeyChanged)
                KeypadConstants.KeypadRow.KEYPAD_ROW_3 -> handleKeypadForRow3(it, isCapsLockEnabled, onKeyChanged)
                KeypadConstants.KeypadRow.KEYPAD_ROW_4 -> handleKeypadForRow4(it, onKeyChanged)
            }
        }
    }
}

@Composable
private fun handleKeypadForRow1And2(
    it: KeypadConstants.KeypadCharacter,
    isCapsLockEnabled: Boolean,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    NormalCharacterKey(
        keypadCharacter = it,
        isCapsLockEnabled = isCapsLockEnabled,
        onKeyChanged = { onKeyChanged(it) }
    )
}

@Composable
private fun handleKeypadForRow3(
    it: KeypadConstants.KeypadCharacter,
    isCapsLockEnabled: Boolean,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    when (it.characterRowMap.first) {
        KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_CAPS.characterRowMap.first -> {
            IconCharacterKey(
                keypadCharacter = it,
                isCapsLockEnabled = isCapsLockEnabled,
                onKeyChanged = { onKeyChanged(it) }
            )
        }

        KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_BACKSPACE.characterRowMap.first -> {
            IconCharacterKey(
                keypadCharacter = it,
                onKeyChanged = { onKeyChanged(it) }
            )
        }

        else -> {
            NormalCharacterKey(
                keypadCharacter = it,
                isCapsLockEnabled = isCapsLockEnabled,
                onKeyChanged = { onKeyChanged(it) }
            )
        }
    }
}

@Composable
private fun handleKeypadForRow4(
    it: KeypadConstants.KeypadCharacter,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    when (it.characterRowMap.first) {
        KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_SPACE.characterRowMap.first -> {
            SpaceBarKey(
                keypadCharacter = it,
                onKeyChanged = { onKeyChanged(it) }
            )
        }

        KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_OK.characterRowMap.first -> {
            IconCharacterKey(
                keypadCharacter = it,
                onKeyChanged = { onKeyChanged(it) }
            )
        }
    }
}

@Composable
fun NormalCharacterKey(
    keypadCharacter: KeypadConstants.KeypadCharacter,
    isCapsLockEnabled: Boolean,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    val key =
        if (isCapsLockEnabled) keypadCharacter.characterRowMap.first.uppercase()
        else keypadCharacter.characterRowMap.first.lowercase()

    Text(
        modifier = Modifier
            .clickable { onKeyChanged(keypadCharacter) }
            .size(48.dp)
            .padding(4.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(size = 10.dp)
            )
        ,
        text = key,
        textAlign = TextAlign.Center,
        style = NewsTypography.headlineLarge,
        color = Color(0xFFFC8019)
    )
}

@Composable
fun SpaceBarKey(
    keypadCharacter: KeypadConstants.KeypadCharacter,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    Text(
        modifier = Modifier
            .clickable { onKeyChanged(keypadCharacter) }
            .height(60.dp)
            .width(400.dp)
            .padding(5.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = Color.DarkGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(size = 10.dp)
            ),
        text = keypadCharacter.characterRowMap.first,
        textAlign = TextAlign.Center,
        style = NewsTypography.displaySmall,
        color = Color(0xFFFC8019).copy(alpha = 0.65f)
    )
}

@Composable
fun IconCharacterKey(
    keypadCharacter: KeypadConstants.KeypadCharacter,
    isCapsLockEnabled: Boolean = false,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    val drawable = when (keypadCharacter) {
        KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_CAPS -> {
            if (isCapsLockEnabled) R.drawable.vd_keypad_capson
            else R.drawable.vd_keypad_capsoff
        }
        KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_BACKSPACE -> {
            R.drawable.vd_keypad_backspace
        }
        KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_OK -> {
            R.drawable.vd_keypad_minimize
        }

        else -> { R.drawable.vd_keypad_minimize }
    }

    Box(
        modifier = Modifier
            .clickable { onKeyChanged(keypadCharacter) }
            .size(60.dp)
            .padding(4.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = Color.DarkGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(size = 10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = drawable),
            tint = Color(0xFFFC8019),
            contentDescription = ""
        )
    }
}