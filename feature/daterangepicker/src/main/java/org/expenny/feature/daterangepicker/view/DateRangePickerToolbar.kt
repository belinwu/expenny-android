package org.expenny.feature.daterangepicker.view

import androidx.compose.animation.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.expenny.core.resources.R
import org.expenny.core.ui.foundation.ExpennyTextButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateRangePickerToolbar(
    showClearButton: Boolean,
    onClearClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    TopAppBar(
        actions = {
            if (showClearButton) {
                ExpennyTextButton(
                    onClick = onClearClick,
                    content = {
                        Text(text = stringResource(R.string.clear_button))
                    }
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onCloseClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.select_range_label),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}