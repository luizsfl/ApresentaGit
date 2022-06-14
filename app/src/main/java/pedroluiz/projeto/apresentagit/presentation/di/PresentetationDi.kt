package pedroluiz.projeto.apresentagit.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import pedroluiz.projeto.apresentagit.domain.ListUserRepositoriesUseCase
import pedroluiz.projeto.apresentagit.presentation.MainViewModel

object PresentetationDi {
    fun load() {
        loadKoinModules(viewModules())
    }

    private fun viewModules(): Module {
        return module {
            viewModel {
                MainViewModel(get())
            }
        }
    }
}