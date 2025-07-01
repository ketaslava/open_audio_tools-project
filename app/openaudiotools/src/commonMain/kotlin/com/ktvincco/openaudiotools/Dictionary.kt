package com.ktvincco.openaudiotools


class Translation (
    private val languageCode: String,
    private val text: String,
) {
    fun getLanguageCode(): String {
        return languageCode
    }

    fun getText(): String {
        return text
    }
}


class TranslatableText (
    private val originalText: String,
    private val translations: List<Translation>
) {
    fun getOriginalText(): String {
        return originalText
    }

    fun getTranslatedText(languageCode: String): String? {
        for (translation in translations) {
            if (translation.getLanguageCode() == languageCode) {
                return  translation.getText()
            }
        }
        return null
    }
}


class Dictionary (
    private val headline: String,
    private val text: String
) {
    companion object {

        private val translatableTexts = arrayOf(
            TranslatableText(
                originalText = "Spectrum",
                translations = listOf(
                    Translation(
                        languageCode = "ru",
                        text = "Спектр"
                    )
                )
            ),
            TranslatableText(
                originalText = "Settings",
                translations = listOf(
                    Translation(
                        languageCode = "ru",
                        text = "Настройки"
                    )
                )
            )
        )

        fun getTextTranslation(originalText: String, languageCode: String): String? {
            for (translatableText in translatableTexts) {
                if (translatableText.getOriginalText() == originalText) {
                    return  translatableText.getTranslatedText(languageCode)
                }
            }
            return null
        }
    }
}