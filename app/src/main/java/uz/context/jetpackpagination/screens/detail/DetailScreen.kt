package uz.context.jetpackpagination.screens.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import uz.context.jetpackpagination.R
import uz.context.jetpackpagination.ui.theme.Purple200
import uz.context.jetpackpagination.ui.theme.Purple500
import uz.context.jetpackpagination.util.Constants.TAG

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailScreen(
    id: String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    var isLiked by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getImageById(id)
    }
    val state by remember {
        mutableStateOf(viewModel.state.value)
    }
    val detail = viewModel.state.value.detail
    LaunchedEffect(key1 = Unit) {
        Log.d(TAG, "DetailScreen: $detail")
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Purple500
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if(state.isLoading) {
                CircularProgressIndicator()
            }
            detail?.let {
                val painter = rememberImagePainter(data = it.url) {
                    crossfade(durationMillis = 1000)
                    error(R.drawable.ic_placeholder)
                    placeholder(R.drawable.ic_placeholder)
                }
                LaunchedEffect(key1 = Unit) {
                    Log.d(TAG, "DetailScreen: $it")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(250.dp)
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = it.userName,
                                color = Purple200,
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic
                            )
                            IconButton(onClick = { isLiked = !isLiked }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "",
                                    tint = if (isLiked) Color.Red else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}