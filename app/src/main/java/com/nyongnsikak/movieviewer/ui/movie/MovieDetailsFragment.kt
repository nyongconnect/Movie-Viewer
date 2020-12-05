package com.nyongnsikak.movieviewer.ui.movie

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.nyongnsikak.movieviewer.R
import com.nyongnsikak.movieviewer.databinding.FragmentMovieBinding
import com.nyongnsikak.movieviewer.ui.zbase.BaseFragment
import com.nyongnsikak.movieviewer.utils.AppConstants
import com.nyongnsikak.movieviewer.utils.Result
import com.nyongnsikak.movieviewer.utils.loadImage
import com.nyongnsikak.movieviewer.utils.toastMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieDetailsFragment : BaseFragment() {

    lateinit var binding : FragmentMovieBinding
    private val args : MovieDetailsFragmentArgs by navArgs()
    private val viewModel : MovieViewModel by navGraphViewModels(R.id.nav_host_graph){
        defaultViewModelProviderFactory
    }

    var isFavorite = false

    private val favoriteImage by lazy {  ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite) }
    private val uncheckFavorite by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_unchecked_favorite) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().toolbar.title = args.movieItem.title
        binding = FragmentMovieBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movie_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_fav)
        if (isFavorite){
            menuItem.icon = favoriteImage
        }else{
            menuItem.icon = uncheckFavorite
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel.getFavoriteMovies()


        val movieItem = args.movieItem
        tvDescription.text = movieItem.overview
        tv_movie_title.text = movieItem.title
        tv_production_date.text = movieItem.release_date
        tv_rating.text = "(${movieItem.vote_count})"
        poster.loadImage(AppConstants.BASE_IMAGE_URL + movieItem.poster_path
        )
    }


    override fun observeData() {


        viewModel.favoriteMovies.observe(viewLifecycleOwner, Observer {result ->
            when(result){
                Result.Loading ->{}
                is Result.Error ->{}
                is Result.Success ->{
                    if(result.data?.filter {it.id == args.movieItem.id
                    }.isNullOrEmpty()){
                        isFavorite = false
                        activity?.invalidateOptionsMenu()
                    }
                    else  {
                        isFavorite = true
                        activity?.invalidateOptionsMenu()

                    }

                }
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_fav -> {
                if(item.icon == favoriteImage){
                    viewModel.removeFromFavorite(args.movieItem.id)
                    toastMessage(requireContext(), "Removed from favorites")
                }
                else{
                    viewModel.addToFavorite(args.movieItem)
                    toastMessage(requireContext(), "Added to favorites")
                }
                return true

            }
        }
        return super.onOptionsItemSelected(item)
    }


}
