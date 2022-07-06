package com.goofy.goober.composelab.casestudy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goofy.goober.composelab.R

val Bg = Color(0xFFE9EAEB)
val BgCircle = Color(0xFFD4DAE1)
val CostStart = Color(0xFF62E3BE)
val CostEnd = Color(0xFF51D9DD)
val DetailsStart = Color(0xFF6AE3BE)
val DetailsEnd = Color(0xFFF7E166)
val AddBagStart = Color(0xFF5FDEB9)
val AddBagEnd = Color(0xFF48D6DF)
val AddBagText = Color(0xFFFBFCFB)
val FooterGradient = Color(0xFF98E5DE)
val TitleText = Color(0xFF576063)
val PropChoice = Color(0xFF646B6F)
val PropLabel = Color(0xFFB6BFC3)
val PropArrow = Color(0xFF898F91)

@Composable
fun PlantApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = Bg),
        contentAlignment = Alignment.Center
    ) {
        FooterGradient()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(40.dp))
            Plant()
            Text(
                fontSize = 24.sp,
                text = "Monstera Deliciosa",
                letterSpacing = 1.3.sp,
                color = TitleText,
                fontWeight = FontWeight.W700
            )
            Spacer(Modifier.height(18.dp))
            Price()
            Spacer(Modifier.height(42.dp))
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Choices(label = "Size", value = "Medium")
                Spacer(Modifier.width(32.dp))
                Choices(label = "Quantity", value = "2")
            }
            Spacer(Modifier.height(40.dp))
            AddButton(label = "Add to your bag")
            Spacer(Modifier.height(80.dp))
            Footer()
        }
    }
}

@Composable
fun Plant() {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(screenWidth * 0.7f)
                .background(shape = CircleShape, color = BgCircle)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.67f)
                .offset(x = 3.dp, y = -50.dp),
            painter = painterResource(R.drawable.md_plant2),
            contentDescription = null
        )
    }
}

@Composable
fun Price() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            fontSize = 40.sp,
            text = "$20",
            letterSpacing = 2.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier.textBrush(
                Brush.horizontalGradient(
                    listOf(
                        CostStart,
                        CostEnd
                    )
                )
            ),
        )
        Spacer(Modifier.width(42.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .wrapContentSize()
                .background(
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    ),
                    brush = Brush.horizontalGradient(listOf(DetailsStart, DetailsEnd))
                )
                .padding(top = 14.dp, bottom = 14.dp, start = 32.dp, end = 32.dp)
        ) {
            Text(
                text = "Details",
                color = Bg,
                fontSize = 16.sp,
                letterSpacing = 0.6.sp,
                fontWeight = FontWeight.W700
            )
        }
    }
}

@Composable
fun Footer() {
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = null,
                tint = AddBagText
            )
        }
        Spacer(Modifier.width(42.dp))
        IconButton(
            modifier = Modifier
                .padding(4.dp)
                .size(34.dp)
                .background(shape = CircleShape, color = AddBagText),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.ic_bag),
                contentDescription = null,
                tint = AddBagEnd
            )
        }
        Spacer(Modifier.width(42.dp))
        IconButton(
            modifier = Modifier
                .size(24.dp)
                .padding(top = 4.dp),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = null,
                tint = AddBagText
            )
        }
    }
}

@Composable
fun AddButton(label: String) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                shape = RoundedCornerShape(17.dp),
                brush = Brush.horizontalGradient(listOf(AddBagStart, AddBagEnd))
            )
            .padding(top = 23.dp, bottom = 23.dp, start = 60.dp, end = 60.dp)
    ) {
        Text(
            text = label,
            color = AddBagText,
            fontSize = 19.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.W700
        )
    }
}

@Composable
fun Choices(label: String, value: String) {
    Row(modifier = Modifier.wrapContentSize()) {
        Text(
            text = label,
            color = PropLabel,
            fontSize = 14.sp,
            letterSpacing = 0.6.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(Modifier.width(22.dp))
        Text(
            text = value,
            color = PropChoice,
            fontSize = 14.sp,
            letterSpacing = 0.6.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(Modifier.width(5.dp))
        IconButton(
            modifier = Modifier
                .padding(top = 3.dp)
                .size(12.dp)
                .rotate(degrees = 90f),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = null,
                tint = PropArrow
            )
        }
    }
}

@Composable
fun FooterGradient() {
    val config = LocalConfiguration.current
    val screen = config.screenWidthDp.dp
    Box(
        modifier = Modifier
            .size(screen)
            .offset(y = screen + (screen * 0.1f))
            .background(Brush.radialGradient(colors = listOf(FooterGradient, Bg)))
//            .clickable(interactionSource = MutableInteractionSource(), indication = null) { expand = !expand }
    )
}

fun Modifier.textBrush(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }


@Preview
@Composable
fun PlantAppPreview() {
    PlantApp()
}
