package com.example.fittrack_mobile.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fittrack_mobile.model.OnboardingData
import kotlinx.coroutines.launch

@Composable
fun OnboardingPage(onboardingData: OnboardingData, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(onboardingData.image),
            contentDescription = null
        )
        Text(
            text = onboardingData.title,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = onboardingData.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun OnboardingScreen(navController: NavController, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState {
        onboardingList.size
    }
    val coroutineScope = rememberCoroutineScope()
    val isFirstPage = pagerState.currentPage == 0
    val isLastPage = pagerState.currentPage == pagerState.pageCount - 1
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            OnboardingPage(onboardingList[page])
        }
        Row(
            horizontalArrangement = if (isFirstPage) Arrangement.End else Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            if(!isFirstPage)
                Button(onClick = {
                    coroutineScope.launch{
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                Text(text = "Regresar")
            }
            Button(onClick = {
                if(isLastPage) navController.navigate("auth")
                else coroutineScope.launch{
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Text(text = if(isLastPage) "Comenzar" else "Siguiente")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
//    OnboardingScreen(navController = NavController(LocalContext.current))
}