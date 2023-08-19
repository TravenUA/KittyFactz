package com.traven.kittyfactz.presentation

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.traven.kittyfactz.Const
import com.traven.kittyfactz.presentation.theme.KittyFactzTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MViewModel::class.java]

        setContent {
            MScreen(viewModel = viewModel)
        }

    }
}

@Composable
fun MScreen(viewModel: MViewModel) {

    val txtState = viewModel.outData.observeAsState()

    if (viewModel.outData.value == null)
        LaunchedEffect(key1 = Unit) { viewModel.getFact() }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxHeight(.5f)
                .fillMaxWidth(),
        ) {
            Text(
                text = txtState.value ?: Const.STATE_MSG_LOAD,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .align(Alignment.Center),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(.5f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        viewModel.getFact()
                    }
                }, modifier = Modifier.align(Alignment.Center)
            ) {
                Text("One more")
            }
        }
    }
}