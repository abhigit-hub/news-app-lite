package com.compose.newsapplite.presentation.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.newsapplite.domain.repository.NewsRepository
import com.compose.newsapplite.presentation.mapper.toCategoryNewsUiState
import com.compose.newsapplite.presentation.mapper.toTrendingNewsUiState
import com.compose.newsapplite.presentation.model.KeypadUiState
import com.compose.newsapplite.presentation.model.CategoryNewsUiState
import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.model.NewsCategoryType
import com.compose.newsapplite.presentation.model.TrendingNewsUiState
import com.compose.newsapplite.presentation.model.UserSelectionUiState
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

    private lateinit var newsByTrending: CategoryNewsUiState
    private lateinit var newsBySports: CategoryNewsUiState
    private lateinit var newsByPolitics: CategoryNewsUiState
    private lateinit var newsByTechnology: CategoryNewsUiState
    private lateinit var newsByGlobalNews: CategoryNewsUiState
    private lateinit var newsByFitness: CategoryNewsUiState
    private lateinit var newsByMusic: CategoryNewsUiState

    private val _trendingNewsUiState = mutableStateOf(TrendingNewsUiState(trendingNews = emptyList()))
    private val _categoryNewsUiState = mutableStateOf(CategoryNewsUiState(categoryNews = emptyList()))
    private val _selectedArticleUiState = mutableStateOf<NewsArticleUiState?>(null)
    private val _userUiState = mutableStateOf(UserUiState())
    private val _userSelectionUiState = mutableStateOf(UserSelectionUiState())
    private val _keypadUiState = mutableStateOf(KeypadUiState())

    val trendingNewsUiState: State<TrendingNewsUiState> = _trendingNewsUiState
    val categoryNewsUiState: State<CategoryNewsUiState> = _categoryNewsUiState
    val selectedArticleUiState: State<NewsArticleUiState?> = _selectedArticleUiState
    val userUiState: State<UserUiState> = _userUiState
    val userSelectionUiState: State<UserSelectionUiState> = _userSelectionUiState
    val keypadUiState: State<KeypadUiState> = _keypadUiState

    private fun initializeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val response1 = newsRepository.getNewsByTrending()
            val responseForNewsByCategoryTrending = newsRepository.getNewsByCategory(NewsCategoryType.TRENDING)
            val responseForNewsByCategorySports = newsRepository.getNewsByCategory(NewsCategoryType.SPORTS)
            val responseForNewsByCategoryPolitics = newsRepository.getNewsByCategory(NewsCategoryType.POLITICS)
            val responseForNewsByCategoryTechnology = newsRepository.getNewsByCategory(NewsCategoryType.TECHNOLOGY)
            val responseForNewsByCategoryGlobalNews = newsRepository.getNewsByCategory(NewsCategoryType.GLOBAL_NEWS)
            val responseForNewsByCategoryFitness = newsRepository.getNewsByCategory(NewsCategoryType.FITNESS)
            val responseForNewsByCategoryMusic = newsRepository.getNewsByCategory(NewsCategoryType.MUSIC)

            withContext(Dispatchers.Main) {
                // Update Trending News
                _trendingNewsUiState.value = response1.data?.toTrendingNewsUiState()
                    ?: TrendingNewsUiState(trendingNews = listOf())

                // Update Category News by Search
                newsByTrending = responseForNewsByCategoryTrending.data?.toCategoryNewsUiState()
                    ?: CategoryNewsUiState(categoryNews = listOf())
                newsBySports = responseForNewsByCategorySports.data?.toCategoryNewsUiState()
                    ?: CategoryNewsUiState(categoryNews = listOf())
                newsByPolitics = responseForNewsByCategoryPolitics.data?.toCategoryNewsUiState()
                    ?: CategoryNewsUiState(categoryNews = listOf())
                newsByTechnology = responseForNewsByCategoryTechnology.data?.toCategoryNewsUiState()
                    ?: CategoryNewsUiState(categoryNews = listOf())
                newsByGlobalNews = responseForNewsByCategoryGlobalNews.data?.toCategoryNewsUiState()
                    ?: CategoryNewsUiState(categoryNews = listOf())
                newsByFitness = responseForNewsByCategoryFitness.data?.toCategoryNewsUiState()
                    ?: CategoryNewsUiState(categoryNews = listOf())
                newsByMusic = responseForNewsByCategoryMusic.data?.toCategoryNewsUiState()
                    ?: CategoryNewsUiState(categoryNews = listOf())

                refreshNewsByCategory()
            }
        }
    }

    private fun refreshNewsByCategory() {
        when (_userSelectionUiState.value.selectedCategoryIndex) {
            NewsCategoryType.TRENDING.searchIndex ->
                _categoryNewsUiState.value = newsByTrending

            NewsCategoryType.SPORTS.searchIndex ->
                _categoryNewsUiState.value = newsBySports

            NewsCategoryType.POLITICS.searchIndex ->
                _categoryNewsUiState.value = newsByPolitics

            NewsCategoryType.TECHNOLOGY.searchIndex ->
                _categoryNewsUiState.value = newsByTechnology

            NewsCategoryType.GLOBAL_NEWS.searchIndex ->
                _categoryNewsUiState.value = newsByGlobalNews

            NewsCategoryType.FITNESS.searchIndex ->
                _categoryNewsUiState.value = newsByFitness

            NewsCategoryType.MUSIC.searchIndex ->
                _categoryNewsUiState.value = newsByMusic
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

    fun updateUserName() {
        val userName = if (_stringBuilderForKeypad.isEmpty()) "READER" else _stringBuilderForKeypad.toString()

        _userUiState.value = UserUiState(
            userName = userName,
            hasUserEnteredValidName = true
        )
    }

    fun updateSelectedArticle(articleUiState: NewsArticleUiState) {
        _selectedArticleUiState.value = articleUiState
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

    fun updateSelectedCategoryIndex(index: Int) {
        _userSelectionUiState.value = UserSelectionUiState(
            selectedCategoryIndex = index
        )
        refreshNewsByCategory()
    }
}