package pedroluiz.projeto.apresentagit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import pedroluiz.projeto.apresentagit.data.model.Repo
import pedroluiz.projeto.apresentagit.domain.ListUserRepositoriesUseCase

class MainViewModel(private val listUserRepositorioUseCase:ListUserRepositoriesUseCase) :ViewModel(){

    private val _repo = MutableLiveData<State>()
    val repos:LiveData<State> = _repo

    fun getRepoList(user:String){
        viewModelScope.launch {
            listUserRepositorioUseCase(user)
                .onStart {
                    _repo.postValue(State.Loading)
                }
                .catch {
                    _repo.postValue(State.Erro(it))
                   // Log.e("errott",it.toString())
                }
                .collect {
                    _repo.postValue(State.Sucess(it))
                }


        }
    }

    sealed class State{
        object Loading:State()
        data class Sucess(val list:List<Repo>):State()
        data class Erro(val error:Throwable):State()
    }
}