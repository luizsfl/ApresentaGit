package pedroluiz.projeto.apresentagit.data.repositores

import android.os.RemoteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pedroluiz.projeto.apresentagit.data.model.Repo
import pedroluiz.projeto.apresentagit.data.service.GitHubService

class RepoRepositoryImpl(private val service : GitHubService) :RepoRepositories{
    override suspend fun listRepositores(user: String): Flow<List<Repo>> = flow {
      try {
          val repoList =  service.listRepositores(user)
          emit(repoList)
      }catch (e : Exception){
            throw RemoteException(e.message ?:"Não foi possivel realizar a busca novamente")
      }


    }
}