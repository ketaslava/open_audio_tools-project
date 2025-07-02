package com.ktvincco.openaudiotools.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.Dictionary
import com.ktvincco.openaudiotools.DynamicText
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.ui.basics.BaseComponents


class SettingsPage (
    private val modelData: ModelData
) {

    @Composable
    fun draw() {

        var isOpenLanguageSelectionMenu by remember { mutableStateOf(false) }
        val availableLanguages = Dictionary.getAvailableLanguagesWithLangCodes()
        var currentLanguage = "Original"
        val currentLanguageCode = modelData.languageCode.collectAsState().value
        for (language in availableLanguages) {
            if (language.second == currentLanguageCode) {
                currentLanguage = language.first
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            val scrollState = rememberScrollState()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state = scrollState)
            ) {

                DynamicText(
                    text = "Settings",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                DynamicText(
                    textByParts = listOf("Language", ":", " ", currentLanguage),
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .clickable { isOpenLanguageSelectionMenu = true }
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)
            }
        }
        AnimatedVisibility(
            isOpenLanguageSelectionMenu,
            enter = slideInVertically(initialOffsetY = {it * 2}) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = {it}) + fadeOut()
        ) {
            languageSelectionMenu (availableLanguages) { selectedLanguageCode ->
                modelData.languageSelected(selectedLanguageCode)
                isOpenLanguageSelectionMenu = false
            }
        }
    }


    @Composable
    fun languageSelectionMenu(
        languages: List<Pair<String, String>>,
        onLanguageSelection: (selectedLanguageCode: String) -> Unit
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = ScrollState(0))
                .background(ColorPalette.getBlockColor()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            DynamicText(
                text = "Select Language",
                modelData = modelData,
                color = ColorPalette.getTextColor(),
                fontSize = 24.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                itemsIndexed(languages) { index, language ->

                    BaseComponents().HorizontalDivider(
                        color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                    menuItem(language.first) {
                        onLanguageSelection.invoke(language.second)
                    }

                    if (languages.isNotEmpty() &&
                        index == languages.size - 1) {
                        BaseComponents().HorizontalDivider(
                            color = ColorPalette.getMarkupColor(), thickness = 1.dp)
                    }
                }
            }
        }
    }


    @Composable
    fun menuItem(text: String, callback: () -> Unit) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .clickable { callback.invoke() }
        ) {
            DynamicText(
                text = text,
                modelData = modelData,
                isTranslatable = false,
                color = ColorPalette.getTextColor(),
                style = MaterialTheme.typography.body1
            )
        }
    }

}