package com.example.jetpack.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jetpack.data.utils.ApiState
import com.example.jetpack.ui.theme.JetpackExampleTheme
import com.example.jetpack.ui.theme.Shapes
import com.example.jetpack.ui.theme.Typography
import com.example.jetpack.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

//            Greeting("Kashif")
            Scaffold(
                topBar = {
                    TopAppBar() {
                        Text("JetPack-Example", modifier = Modifier.padding(start = 10.dp),
                            fontSize = 18.sp,
                        )
                    }
                }
            ) {
                when(val result = mainViewModel.response.value){
                    ApiState.Empty -> Text("Empty")
                    is ApiState.Error -> Text(result.toString())
                    ApiState.Loading -> Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                    is ApiState.Success -> RecyclerView(paddingValues = it, result.data)
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String) {

    Scaffold(
        topBar = {
            TopAppBar() {
                Text("AppBar", modifier = Modifier.padding(start = 10.dp))
            }
        }
    ) {
        body(it, name)
    }
}

@Composable
fun cardBody(name: String) {
    val context = LocalContext.current
    SelectionContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.LightGray, Shapes.small)

    ) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .height(100.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
//        Image(
//            painter = rememberAsyncImagePainter(
//                "https://instagram.flhe5-1.fna.fbcdn.net/v/t51.2885-19/271921836_1392141027884991_8387979883498782617_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.flhe5-1.fna.fbcdn.net&_nc_cat=103&_nc_ohc=_L3FKRXZGWMAX8-pjsB&edm=AOQ1c0wBAAAA&ccb=7-5&oh=00_AT9vFMpGb3dN9pn8SR2tA-jRT7jPLf0ltU6buACV6dXSBw&oe=62BD485D&_nc_sid=8fd12b",
//                contentScale = ContentScale.None
//            ),
//            contentDescription = null,
//            modifier = Modifier.requiredSize(50.dp)
//        )

            Image(
                painter = rememberAsyncImagePainter("https://media-exp1.licdn.com/dms/image/C4D03AQFgm_4ycQvVww/profile-displayphoto-shrink_100_100/0/1595601733861?e=1671062400&v=beta&t=ZK4tdYCmI91rUiGHCAJQltfwozFse-Z3FDeOr2Oe4BU"),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = CircleShape)
                    .clickable {
                        Toast
                            .makeText(
                                context,
                                "Showing toast....",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Hello $name!")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Hello I am second text", style = Typography.body2)

            }
        }
    }
}

@Composable
fun body(paddingValues: PaddingValues, name: String) {
    Column() {
        cardBody(name)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackExampleTheme {
        Greeting("Kashif")
    }
}