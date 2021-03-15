package com.goofy.goober.composelab.activity.gestures

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.goofy.goober.composelab.R
import com.goofy.goober.composelab.databinding.GifViewBinding
import kotlin.math.roundToInt

@Composable
fun DragWrappingAndroidView() {
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(Size.Zero) }
    var cardSize by remember { mutableStateOf(Size.Zero) }
    Box(
        Modifier.fillMaxSize()
            .background(color = Color.LightGray)
            .onSizeChanged { size = it.toSize() }
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxSize(0.6f)
                .onSizeChanged { cardSize = size }
                .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        val original = Offset(offsetX.value, offsetY.value)
                        val summed = original + dragAmount
                        val newValue = Offset(
                            x = summed.x.coerceIn(0f, size.width - cardSize.width),
                            y = summed.y.coerceIn(0f, size.height - cardSize.height)
                        )
                        change.consumePositionChange()
                        offsetX.value = newValue.x
                        offsetY.value = newValue.y
                    }
                },
            elevation = 5.dp
        ) {
            AndroidView(
                factory = {
                    GifView(it).apply {
                        setImage("https://media.giphy.com/media/8UGGp7rQvfhe63HrFq/giphy.gif")
                    }
                },
                update = {}
            )
        }
    }
}

class GifView(
    context: Context,
    attrs: AttributeSet? = null
): ConstraintLayout(context, attrs) {

    private val binding = GifViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        setBackgroundColor(context.resources.getColor(R.color.purple_200, null))
    }

    fun setImage(url: String) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(android.R.color.darker_gray)
            .centerCrop()
            .into(binding.image)
    }
}
