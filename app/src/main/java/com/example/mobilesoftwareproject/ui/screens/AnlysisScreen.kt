

package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnalysisScreen() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("월간 칼로리 분석", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        // Placeholder for charts or analysis components
        Text("식사별 비용 분석", style = MaterialTheme.typography.h6)
    }
}

