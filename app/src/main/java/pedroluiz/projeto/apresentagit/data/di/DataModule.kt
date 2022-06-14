package pedroluiz.projeto.apresentagit.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import pedroluiz.projeto.apresentagit.data.repositores.RepoRepositories
import pedroluiz.projeto.apresentagit.data.repositores.RepoRepositoryImpl
import pedroluiz.projeto.apresentagit.data.service.GitHubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load(){
        loadKoinModules(networkModules() + repositoresModule())
    }

    private fun networkModules(): Module {



        return module {
            single {
                val interceptor = HttpLoggingInterceptor{
                    //Log.e("TAG",it ) // tag para apresentar o retorno da api
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())

            }

            single {
                createService<GitHubService>(get(),get())
            }


        }
    }


    private fun repositoresModule():Module {
        return module {
            single<RepoRepositories> {
                RepoRepositoryImpl(get())
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient,factory: GsonConverterFactory): T
    {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)

    }
}