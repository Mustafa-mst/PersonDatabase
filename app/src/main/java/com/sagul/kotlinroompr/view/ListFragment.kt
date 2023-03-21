package com.sagul.kotlinroompr.view

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagul.kotlinroompr.R
import com.sagul.kotlinroompr.databinding.FragmentListBinding
import com.sagul.kotlinroompr.service.recylerAdapter
import com.sagul.kotlinroompr.viewModel.LocalViewModel


class ListFragment : Fragment() {
private lateinit var binding:FragmentListBinding
private lateinit var viewModel:LocalViewModel
private var adapter=recylerAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(LocalViewModel::class.java)
        viewModel.getAll()
        observeLiveData(view)
        binding.recyclerView.layoutManager=LinearLayoutManager(view.context)
        binding.recyclerView.adapter=adapter
        binding.floatingButton.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment()
            Navigation.findNavController(it).navigate(action)
        }


    }
    fun observeLiveData(view:View){
        viewModel.userList.observe(viewLifecycleOwner){
            adapter.refreshAdapter(ArrayList(it))
            if (it.size==0){
                Toast.makeText(view.context,"Liste Boş",Toast.LENGTH_LONG).show()
            }
        }

    }


}