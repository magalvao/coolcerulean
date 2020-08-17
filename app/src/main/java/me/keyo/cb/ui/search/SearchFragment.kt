package me.keyo.cb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.fragment_search_recyclerview
import kotlinx.android.synthetic.main.fragment_search.*
import me.keyo.cb.R
import me.keyo.cb.data.model.ProductSearchResponse
import me.keyo.cb.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchViewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        binding.viewModel = searchViewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val searchTerm: String? = arguments?.getString("searchTerm", "")
        val productsLiveData = searchViewModel.performSearch(searchTerm)

        searchProducts(searchTerm, productsLiveData)
    }

    private fun searchProducts(
        searchTerm: String?,
        productsLiveData: LiveData<ProductSearchResponse>
    ) {
        fragment_search_progress_bar.visibility = View.VISIBLE
        fragment_search_recyclerview.visibility = View.INVISIBLE
        fagment_search_edit.setText(searchTerm)

        productsLiveData.observe(viewLifecycleOwner, Observer { t ->
            updateSearchResults(t)
        })
    }

    private fun updateSearchResults(r: ProductSearchResponse?) {
        viewAdapter = SearchAdapter(r!!)
        viewAdapter.onNextClick = {
            performSearch(fagment_search_edit.text.toString(), it)
        }
        viewAdapter.onPrevClick = {
            performSearch(fagment_search_edit.text.toString(), it)
        }
        viewAdapter.notifyDataSetChanged()

        fragment_search_recyclerview.adapter = viewAdapter
        fragment_search_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_search_progress_bar.visibility = View.GONE
        fragment_search_recyclerview.visibility = View.VISIBLE
    }

    fun performSearch(searchTerm: String) {
        performSearch(searchTerm, 1)
    }
    fun performSearch(searchTerm: String, page: Int) {

        val productsLiveData = searchViewModel.performSearch(searchTerm, page)
        searchProducts(searchTerm, productsLiveData)
    }
    val performSearch: Function1<String, Unit> = this::performSearch
}