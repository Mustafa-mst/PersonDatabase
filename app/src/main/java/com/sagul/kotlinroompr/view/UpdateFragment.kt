package com.sagul.kotlinroompr.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sagul.kotlinroompr.R
import com.sagul.kotlinroompr.databinding.FragmentUpdateBinding
import com.sagul.kotlinroompr.service.User
import com.sagul.kotlinroompr.viewModel.LocalViewModel


class UpdateFragment : Fragment() {
private lateinit var binding: FragmentUpdateBinding
private var gelenId=0
private lateinit var viewModel: LocalViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(LocalViewModel::class.java)
        arguments?.let {
            var gelenUser=UpdateFragmentArgs.fromBundle(it).customUser
            if (gelenUser==null){
                binding.updateBtn.setOnClickListener{
                    viewModel.insertData(User(0,binding.updateNameTxt.text.toString(),binding.updateAgeTxt.text.toString()))
                    Toast.makeText(view.context,"Kullanıcı Eklendi",Toast.LENGTH_LONG).show()
                    val action=UpdateFragmentDirections.actionUpdateFragmentToListFragment()
                    Navigation.findNavController(it).navigate(action)
                }
            }else{
                binding.updateBtn.setText("Güncelle")
                gelenId=gelenUser.id
                binding.updateNameTxt.setText(gelenUser.name)
                binding.updateAgeTxt.setText(gelenUser.age)
                binding.updateBtn.setOnClickListener{
                    val updateUser=User(gelenUser.id,binding.updateNameTxt.text.toString(),binding.updateAgeTxt.text.toString())
                    viewModel.updateData(updateUser)

                    Toast.makeText(view.context,"Kullanıcı Güncellendi",Toast.LENGTH_LONG).show()
                    val action=UpdateFragmentDirections.actionUpdateFragmentToListFragment()
                    Navigation.findNavController(it).navigate(action)

                }
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_row,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.deleteMenu){
            AlertDialog.Builder(requireContext()).setTitle("Geri Dönüşüm").setMessage("Bu kulllanıcıyı silmek istiyor musunuz ?").setPositiveButton("Evet",DialogInterface.OnClickListener { dialog, which ->
                viewModel.deleteData(User(gelenId,binding.updateNameTxt.text.toString(),binding.updateAgeTxt.text.toString()))
                val action=UpdateFragmentDirections.actionUpdateFragmentToListFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }).setNegativeButton("Hayır",DialogInterface.OnClickListener { dialog, which ->  }).create().show()
        }
        return super.onOptionsItemSelected(item)
    }

}