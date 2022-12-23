package com.anish.splashapidemo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.anish.splashapidemo.ui.theme.SplashApiDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        //splashScreen.setKeepOnScreenCondition { true }

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
//            val slideUp = ObjectAnimator.ofFloat(
//                splashScreenView,
//                View.TRANSLATION_Y,
//                0f,
//                -splashScreenView.height.toFloat()
//            )

            val slideUp = ObjectAnimator.ofFloat(splashScreenView.view, View.TRANSLATION_Y, 0f, -splashScreenView.view.height.toFloat())

            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 1000L

            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.doOnEnd { splashScreenView.remove() }

            // Run your animation.
            slideUp.start()
        }

        setContent {
            SplashApiDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android ANish anish kumar dubey")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SplashApiDemoTheme {
        Greeting("Android")
    }
}