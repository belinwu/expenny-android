package org.expenny.navigation

import android.content.Intent
import androidx.navigation.NavController
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import org.expenny.core.common.types.RecordType
import org.expenny.core.common.utils.Constants.NULL_ID
import org.expenny.core.ui.data.navargs.LongNavArg
import org.expenny.core.ui.data.navargs.RecordsListFilterNavArg
import org.expenny.feature.accountdetails.destinations.AccountDetailsScreenDestination
import org.expenny.feature.accountdetails.navigation.AccountDetailsNavigator
import org.expenny.feature.accountoverview.destinations.AccountOverviewScreenDestination
import org.expenny.feature.accounts.destinations.AccountsListScreenDestination
import org.expenny.feature.accounts.navigation.AccountsListNavigator
import org.expenny.feature.categories.destinations.CategoriesListScreenDestination
import org.expenny.feature.categories.navigation.CategoriesListNavigator
import org.expenny.feature.categorydetails.destinations.CategoryDetailsScreenDestination
import org.expenny.feature.categorydetails.navigation.CategoryDetailsNavigator
import org.expenny.feature.currencies.destinations.CurrenciesListScreenDestination
import org.expenny.feature.currencies.navigation.CurrenciesListNavigator
import org.expenny.feature.currencydetails.destinations.CurrencyDetailsScreenDestination
import org.expenny.feature.currencydetails.navigation.CurrencyDetailsNavigator
import org.expenny.feature.currencyunits.destinations.CurrencyUnitsListScreenDestination
import org.expenny.feature.dashboard.navigation.DashboardNavigator
import org.expenny.feature.passcode.destinations.PasscodeScreenDestination
import org.expenny.feature.passcode.model.PasscodeType
import org.expenny.feature.passcode.navigation.PasscodeNavigator
import org.expenny.feature.profilesetup.destinations.ProfileSetupScreenDestination
import org.expenny.feature.profilesetup.navigation.ProfileSetupNavigator
import org.expenny.feature.recorddetails.destinations.RecordDetailsScreenDestination
import org.expenny.feature.recorddetails.navigation.RecordDetailsNavigator
import org.expenny.feature.records.destinations.RecordsListScreenDestination
import org.expenny.feature.records.navigation.RecordsListNavigator
import org.expenny.feature.settings.navigation.SettingsNavigator
import org.expenny.feature.splash.navigation.SplashNavigator
import org.expenny.feature.welcome.navigation.WelcomeNavigator
import org.expenny.main.MainActivity
import org.expenny.main.drawer.DrawerTab

class ExpennyNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController,
) : SplashNavigator,
    WelcomeNavigator,
    ProfileSetupNavigator,
    AccountsListNavigator,
    DashboardNavigator,
    AccountDetailsNavigator,
    CurrenciesListNavigator,
    CurrencyDetailsNavigator,
    RecordsListNavigator,
    RecordDetailsNavigator,
    SettingsNavigator,
    CategoriesListNavigator,
    PasscodeNavigator,
    CategoryDetailsNavigator {

    override fun navigateToSetup() {
        navController.navigate(ExpennyNavGraphs.setup) {
            popUpTo(ExpennyNavGraphs.root.route) {
                inclusive = true
            }
        }
    }

    override fun navigateToHome() {
        navController.navigate(ExpennyNavGraphs.home) {
            popUpTo(ExpennyNavGraphs.root.route) {
                inclusive = true
            }
        }
    }

    override fun navigateToCreateProfileScreen() {
        navController.navigate(ProfileSetupScreenDestination within navGraph)
    }

    override fun navigateToProfileSetupScreen() {
        navController.navigate(ProfileSetupScreenDestination within navGraph)
    }

    override fun navigateToCurrencyUnitSelectionListScreen(selectedId: Long?) {
        navController.navigate(
            direction = CurrencyUnitsListScreenDestination(
                selection = LongNavArg(selectedId ?: NULL_ID),
                includeOnlyAvailable = false,
            ) within navGraph
        )
    }

    override fun navigateToAvailableCurrencyUnitSelectionListScreen(selectedId: Long?) {
        navController.navigate(
            direction = CurrencyUnitsListScreenDestination(
                selection = LongNavArg(selectedId ?: NULL_ID),
                includeOnlyAvailable = true,
            ) within navGraph
        )
    }

    override fun navigateToCurrencySelectionListScreen(selectedId: Long?) {
        navController.navigate(
            direction = CurrenciesListScreenDestination(
                selection = LongNavArg(selectedId ?: NULL_ID)
            ) within navGraph
        )
    }

    override fun navigateToEditCurrencyScreen(currencyId: Long) {
        navController.navigate(
            direction = CurrencyDetailsScreenDestination(currencyId = currencyId) within navGraph
        )
    }

    override fun navigateToCreateCurrencyScreen() {
        navController.navigate(
            direction = CurrencyDetailsScreenDestination() within navGraph
        )
    }

    override fun navigateToEditRecordScreen(recordId: Long) {
        navController.navigate(
            direction = RecordDetailsScreenDestination(recordId = recordId) within navGraph
        )
    }

    override fun navigateToCloneRecordScreen(recordId: Long) {
        navController.navigate(
            direction = RecordDetailsScreenDestination(recordId = recordId, isClone = true) within navGraph
        )
    }

    override fun navigateToCreateRecordScreen() {
        navController.navigate(RecordDetailsScreenDestination() within navGraph)
    }

    override fun navigateToAccountSelectionListScreen(selection: LongNavArg, excludeIds: LongArray?) {
        navController.navigate(
            direction = AccountsListScreenDestination(
                selection = selection,
                excludeIds = excludeIds
            ) within navGraph
        )
    }

    override fun navigateToCategorySelectionListScreen(selection: LongNavArg) {
        navController.navigate(
            direction = CategoriesListScreenDestination(selection = selection) within navGraph
        )
    }

    override fun navigateToDashboardScreen() {
        navigateToHome()
    }

    override fun navigateToEditCategoryScreen(categoryId: Long) {
        navController.navigate(
            direction = CategoryDetailsScreenDestination(categoryId = categoryId) within navGraph
        )
    }

    override fun navigateToAddCategoryScreen() {
        navController.navigate(
            direction = CategoryDetailsScreenDestination() within navGraph
        )
    }

    override fun navigateBack() {
        if (navController.currentDestination?.route == DrawerTab.Settings.route) {
            navController.navigateFirstTab()
        } else {
            navController.popBackStack()
        }
    }

    override fun navigateToCurrenciesListScreen() {
        navController.navigate(CurrenciesListScreenDestination() within navGraph)
    }

    override fun navigateToCreatePasscodeScreen() {
        navController.navigate(
            direction = PasscodeScreenDestination(type = PasscodeType.Create) within navGraph
        )
    }

    override fun navigateToCategoriesListScreen() {
        navController.navigate(
            direction = CategoriesListScreenDestination() within navGraph
        )
    }

    override fun navigateToAccountsListScreen() {
        navController.navigate(AccountsListScreenDestination() within navGraph)
    }

    override fun navigateToRecordsListScreen(filter: RecordsListFilterNavArg?) {
        // navController.navigateTab(ExpennyNavGraphs.records)
        navController.navigate(
            direction = RecordsListScreenDestination(filter = filter) within navGraph
        )
    }

    override fun navigateToCreateRecordScreen(recordType: RecordType) {
        navController.navigate(
            direction = RecordDetailsScreenDestination(recordType = recordType) within navGraph
        )
    }

    override fun navigateToCreateAccountScreen() {
        navController.navigate(
            direction = AccountDetailsScreenDestination() within navGraph
        )
    }

    override fun navigateToOverviewAccountScreen(accountId: Long) {
        navController.navigate(
            direction = AccountOverviewScreenDestination() within navGraph
        )
    }

    override fun navigateToEditAccountScreen(accountId: Long) {
        navController.navigate(
            direction = AccountDetailsScreenDestination(accountId = accountId) within navGraph
        )
    }

    override fun restartApplication(isDataCleanupRequested: Boolean) {
        val context = navController.context

        (context as MainActivity).finish()

        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(MainActivity.isDataCleanupRequestedKey, isDataCleanupRequested)
        }
        context.startActivity(intent)
    }
}