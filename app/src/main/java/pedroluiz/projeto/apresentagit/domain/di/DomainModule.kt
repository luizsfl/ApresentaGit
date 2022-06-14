package pedroluiz.projeto.apresentagit.domain.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import pedroluiz.projeto.apresentagit.domain.ListUserRepositoriesUseCase

object DomainModule {
    fun load(){
        loadKoinModules(useCaseModules())
    }

    private fun useCaseModules():Module{
        return module {
            factory {
                ListUserRepositoriesUseCase(get())
            }
        }
    }
}