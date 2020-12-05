package com.nyongnsikak.movieviewer.ui.movie

import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nyongnsikak.movieviewer.R
import com.nyongnsikak.movieviewer.databinding.FragmentMovieListBinding
import com.nyongnsikak.movieviewer.databinding.ItemMovieListBinding
import com.nyongnsikak.movieviewer.ui.movie.model.MovieDiffUtil
import com.nyongnsikak.movieviewer.ui.movie.model.MovieItem
import com.nyongnsikak.movieviewer.ui.movie.viewholder.MovieViewHolder
import com.nyongnsikak.movieviewer.ui.zbase.BaseFragment
import com.nyongnsikak.movieviewer.ui.zcustom.RecyclerViewAdapter
import com.nyongnsikak.movieviewer.ui.zcustom.ViewHolder
import com.nyongnsikak.movieviewer.utils.PreferenceHelper
import com.nyongnsikak.movieviewer.utils.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment: BaseFragment(), CompoundButton.OnCheckedChangeListener {


    @Inject
    lateinit var preferenceHelper : PreferenceHelper

    lateinit var binding : FragmentMovieListBinding
    private val viewModel : MovieViewModel by navGraphViewModels(R.id.nav_host_graph){
        defaultViewModelProviderFactory
    }
    lateinit var movieAdapter : RecyclerViewAdapter<MovieItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding
                .inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchMaterial.setOnCheckedChangeListener(this)
        setUpMovieAdapter()
        viewModel.setColumn(preferenceHelper.isLinear)
        initRecyclerView()

    }

    private fun setUpMovieAdapter() {
        movieAdapter = object : RecyclerViewAdapter<MovieItem>(MovieDiffUtil()) {
            override fun getLayoutRes(model: MovieItem): Int {
                return R.layout.item_movie_list
            }

            override fun getViewHolder(
                    view: View,
                    recyclerViewAdapter: RecyclerViewAdapter<MovieItem>
            ): ViewHolder<MovieItem> {
                return MovieViewHolder(
                        ItemMovieListBinding.bind(view)
                ) {
                    val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(it)
                    launchFragment(action)
                }
            }

        }
    }

    private fun initRecyclerView() {
        binding.rvMovieList.apply {
            layoutManager = GridLayoutManager(requireContext(), viewModel.gridCount)
            adapter = movieAdapter

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.toggle ->{
                preferenceHelper.isLinear = !preferenceHelper.isLinear
                viewModel.setColumn(preferenceHelper.isLinear)
                initRecyclerView()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayFavoriteMovies() {
        viewModel.isAllMoviesSelected = false
        viewModel.getFavoriteMovies()
    }

    private fun displayAllMovies() {
        viewModel.isAllMoviesSelected = true
        viewModel.getAllMovies()
    }

    private fun showEmptyState(){
        tv_empty_state.visibility = View.VISIBLE
        rv_movie_list.visibility = View.INVISIBLE
    }
    private fun hideEmptyState(){
        tv_empty_state.visibility = View.INVISIBLE
        rv_movie_list.visibility = View.VISIBLE
    }


    override fun observeData() {

        viewModel.favoriteMovies.observe(viewLifecycleOwner, Observer {result->
            when(result){
                Result.Loading ->{


                }
                is Result.Error ->{

                }
                is Result.Success ->{
                    if (!viewModel.isAllMoviesSelected){
                        setToolbarTitle(MENU_CODE_FAVORITE)
                       if (result.data.isNullOrEmpty()){
                           showEmptyState()
                       }else{
                           hideEmptyState()
                           movieAdapter.submitList(result.data)
                           movieAdapter.notifyDataSetChanged()
                       }

                    }
                }
            }
        })

        viewModel.movies.observe(viewLifecycleOwner, Observer {result ->
            when (result){

                Result.Loading ->{


                }
                is Result.Error ->{

                }
                is Result.Success ->{
                    if (viewModel.isAllMoviesSelected){
                        hideEmptyState()
                        setToolbarTitle(MENU_CODE_ALL_MOVIES)
                        movieAdapter.submitList(result.data?.results!!)
                        movieAdapter.notifyDataSetChanged()
                    }

                }
            }

        })
    }

    private fun setToolbarTitle(menuCode : Int) {
        if (menuCode == MENU_CODE_ALL_MOVIES)
            requireActivity().toolbar.title = "Movies"
        else requireActivity().toolbar.title = "Favorites"
    }

    companion object{
        const val MENU_CODE_ALL_MOVIES = 0
        const val MENU_CODE_FAVORITE = 1
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked){
            displayFavoriteMovies()
        }else  displayAllMovies()

    }

}