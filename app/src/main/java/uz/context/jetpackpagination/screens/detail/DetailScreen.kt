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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import uz.context.jetpackpagination.R
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
        if (state.isLoading) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        detail?.let {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                var likesCount by remember {
                    mutableStateOf(detail.likes)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.2f)
                ) {
                    val painter = rememberImagePainter(data = it.url) {
                        crossfade(durationMillis = 1000)
                        error(R.drawable.ic_placeholder)
                        placeholder(R.drawable.ic_placeholder)
                    }
                    Image(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row(modifier =  Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                modifier = Modifier.fillMaxWidth().weight(3f),
                                text = it.userName,
                                color = Color(android.graphics.Color.parseColor(it.color)),
                                fontSize = 22.sp,
                                fontStyle = FontStyle.Italic
                            )
                            IconButton(
                                modifier = Modifier.fillMaxWidth().weight(1f),
                                onClick = {
                                isLiked = !isLiked
                                if (isLiked) likesCount += 1
                                else likesCount -= 1
                            }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "",
                                        tint = if (isLiked) Color.Red else Color.Gray
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = likesCount.toString(),
                                        fontSize = 12.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                        Text(
                            text = it.bio,
                            fontSize = 14.sp,
                            color = Color(android.graphics.Color.parseColor(it.color))
                        )
                    }
                }
            }
        }
    }
}
