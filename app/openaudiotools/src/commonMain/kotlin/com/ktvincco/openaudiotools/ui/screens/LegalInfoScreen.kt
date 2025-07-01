package com.ktvincco.openaudiotools.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import openaudiotools.app.openaudiotools.generated.resources.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource


class LegalInfoScreen (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    @Composable
    fun draw() {
        val scrollState = rememberScrollState()

        // Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
                .padding(horizontal = 15.dp)
        ) {
            // Top bar (close button)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clickable { modelData.setLegalInfoScreenState(false) }
                    .padding(horizontal = 5.dp)
            ) {
                Image(
                    painterResource(Res.drawable.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                    null,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }

            // Content

            DynamicText(
                text = "Legal Info",
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
                        "   * Email: ktvincco@gmail.com",
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

            Spacer(modifier = Modifier.height(12.dp))

            BaseComponents().HorizontalDivider(
                color = ColorPalette.getMarkupColor(), thickness = 1.dp)

            DynamicText(
                text = "License NOTICE",
                modelData = modelData,
                color = ColorPalette.getTextColor(),
                fontSize = 20.sp,
                lineHeight = 30.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            )

            DynamicText(
                text = "This application was developed by Ketaslava Ket and KTVINCCO Team\n\n" +
                        "The application is distributed under GNU GENERAL PUBLIC LICENSE Version 3.0",
                modelData = modelData,
                color = ColorPalette.getTextColor(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                    .fillMaxWidth()
            )

            DynamicText(
                text = "[License]",
                modelData = modelData,
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = ColorPalette.getTextColor(),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { uiEventHandler.openWebLinkButtonCallbackClicked(
                        Settings.getLicenseWebLink()) }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            DynamicText(
                text = "[Source Code]",
                modelData = modelData,
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = ColorPalette.getTextColor(),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { uiEventHandler.openWebLinkButtonCallbackClicked(
                        Settings.getSourceCodeWebLink()) }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            DynamicText(
                text = "This app contains:\n\n" +
                    "    * Google icons (Apache License Version 2.0)",
                modelData = modelData,
                color = ColorPalette.getTextColor(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp, top = 12.dp)
                    .fillMaxWidth()
            )

            BaseComponents().HorizontalDivider(
                color = ColorPalette.getMarkupColor(), thickness = 1.dp)

            // Bottom spacer
            Spacer(modifier = Modifier.height(64.dp))
        }
    }

}