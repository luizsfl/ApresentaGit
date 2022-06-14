package pedroluiz.projeto.apresentagit.data.repositores

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pedroluiz.projeto.apresentagit.data.model.Repo
import pedroluiz.projeto.apresentagit.data.service.GitHubService

class RepoRepositoryImpl(private val service : GitHubService) :RepoRepositories{
    override suspend fun listRepositores(user: String): Flow<List<Repo>> = flow {
        val repoList =  service.listRepositores(user)
        emit(repoList)
    }
}