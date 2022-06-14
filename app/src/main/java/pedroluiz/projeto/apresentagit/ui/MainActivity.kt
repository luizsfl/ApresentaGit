package pedroluiz.projeto.apresentagit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import org.koin.androidx.viewmodel.ext.android.viewModel
import pedroluiz.projeto.apresentagit.R
import pedroluiz.projeto.apresentagit.core.createProgressDialog
import pedroluiz.projeto.apresentagit.databinding.ActivityMainBinding
import pedroluiz.projeto.apresentagit.presentation.MainViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }

    private val viewModel by viewModel<MainViewModel>()

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    private val adapter by lazy{ RepoListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.rcRepos.adapter = adapter
         viewModel.repos.observe(this){
             when(it){
                 MainViewModel.State.Loading -> {dialog.show()}
                 is MainViewModel.State.Erro -> {
                     dialog.dismiss()
                 }
                 is MainViewModel.State.Sucess -> {
                     dialog.dismiss()
                     adapter.submitList(it.list)
                 }
             }
         }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchView =  menu.findItem(R.id.action_search).actionView  as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //Log.e(TAG,"change$newText")
        return false
    }

    companion object{
        private const val TAG = "TAG"
    }

}