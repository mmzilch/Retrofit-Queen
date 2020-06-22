package com.example.retrofits.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofits.R
import com.example.retrofits.model.Voting
import com.example.retrofits.adapter.RetrofitsAdapter
import com.example.retrofits.api.VotingApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RetrofitsAdapter.ClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getQueen()
    }

    fun loadUserList(queenList: List<Voting>) {
        rcyRetrofic.apply {
            val votingAdapter = RetrofitsAdapter(queenList as ArrayList<Voting>)
            layoutManager = LinearLayoutManager(context)
            votingAdapter.setClickListener(this@MainActivity)
            adapter = votingAdapter
            /*var votingAdapter= RetrofitsAdapter(queenList as ArrayList<Voting>)//queen name list to adapter and bind data
        rcyRetrofic.layoutManager= LinearLayoutManager(this@MainActivity)
        rcyRetrofic.adapter=votingAdapter*/
        }
    }

    fun getQueen() {

        var BASE_URL = "https://ucsmonywaonlinevote.000webhostapp.com/api/"//must be string

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //create retrofit//
        // converterfactory is use to convert json format to gson

        var retrofitService = retrofit.create(VotingApiInterface::class.java)//create  api interface

        var apiCall = retrofitService.getQueen()//call query but don't work

        apiCall.enqueue(object : Callback<List<Voting>> {
            //to get data call with enqueue and use callback keyword
            //must be the same with interface >>> Call<List<Voting>>
            override fun onFailure(call: Call<List<Voting>>, t: Throwable) {//fail
                Log.d("Failure>>>>>>", t.toString())
            }

            override fun onResponse(
                call: Call<List<Voting>>,
                response: Response<List<Voting>>
            ) {//data success
                var queenList = response.body()//to get all json code (body)
                Log.d("Response>>>>>>", queenList.toString())
                loadUserList(queenList!!)
            }
        })
    }

    override fun onClick(voting: Voting) {
        Toast.makeText(applicationContext, "${voting.name}", Toast.LENGTH_LONG).show()
            val intent =
                Intent(this@MainActivity, NextActivity::class.java)
            intent.putExtra("name",voting.name)
            intent.putExtra("Image",voting.img_url)
            startActivity(intent)

    }

}
