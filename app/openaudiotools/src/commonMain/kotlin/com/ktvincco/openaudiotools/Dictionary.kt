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
         * Parses the CSV text into a nested map structure.
         *
         * CSV format assumptions:
         * - The first non-blank, trimmed line is the header row.
         * - Header row columns: "key,lang1,lang2,lang3,..."
         *   where "key" is the original text, and subsequent columns are language codes.
         * - Each following non-blank line is a data row:
         *   - First column = original English text (the lookup key).
         *   - Columns 2...N = translated text for each language in header order.
         *
         * Steps:
         * 1. Split the input text into lines.
         * 2. Trim whitespace and ignore blank lines.
         * 3. If fewer than 2 lines remain, return an empty map (no data).
         * 4. Split the header row on commas to extract language codes.
         * 5. For each subsequent line:
         *    a. Split on commas to get columns.
         *    b. The first column is the lookup key.
         *    c. Zip each remaining column with its corresponding language code.
         *    d. Ignore empty translation cells.
         *    e. Build a map of languageCode -> translation.
         * 6. Return a map of originalText -> (languageCode -> translation).
         */
        private fun parseCsv(csvText: String): Map<String, Map<String, String>> {
            // 1–2. Split into non-blank, trimmed lines
            val lines = csvText.lineSequence()
                .map { it.trim() }          // remove leading/trailing spaces
                .filter { it.isNotEmpty() } // skip empty lines
                .toList()

            // 3. If no header or only header, nothing to parse
            if (lines.size < 2) return emptyMap()

            // 4. Parse header row, splitting on comma to get column names
            val headers = lines[0].split(',').map { it.trim() }
            // Drop the first header ("key") to get just the language codes
            val langCodes = headers.drop(1)

            // 5. Process each data row
            return lines
                .drop(1) // skip header row
                .mapNotNull { line ->
                    // Split the row into columns on comma
                    val cols = line.split(',').map { it.trim() }
                    if (cols.isEmpty()) return@mapNotNull null

                    // a. First column is the lookup key
                    val key = cols[0]

                    // b–e. Build language->translation map for this row
                    val translationsForRow = langCodes
                        .mapIndexedNotNull { idx, lang ->
                            // Attempt to get the corresponding column value
                            cols.getOrNull(idx + 1)
                                // Only include non-empty translations
                                ?.takeIf { it.isNotEmpty() }
                                // Pair the language code with its translation
                                ?.let { lang to it }
                        }
                        .toMap()

                    // Return a pair of key -> translations
                    key to translationsForRow
                }
                // Convert list of pairs into a Map for fast lookup
                .toMap()
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

            // Get language code
            val languageCode = modelData.languageCode.collectAsState().value

            // Look up the row for this key
            val row = translationsDictionary[originalText]
            if (row == null) {
                // Log missing translation for further processing
                if (Configuration.getIsEnableAbsentTranslationLogging()) {
                    modelData.reportAbsenceOfTranslation(originalText)
                }
                return null
            }

            // Return the translation for the specified language code (or null)
            return row[languageCode]
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
                Pair("Tiếng Việt", "vi"),
                Pair("Türkçe", "tr"),
                Pair("فارسی", "fa"),
                Pair("Kiswahili", "sw"),
                Pair("தமிழ்", "ta")
            )
        }
    }
}
