package com.example.retrofits.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofits.R
import com.example.retrofits.model.Voting
import kotlinx.android.synthetic.main.item_retrofits.view.*

class RetrofitsAdapter(var votingList:ArrayList<Voting>):
    RecyclerView.Adapter<RetrofitsAdapter.RetrofitsViewHolder>() {
    //private var votingList = emptyList<Voting>()

    var mClickListener: ClickListener? = null

    fun setClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    inner class RetrofitsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var voting: Voting

        init {
            itemView.setOnClickListener(this)//initialize onClick fun:s and/ when start create obj,it works
        }
        fun bindVoting(voting: Voting) {
            this.voting=voting
            itemView.tvName.text = voting.name
        }

        override fun onClick(v: View?) {
            mClickListener?.onClick(voting)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitsViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_retrofits, parent, false)
        return RetrofitsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return votingList.size
    }

    /*fun setVotes(votes: List<Voting>){
        this.votingList=votes
        notifyDataSetChanged()
    }*/

    override fun onBindViewHolder(holder: RetrofitsViewHolder, position: Int) {
        holder.bindVoting(votingList[position])
    }

    interface ClickListener {
        fun onClick(voting: Voting)
    }

}