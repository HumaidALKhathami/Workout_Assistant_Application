package com.tuwaiq.workoutassistantapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tuwaiq.workoutassistantapplication.core.presentation.BottomNavigationBarItem
import com.tuwaiq.workoutassistantapplication.core.presentation.Navigation
import com.tuwaiq.workoutassistantapplication.core.presentation.NavigationBottomBar
import com.tuwaiq.workoutassistantapplication.core.presentation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
        NavigationBottomBar(
            items = listOf(
                BottomNavigationBarItem(
                    name = stringResource(R.string.bottom_navigation_workout_name),
                    route = Screen.WorkoutListScreen.route,
                    Icons.Default.EmojiPeople,
                ),
                BottomNavigationBarItem(
                    name = "Profile",
                    route = Screen.PersonalProfileScreen.route,
                    Icons.Default.Person,
                )
            ),
            navController = navController,
            onItemClick = { item ->
                navController.navigate(item.route)
            },
        )
    }) {
        Navigation(navController = navController)
    }
}

