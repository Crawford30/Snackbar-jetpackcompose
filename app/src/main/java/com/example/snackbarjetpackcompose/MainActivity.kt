package com.example.snackbarjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.snackbarjetpackcompose.ui.theme.SnackBarJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isShowing = remember { mutableStateOf(false) }

            Column() {
                Button(onClick = {
                    isShowing.value = true
                }) {
                    Text(text = "Show Snackbar")
                }

                SimpleSnackBarDemo(
                    isShowing = isShowing.value,
                    onHideSnackBar = {
                        isShowing.value = false
                    }
                )

            }


//            SnackBarJetpackComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
        }
    }
}


@Composable
fun SimpleSnackBarDemo(
    isShowing: Boolean,
    onHideSnackBar: () -> Unit
) {
    if (isShowing) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val snackBar = createRef()
            Snackbar(
                modifier = Modifier.constrainAs(snackBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                action = {
                    Text(
                        text = "Hide",
                        modifier = Modifier.clickable(
                            onClick = onHideSnackBar
                        ),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            ) {
                Text(text = "Hey Look a snack bar")
            }

        }

    } else {

    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnackBarJetpackComposeTheme {
        Greeting("Android")
    }
}