package com.goofy.goober.composelab.lists.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ColorItemContent(
    item: Item,
    onClick: () -> Unit
) {
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(50.dp)
                .background(color = item.color, shape = RoundedCornerShape(8.dp))
                .clickable { onClick() }
        ) {
            Text(
                color = Color.Black,
                textAlign = TextAlign.Center,
                text = ""
//                text = if (listState.selectedItem == item) "selected" else ""
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}
