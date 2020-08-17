package me.keyo.cb.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.keyo.cb.R

class BagFragment : Fragment() {

    private lateinit var bagViewModel: BagViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bagViewModel =
                ViewModelProviders.of(this).get(BagViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tbd, container, false)
        val textView: TextView = root.findViewById(R.id.text_tbd)
        bagViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}