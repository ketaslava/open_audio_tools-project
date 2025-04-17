package com.ktvincco.openvoiceanalyzer.ui

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
import com.ktvincco.openvoiceanalyzer.ColorPalette
import com.ktvincco.openvoiceanalyzer.Settings
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import openaudiotools.app.openvoiceanalyzer.generated.resources.Res
import openaudiotools.app.openvoiceanalyzer.generated.resources.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
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

            Text(
                text = "Legal Info",
                color = ColorPalette.getTextColor(),
                fontSize = 28.sp,
                lineHeight = 36.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            )

            BaseComponents().HorizontalDivider(
                color = ColorPalette.getMarkupColor(), thickness = 1.dp)

            Text(
                text = "DISCLAIMER",
                color = ColorPalette.getTextColor(),
                fontSize = 20.sp,
                lineHeight = 30.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            )

            Text(
                text = "This application is provided \"AS IS\" and does not provide any guarantees. The authors of the application does not bear any responsibility for its use. The application does not provide any medical advice",
                color = ColorPalette.getTextColor(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                    .fillMaxWidth()
            )

            BaseComponents().HorizontalDivider(
                color = ColorPalette.getMarkupColor(), thickness = 1.dp)

            Text(
                text = "Contact Us",
                color = ColorPalette.getTextColor(),
                fontSize = 20.sp,
                lineHeight = 30.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            )

            Text(
                text = "Official Communication:\n\n" +
                        "   * Email: ktvincco@gmail.com",
                color = ColorPalette.getTextColor(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                    .fillMaxWidth()
            )

            BaseComponents().HorizontalDivider(
                color = ColorPalette.getMarkupColor(), thickness = 1.dp)

            Text(
                text = "User agreement",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                lineHeight = 30.sp,
                color = ColorPalette.getTextColor(),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            )

            Text(
                text = "By using this application, you agree with our Terms Of Use and Privacy Policy",
                color = ColorPalette.getTextColor(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "[Terms Of Use]",
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
            Text(
                text = "[Privacy Policy]",
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

            Text(
                text = "License NOTICE",
                color = ColorPalette.getTextColor(),
                fontSize = 20.sp,
                lineHeight = 30.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            )

            Text(
                text = "This application was developed by Ketaslava Ket and KTVINCCO Team\n\n" +
                        "The application is distributed under GNU GENERAL PUBLIC LICENSE Version 3.0",
                color = ColorPalette.getTextColor(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "[License]",
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

            Text(
                text = "[Source Code]",
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

            Text(
                text = "This app contains:\n\n" +
                    "    * Google icons (Apache License Version 2.0)",
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