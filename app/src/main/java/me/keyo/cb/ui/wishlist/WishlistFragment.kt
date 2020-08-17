package me.keyo.cb.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.keyo.cb.R

class WishlistFragment : Fragment() {

    private lateinit var wishlistViewModel: WishlistViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        wishlistViewModel =
                ViewModelProviders.of(this).get(WishlistViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tbd, container, false)
        val textView: TextView = root.findViewById(R.id.text_tbd)
        wishlistViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}