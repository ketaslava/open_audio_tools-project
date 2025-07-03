package com.ktvincco.openaudiotools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ktvincco.openaudiotools.presentation.ModelData
import openaudiotools.app.openaudiotools.generated.resources.Res
import kotlinx.coroutines.*
import org.jetbrains.compose.resources.ExperimentalResourceApi

class Dictionary {
    companion object {

        // Path to the CSV file inside the Compose Multiplatform resources
        private const val CSV_PATH = "files/translations.csv"

        // In-memory storage of translations loaded from CSV
        // Outer map key = original English text
        // Inner map key = language code, value = translated text
        private var translationsDictionary: Map<String, Map<String, String>> = mapOf()
        private var isTranslationsDictionaryLoaded = false

        /**
         * Reads the raw bytes of the CSV resource and decodes them as a UTF-8 string.
         * This function suspends, so it can be called from a coroutine.
         */
        @OptIn(ExperimentalResourceApi::class)
        suspend fun loadCsv(): String {
            // Read file bytes from generated resources
            val bytes = Res.readBytes(CSV_PATH)
            // Convert to UTF-8 String
            return bytes.toString(Charsets.UTF_8)
        }

        /**
         * Parse CSV text into a nested map:
         * - Handles quoted fields with commas and escaped quotes.
         * - First row is header: key,lang1,lang2,...
         * - Subsequent rows: first field is original text, rest are translations.
         */
        private fun parseCsv(csvText: String): Map<String, Map<String, String>> {
            // Split input into non-blank lines
            val lines = csvText.lineSequence()
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toList()

            // Require at least header + one data row
            if (lines.size < 2) return emptyMap()

            // Parse header, using robust CSV splitting
            val headers = splitCsvLine(lines[0])
            val langCodes = headers.drop(1)

            // Parse each subsequent row
            return lines.drop(1)
                .mapNotNull { line ->
                    val cols = splitCsvLine(line)
                    if (cols.isEmpty()) return@mapNotNull null

                    val key = cols[0]
                    val translations = langCodes.mapIndexedNotNull { idx, lang ->
                        cols.getOrNull(idx + 1)
                            ?.takeIf { it.isNotEmpty() }
                            ?.let { lang to it }
                    }.toMap()

                    key to translations
                }
                .toMap()
        }

        /**
         * Splits a single CSV line into fields according to RFC4180:
         * - Fields may be quoted with double quotes.
         * - Inside quoted fields, a pair of double quotes represents a literal quote.
         * - Commas inside quotes are not separators.
         */
        private fun splitCsvLine(line: String): List<String> {
            val result = mutableListOf<String>()
            val current = StringBuilder()
            var inQuotes = false
            var i = 0

            while (i < line.length) {
                val c = line[i]
                when {
                    c == '"' -> {
                        // If we're in quotes and next char is also a quote => escaped quote
                        if (inQuotes && i + 1 < line.length && line[i + 1] == '"') {
                            current.append('"')
                            i++
                        } else {
                            inQuotes = !inQuotes
                        }
                    }
                    c == ',' && !inQuotes -> {
                        // Comma outside quotes -> new field
                        result.add(current.toString())
                        current.clear()
                    }
                    else -> current.append(c)
                }
                i++
            }
            result.add(current.toString())
            return result
        }

        /**
         * On object initialization, launch a coroutine on the IO dispatcher
         * to load and parse the CSV in the background. The resulting map
         * is stored in translationsDictionary.
         */
        init {
            CoroutineScope(Dispatchers.IO).launch {
                val rawCsv = loadCsv()              // Read CSV text
                translationsDictionary = parseCsv(rawCsv)  // Parse into map
            }
        }

        /**
         * Returns the translated text for a given original English phrase and language code.
         * If the dictionary is not yet loaded or the key is missing, returns null.
         * If logging of missing translations is enabled in Configuration, report it via modelData.
         */
        @Composable
        fun getTextTranslation(
            originalText: String,
            modelData: ModelData
        ): String? {

            // If dictionary is still the empty default, consider it "not loaded" yet
            // Update previously untranslated texts when it gonna be loaded
            if (translationsDictionary.isEmpty()) return null
            if (!isTranslationsDictionaryLoaded) {
                isTranslationsDictionaryLoaded = true
                modelData.updateUi() // Update
            }

            // Sanitize original text (to make it similar to key in the dictionary)
            val sanitizedString = originalText.replace("\n", "\\n")

            // Get language code
            val languageCode = modelData.languageCode.collectAsState().value

            // Look up the row for this key
            val row = translationsDictionary[sanitizedString]
            if (row == null) {
                // Log missing translation for further processing
                if (Configuration.getIsEnableAbsentTranslationLogging()) {
                    modelData.reportAbsenceOfTranslation(originalText)
                }
                return null
            }

            // Check if translation is valid
            if (languageCode !in row) { return null }
            if (row[languageCode] == "" || row[languageCode] == null) { return null }
            val translation = row[languageCode]!!

            // Desanitize translation
            val desanitizedTranslation = translation.replace("\\n", "\n")

            // Return translation
            return desanitizedTranslation
        }

        /**
         * Lists the available languages for display in the UI.
         * Each Pair contains the user-visible label and the language code.
         * A special code "original" indicates using the base English text.
         */
        fun getAvailableLanguagesWithLangCodes(): List<Pair<String, String>> {
            return listOf(
                Pair("American (Original) (\uD83C\uDFF3\uFE0F\u200D\uD83C\uDF08)", "original"),
                Pair("AUTO (Follow System)", ""),
                Pair("Espanol", "es"),
                Pair("\uD83D\uDDE3\uFE0F\uD83D\uDD24➡️\uFE0F\uD83D\uDE00", "emoji"),
                Pair("Українська", "uk"),
                Pair("Свободньй Русский", "ru"),
                Pair("Deutsch", "de"),
                Pair("Italiano", "it"),
                Pair("Français", "fr"),
                Pair("Português", "pt"),
                Pair("中文 (简体)", "zh-cn"),
                Pair("ไทย (Thai)", "th"),
                Pair("日本語", "ja"),
                Pair("한국어", "ko"),
                Pair("العربية", "ar"),
                Pair("हिंदी", "hi"),
                Pair("বাংলা", "bn"),
                Pair("Bahasa Indonesia", "id"),
                Pair("اردو", "ur"),
                Pair("Tiếng Việt", "vi"),
                Pair("Türkçe", "tr"),
                Pair("فارسی", "fa"),
                Pair("Kiswahili", "sw"),
                Pair("தமிழ்", "ta")
            )
        }
    }
}
