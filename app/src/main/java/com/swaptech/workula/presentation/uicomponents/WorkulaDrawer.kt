package com.swaptech.workula.presentation.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swaptech.workula.ThemedPreview
import com.swaptech.workula.presentation.navigation.WorkulaNavGraph

@Composable
fun WorkulaDrawer(
    onDrawerItemClicked: (String) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(MaterialTheme.colors.primaryVariant),
        )
        repeat(WorkulaNavGraph.WorkulaDrawerScreensNavGraph.values().size) {
            val item = WorkulaNavGraph.WorkulaDrawerScreensNavGraph.values()[it]
            DrawerItem(
                modifier = Modifier.clickable {
                    onDrawerItemClicked(item.name)
                },
                painter = painterResource(item.icon),
                text = item.name
            )
        }
    }
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(
                top = 7.dp,
                bottom = 7.dp,
                start = 14.dp,
                end = 14.dp
            )
            .height(34.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painter, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun DrawerItem() {
    ThemedPreview {
        DrawerItem()
    }
}

@Preview
@Composable
fun WorkulaDrawer() {
    ThemedPreview {
        WorkulaDrawer()
    }
}
