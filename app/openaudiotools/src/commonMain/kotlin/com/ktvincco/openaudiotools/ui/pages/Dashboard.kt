package com.ktvincco.openaudiotools.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.DynamicText
import com.ktvincco.openaudiotools.Settings
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler
import com.ktvincco.openaudiotools.ui.basics.BaseComponents
import openaudiotools.app.openaudiotools.generated.resources.Res
import openaudiotools.app.openaudiotools.generated.resources.ktvincco_logo_full_tp
import openaudiotools.app.openaudiotools.generated.resources.ktvincco_logo_mini_tp
import org.jetbrains.compose.resources.painterResource


class Dashboard (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    @Composable
    fun draw() {

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
                    text = modelData.getAppInfo("Name"),
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "V ${modelData.getAppInfo("Version")}",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )

                DynamicText(
                    text = "Measure everything",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "Open Audio Tools is designed to provide objective real-time feedback on your voice, helping you understand and improve it according to your needs.",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                DynamicText(
                    text = "Main Features:\n\n" +
                            "* Real-Time Feedback: Get instant results on pitch, resonance, and more\n\n" +
                            "* Historical Tracking: Save and review past recordings to monitor progress over time\n\n" +
                            "* Singing Feedback: Detailed metrics to enhance your voice\n\n" +
                            "* Gender Voice Tuning: Modify your voice to match desired gender characteristics\n\n" +
                            "* Vocal Confidence Analysis: Measure speed, intonation, rhythm, breath while speech\n\n" +
                            "* Scientific research: do experiments",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )


                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )


                DynamicText(
                    text = "About Open Audio Tools",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "Who is this for?\n\n" +
                            "This app is built for anyone looking to analyze and adjust their voice—whether you're learning to sing, aiming to sound more confident, or exploring vocal transformation. Open Audio Tools caters to everyone interested in gaining deeper insight into their voice.\n" +
                            "\nHow does it work?\n\n" +
                            "The app records your voice and uses advanced algorithms to analyze key vocal patterns, offering detailed feedback and recommendations.\n" +
                            "\nWhy choose Open Audio Tools?\n\n" +
                            "Our cutting-edge approach combines innovative algorithms and user-friendly features to provide an unparalleled voice analysis experience. Whether you're a professional vocalist or simply curious about your voice, this app can help.",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )


                DynamicText(
                    text = "Getting Started",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "* Open the Main Menu\n" +
                            "* Select your goal\n" +
                            "* Tap \"Record\"",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )

                DynamicText(
                    text = "Developers",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                ) {
                    Image(
                        painterResource(
                            Res.drawable.ktvincco_logo_full_tp
                        ),
                        null,
                        modifier = Modifier
                            .height(64.dp)
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Image(
                        painterResource(
                            Res.drawable.ktvincco_logo_mini_tp
                        ),
                        null,
                        modifier = Modifier
                            .height(64.dp)
                    )
                }

                DynamicText(
                    text = "Open Audio Tools is developed by:\n\n" +
                            "   * Ketaslava Ket\n" +
                            "   * KTVINCCO Team",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                DynamicText(
                    text = "Suggestions & Feedback",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "Got an idea or feature request? We're always looking for ways to improve! Send your suggestions to us!",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                DynamicText(
                    text = "Contact Us",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "Official Communication:\n\n" +
                            "   * Email: ktvincco@gmail.com\n\n" +
                            "Preferred Communication:\n\n" +
                            "   * BlueSky: @ketaslava.bsky.social\n\n" +
                            "Join the Community:\n\n" +
                            "   * YouTube: @ketaslava, @ktvincco_production\n" +
                            "   * Instagram: @ketaslava, @ktvincco\n" +
                            "   * Telegram: @ketaslavaket, @ktvincco_production",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                DynamicText(
                    text = "DISCLAIMER",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "This application is provided \"AS IS\" and does not provide any guarantees. The authors of the application does not bear any responsibility for its use. The application does not provide any medical advice",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                DynamicText(
                    text = "User agreement",
                    modelData = modelData,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    color = ColorPalette.getTextColor(),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "By using this application, you agree with our Terms Of Use and Privacy Policy",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                        .fillMaxWidth()
                )

                DynamicText(
                    text = "[Terms Of Use]",
                    modelData = modelData,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = ColorPalette.getTextColor(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEventHandler.openWebLinkButtonCallbackClicked(
                            Settings.getTermsOfUseWebLink()) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
                DynamicText(
                    text = "[Privacy Policy]",
                    modelData = modelData,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = ColorPalette.getTextColor(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEventHandler.openWebLinkButtonCallbackClicked(
                            Settings.getPrivacyPolicyWebLink()) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
                DynamicText(
                    text = "[Legal Info]",
                    modelData = modelData,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = ColorPalette.getTextColor(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { modelData.setLegalInfoScreenState(true) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}