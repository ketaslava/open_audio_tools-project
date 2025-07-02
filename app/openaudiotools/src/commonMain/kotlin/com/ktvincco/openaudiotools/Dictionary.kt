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
                originalText = "Configuration",
                translations = listOf(
                    Translation(
                        languageCode = "ru",
                        text = "Настройки"
                    )
                )
            ),
            TranslatableText(
                originalText = "Loudness",
                translations = listOf(
                    Translation(
                        languageCode = "ru",
                        text = "Громкость"
                    ),
                    Translation(
                        languageCode = "es",
                        text = "Volumen"
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

        fun getAvailableLanguagesWithLangCodes(): List<Pair<String, String>> {
            return listOf(
                Pair("American (Original) (\uD83C\uDFF3\uFE0F\u200D\uD83C\uDF08)", "original"),
                Pair("AUTO (Follow System)", ""),
                Pair("Espanol", "es"),
                Pair("\uD83D\uDDE3\uFE0F\uD83D\uDD24➡\uFE0F\uD83D\uDE00", "emoji"),
                Pair("Українська", "uk"),
                Pair("Свободньй Русский", "ru"),
                Pair("Deutsch", "de"),
                Pair("Italiano", "it"),
                Pair("Português (BR)", "pt-BR"),
                Pair("中文 (简体)", "zh-Hans"),
                Pair("ไทย (Thai)", "th"),
                Pair("日本語", "ja"),
                Pair("한국어", "ko"),
                Pair("العربية", "ar"),
                Pair("हिंदी", "hi"),
                Pair("বাংলা", "bn"),
                Pair("Bahasa Indonesia", "id"),
                Pair("اردو", "ur"),
                Pair("Français", "fr"),
                Pair("Tiếng Việt", "vi"),
                Pair("Türkçe", "tr"),
                Pair("فارسی", "fa"),
                Pair("Kiswahili", "sw"),
                Pair("தமிழ்", "ta"),
            )
        }
    }
}