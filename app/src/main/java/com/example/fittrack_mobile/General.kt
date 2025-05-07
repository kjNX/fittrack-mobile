package com.example.fittrack_mobile

import android.view.Menu
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fittrack_mobile.ui.theme.FittrackmobileTheme

@Composable
fun FitCard(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String,
    content: @Composable () -> Unit
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row {
                if(icon != null)
                    Icon(imageVector = icon, contentDescription = null)
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            content()
        }
    }
}

@Composable
fun MenuTab(selected: Boolean, title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(
                color = if (selected) Color.White else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun TabbedMenu(idx: Int, labels: List<String>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(
                color = Color.Gray,
                shape = MaterialTheme.shapes.small
            )
            .padding(2.dp)
    ) {
        for((i, label) in labels.withIndex())
            MenuTab(i == idx, label, modifier = Modifier.weight(1f))
    }
}

@Composable
fun TabbedHeader(title: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(color = MaterialTheme.colorScheme.secondary)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        TabbedMenu(0, listOf("A", "B"), modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
private fun Preview() {
    FittrackmobileTheme {
        TabbedHeader("Ayuda y soporte")
    }
}