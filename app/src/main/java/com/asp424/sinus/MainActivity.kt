package com.asp424.sinus

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asp424.sinus.ui.theme.SinusTheme
import kotlin.math.abs
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SinusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Size()
                }
            }
        }
    }
    private val list = mutableListOf(R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.e, R.drawable.d)
    @Composable
    fun Size() {
        val state = rememberScrollState()
        var scale by remember { mutableStateOf(1f) }
        val stateIm = rememberTransformableState { zoomChange, _, _ -> scale *= zoomChange }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.horizontalScroll(state = state, enabled = true)
                .fillMaxSize()
        ) {
            list.forEach { item ->
                Image(
                    painterResource(id = item), contentDescription = null,
                    Modifier.padding(250.dp).size(200.dp).transformable(state = stateIm)
                        .graphicsLayer(
                            scaleX = scale, scaleY = scale)
                )
            }
            scale = abs(sin((state.value.toFloat() / 22) / 30 + 60f) * 3)
        }
        Log.d("My", scale.toString())
    }


}


