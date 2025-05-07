package com.example.fittrack_mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fittrack_mobile.model.Section
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
fun MenuTab(
    selected: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
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
fun TabbedMenu(
    idx: Int,
    labels: List<String>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = Color.LightGray,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 3.dp, horizontal = 1.dp)
    ) {
        for((i, label) in labels.withIndex())
            MenuTab(
                selected = i == idx,
                title = label,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp),
                onClick = { onClick(i) }
            )
    }
}

@Composable
fun TabbedHeader(
    section: Section,
    idx: Int,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
    ) {
        Text(text = section.title, style = MaterialTheme.typography.titleLarge)
        TabbedMenu(
            idx = idx,
            labels = section.tabsTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = onClick
        )
    }
}

@Composable
fun TabbedLayout(section: Section, modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column(modifier = modifier) {
        TabbedHeader(
            section = section,
            idx = selectedTabIndex,
            onClick = { selectedTabIndex = it }
        )
        section.tabsContent[selectedTabIndex]()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    FittrackmobileTheme {
        /*TabbedHeader("Ayuda y soporte", 0, listOf(
            "Chatbot IA",
            "Especialista"
        ))*/
    }
}