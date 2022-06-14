package pedroluiz.projeto.apresentagit

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pedroluiz.projeto.apresentagit.data.di.DataModule
import pedroluiz.projeto.apresentagit.domain.di.DomainModule
import pedroluiz.projeto.apresentagit.presentation.di.PresentetationDi

class App :Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

        }

        DataModule.load()
        DomainModule.load()
        PresentetationDi.load()
    }
}