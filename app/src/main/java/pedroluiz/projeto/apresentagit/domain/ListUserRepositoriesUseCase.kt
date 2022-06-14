package pedroluiz.projeto.apresentagit.domain

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.apresentagit.core.UseCase
import pedroluiz.projeto.apresentagit.data.model.Repo
import pedroluiz.projeto.apresentagit.data.repositores.RepoRepositories

class ListUserRepositoriesUseCase(private val repository: RepoRepositories) :UseCase<String,List<Repo>>(){
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositores(param)
    }

}