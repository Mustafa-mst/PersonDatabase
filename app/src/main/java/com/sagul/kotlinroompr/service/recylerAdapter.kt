package com.sagul.kotlinroompr.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sagul.kotlinroompr.databinding.RecyclerRowBinding
import com.sagul.kotlinroompr.view.ListFragmentDirections

class recylerAdapter(val list:ArrayList<User>):RecyclerView.Adapter<recylerAdapter.UserVh>() {
    class UserVh(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVh {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserVh(binding)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: UserVh, position: Int) {
        holder.binding.rcyName.text=list.get(position).name
        holder.binding.rcyAge.text=list.get(position).age
        holder.itemView.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(User(list.get(position).id,list.get(position).name,list.get(position).age))
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun refreshAdapter(listem:ArrayList<User>){
        list.clear()
        list.addAll(listem)
        notifyDataSetChanged()
    }
}