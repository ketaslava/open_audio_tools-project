package com.ktvincco.openaudiotools.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
actual fun getScreenSizeInDp(): Pair<Dp, Dp> {
    val conf = LocalConfiguration.current
    return Pair(conf.screenWidthDp.dp, conf.screenHeightDp.dp)
}


@Composable
actual fun getScreenSizeInPx(): Pair<Int, Int> {
    val conf = LocalConfiguration.current
    val density = LocalDensity.current.density
    return Pair((conf.screenWidthDp * density).toInt(), (conf.screenHeightDp * density).toInt())
}