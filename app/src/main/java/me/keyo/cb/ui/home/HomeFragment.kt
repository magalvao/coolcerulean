package me.keyo.cb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import me.keyo.cb.R
import me.keyo.cb.data.local.CategoriesLocalEndpoint
import me.keyo.cb.databinding.FragmentHomeBinding
import me.keyo.cb.ui.search.SearchFragment

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewAdapter: CategoriesAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val localCategories = CategoriesLocalEndpoint(requireContext()).getCategories()
        viewAdapter = CategoriesAdapter(localCategories)
        viewAdapter.onItemClick = {
            performSearch(it.tag)
        }
        viewAdapter.notifyDataSetChanged()

        fragment_search_recyclerview.adapter = viewAdapter
        fragment_search_recyclerview.layoutManager = GridLayoutManager(context, 2)

        fragment_home_progress_bar.visibility = View.INVISIBLE
    }

    fun performSearch(searchTerm: String) {
        val searchFragment = SearchFragment()
        searchFragment.arguments = Bundle(1).also { bundle -> bundle.putString("searchTerm", searchTerm) }

        parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, searchFragment).commit();
    }
    val performSearch: Function1<String, Unit> = this::performSearch

}