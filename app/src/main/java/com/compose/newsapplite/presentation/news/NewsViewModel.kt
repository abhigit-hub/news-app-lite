package com.compose.newsapplite.presentation.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.newsapplite.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _uiState = mutableStateOf(NewsUiState())
    val uiState: State<NewsUiState> = _uiState

    private fun initializeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val response1 = newsRepository.getNewsByTrending()
            val response2 = newsRepository.getNewsByCategory()
            withContext(Dispatchers.Main) {
                _uiState.value = NewsUiState(
                    newsByTrendingSize = response1.data?.articles?.size ?: 0,
                    newsByCategorySize = response2.data?.articles?.size ?: 0
                )
            }
        }
    }
}

data class NewsUiState(
    val newsByTrendingSize: Int = 0,
    val newsByCategorySize: Int = 0
)