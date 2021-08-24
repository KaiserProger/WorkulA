package com.swaptech.workula

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.swaptech.workula.presentation.navigation.WorkulaNavGraph
import com.swaptech.workula.presentation.theme.WorkulaTheme

typealias DrawerItem = WorkulaNavGraph.WorkulaDrawerScreensNavGraph
typealias HomeDestinations = WorkulaNavGraph.WorkulaDrawerScreensNavGraph.HomeDestinations

@Preview
@Composable
fun ThemedPreview(
    @PreviewParameter(ComposableLambdaProvider::class)
    content: @Composable () -> Unit
) {
    WorkulaTheme {
        Surface {
            content()
        }
    }
}

class ComposableLambdaProvider : PreviewParameterProvider<@Composable () -> Unit> {
    override val values = sequenceOf({})
    override val count: Int
        get() = values.count()
}