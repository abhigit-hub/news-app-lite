package com.compose.newsapplite.presentation.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.newsapplite.domain.repository.NewsRepository
import com.compose.newsapplite.presentation.model.KeypadUiState
import com.compose.newsapplite.presentation.model.NewsUiState
import com.compose.newsapplite.presentation.model.UserUiState
import com.compose.newsapplite.utils.KeypadConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    companion object {
        val TAG: String = NewsViewModel::class.java.simpleName
    }

    init {
        initializeApiCall()
    }

    private val _stringBuilderForKeypad = StringBuilder()
    private var _isCapsLockEnabled = true
    private var _isKeypadVisible = true

    private val _newsUiState = mutableStateOf(NewsUiState())
    private val _userUiState = mutableStateOf(UserUiState())
    private val _keypadUiState = mutableStateOf(KeypadUiState())
    val newsUiState: State<NewsUiState> = _newsUiState
    val userUiState: State<UserUiState> = _userUiState
    val keypadUiState: State<KeypadUiState> = _keypadUiState

    private fun initializeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val response1 = newsRepository.getNewsByTrending()
            val response2 = newsRepository.getNewsByCategory()
            withContext(Dispatchers.Main) {
                _newsUiState.value = NewsUiState(
                    newsByTrendingSize = response1.data?.articles?.size ?: 0,
                    newsByCategorySize = response2.data?.articles?.size ?: 0
                )
            }
        }
    }

    fun handleOnKeyEvent(keypadCharacter: KeypadConstants.KeypadCharacter) {
        when (keypadCharacter) {
            KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_CAPS -> {
                _isCapsLockEnabled = _isCapsLockEnabled.not()
            }
            KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_SPACE -> {
                _stringBuilderForKeypad.append(" ")
            }
            KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_BACKSPACE -> {
                if (_stringBuilderForKeypad.isNullOrEmpty().not()) {
                    val charArray = _stringBuilderForKeypad.dropLast(1)
                    _stringBuilderForKeypad.clear()
                    _stringBuilderForKeypad.append(charArray)
                }
            }
            KeypadConstants.KeypadCharacter.KEYPAD_CHARACTER_OK -> {
                handleKeypadVisibility(false)
            }
            else -> {
                val key =
                    if (_isCapsLockEnabled) keypadCharacter.characterRowMap.first.uppercase()
                    else keypadCharacter.characterRowMap.first.lowercase()
                _stringBuilderForKeypad.append(key)
            }
        }

        updateKeypadUiState()
    }

    fun handleKeypadVisibility(isVisible: Boolean) {
        _isKeypadVisible = isVisible
        updateKeypadUiState()
    }

    private fun updateKeypadUiState() {
        _keypadUiState.value = KeypadUiState(
            textPad = _stringBuilderForKeypad.toString(),
            isCapsLockEnabled = _isCapsLockEnabled,
            isKeypadVisible = _isKeypadVisible
        )

        _userUiState.value = UserUiState(
            userName = _stringBuilderForKeypad.toString(),
            hasUserEnteredValidName = true
        )
    }
}