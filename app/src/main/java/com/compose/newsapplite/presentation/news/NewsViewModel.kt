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

    private val _uiState = mutableStateOf(-1)
    val uiState: State<Int> = _uiState

    private fun initializeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = newsRepository.getTopHeadlineNews()
            withContext(Dispatchers.Main) {
                _uiState.value = response.data?.totalResults ?: -2
            }
        }
    }
}