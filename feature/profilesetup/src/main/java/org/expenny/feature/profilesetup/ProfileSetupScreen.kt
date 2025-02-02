package org.expenny.feature.profilesetup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.OpenResultRecipient
import org.expenny.core.common.ExpennySnackbarManager
import org.expenny.core.ui.data.navargs.LongNavArg
import org.expenny.feature.profilesetup.navigation.ProfileSetupNavigator
import org.expenny.feature.profilesetup.style.ProfileSetupScreenTransitionStyle
import org.expenny.feature.profilesetup.view.ProfileSetupContent
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = ProfileSetupScreenTransitionStyle::class)
@Composable
fun ProfileSetupScreen(
    snackbarManager: ExpennySnackbarManager,
    navigator: ProfileSetupNavigator,
    currencyUnitResult: OpenResultRecipient<LongNavArg>,
) {
    val vm: ProfileSetupViewModel = hiltViewModel()
    val state by vm.collectAsState()
    val scrollState = rememberScrollState()

    BackHandler(!state.showConfirmationDialog && !state.showAbortDialog) {
        vm.onAction(Action.OnBackClick)
    }

    currencyUnitResult.onNavResult { res ->
        if (res is NavResult.Value) {
            vm.onAction(Action.OnCurrencyUnitSelect(res.value.value))
        }
    }

    vm.collectSideEffect {
        when (it) {
            is Event.ShowMessage -> snackbarManager.showMessage(it.message)
            is Event.NavigateToHome -> navigator.navigateToHome()
            is Event.NavigateToCurrencyUnitsSelectionList -> navigator.navigateToCurrencyUnitSelectionListScreen(it.selectedId)
            is Event.NavigateBack -> navigator.navigateBack()
        }
    }

    ProfileSetupContent(
        state = state,
        scrollState = scrollState,
        onAction = vm::onAction,
    )
}
