package com.acronymslutions.youtubeplayer_kotlin

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list_videos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListView : AppCompatActivity() {
    var listView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //calling the method to display the heroes
        heroes
    }//Creating an String array for the ListView
    //looping through all the heroes and inserting the names inside the string array
    //displaying the string array into listview

    //Here we are using the GsonConverterFactory to directly convert json data to object
    private val heroes: Unit
        private get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build()
            val api = retrofit.create(Api::class.java)
            val call: Call<List<Data>> =api.heroes
            call.enqueue(object : Callback<List<Data>> {
                override fun onResponse(
                    call: Call<List<Data>>,
                    response: Response<List<Data>>
                ) {
                    val heroList: List<Data> = response.body()!!
                    //Creating an String array for the ListView
                    val heroes =
                        arrayOfNulls<String>(heroList.size)
                    //looping through all the heroes and inserting the names inside the string array
                    for (i in heroList.indices) {
                        heroes[i] = heroList[i].name
                    }
                    //displaying the string array into listview


                    listViewHeroes.setAdapter(
                        ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_list_item_1,
                            heroes
                        )
                    )
                }

                override fun onFailure(
                    call: Call<List<Data>>,
                    t: Throwable
                ) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
}