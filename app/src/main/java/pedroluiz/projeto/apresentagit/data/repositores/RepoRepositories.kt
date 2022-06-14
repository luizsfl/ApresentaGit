package pedroluiz.projeto.apresentagit.data.repositores

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.apresentagit.data.model.Repo

interface RepoRepositories {
 suspend fun listRepositores(user: String):Flow<List<Repo>>

}