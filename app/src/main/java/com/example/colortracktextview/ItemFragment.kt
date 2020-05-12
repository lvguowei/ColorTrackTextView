package com.example.colortracktextview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = arguments?.getString("item")
        return view
    }

    companion object {
        fun newInstance(item: String): ItemFragment {
            val fragment = ItemFragment()
            val bundle = Bundle()
            bundle.putString("item", item)
            fragment.arguments = bundle
            return fragment
        }
    }
}