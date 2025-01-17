package com.example.version1_1.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.version1_1.R

class MainFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val boton: Button = view.findViewById(R.id.btn_lista)
        boton.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListFragment())
                .addToBackStack(null)
                .commit()
        }
        return view;
    }

}