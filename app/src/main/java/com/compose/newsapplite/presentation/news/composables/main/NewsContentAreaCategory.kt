package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.model.CategoryNewsUiState
import com.compose.newsapplite.presentation.model.UserSelectionUiState
import com.compose.newsapplite.presentation.news.composables.common.NewsAppTextBox
import com.compose.newsapplite.ui.theme.NewsTypography

@Composable
fun CategorizedNewsContent(
    modifier: Modifier,
    categoryNewsUiState: CategoryNewsUiState,
    userSelectionUiState: UserSelectionUiState,
    onCategorySelected: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CategoriesRowHeader(
            modifier = Modifier
                .fillMaxHeight(0.1f),
            userSelectionUiState = userSelectionUiState,
            onCategorySelected = onCategorySelected
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f))

        CategoriesContentArea(
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .fillMaxWidth(),
            categoryNewsUiState = categoryNewsUiState
        )
    }
}

@Composable
fun CategoriesRowHeader(
    modifier: Modifier,
    userSelectionUiState: UserSelectionUiState,
    onCategorySelected: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(userSelectionUiState.allNewsCategoryTabList.size) {
            CategoryHeaderItem(
                modifier = modifier,
                headerItemName = userSelectionUiState.allNewsCategoryTabList[it],
                isSelected = it == userSelectionUiState.selectedCategoryIndex,
                onCategorySelected = {
                    onCategorySelected(it)
                }
            )
        }
    }
}

@Composable
fun CategoryHeaderItem(
    modifier: Modifier,
    headerItemName: String,
    isSelected: Boolean,
    onCategorySelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 20.dp)
            .fillMaxHeight()
            .clickable {
                onCategorySelected()
            }
    ) {
        NewsAppTextBox(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = headerItemName,
            textAlign = TextAlign.Start,
            style = if (isSelected) NewsTypography.titleLarge else NewsTypography.titleSmall,
            textColor = if (isSelected) Color.White else Color.LightGray
        )

        Box(
            modifier
                .height(5.dp)
                .width(40.dp)
                .background(color = if (isSelected) Color(0xFFFC8019) else Color.Transparent)
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
fun CategoriesContentArea(
    modifier: Modifier,
    categoryNewsUiState: CategoryNewsUiState
) {
    Box(modifier = modifier.fillMaxSize()) {

    }
}