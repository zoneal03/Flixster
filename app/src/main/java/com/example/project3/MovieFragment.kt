package com.example.project3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray



private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MovieFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, )
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.visibility = View.VISIBLE

        val client = AsyncHttpClient()
        //val params = RequestParams()
        //params["api-key"] = API_KEY

        client[

            "https://api.themoviedb.org/3/movie/now_playing?&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
            //params,
            object : JsonHttpResponseHandler()

            {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers?,
                    json: JSON) {

                    progressBar.hide()

                    json.toString()

                    val resultsJsonArr: JSONArray = json.jsonObject.getJSONArray("results") as JSONArray
                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<Movie>>(){}.type
                    val models = gson.fromJson<List<Movie>>(resultsJsonArr.toString(), arrayMovieType)
                    recyclerView.adapter = MovieViewAdapter(models)

                    Log.d("MovieFragment", "response successful")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    progressBar.hide()

                    throwable?.message?.let{
                        Log.e("MovieFragment", response.toString())
                    }
                }
            }
        ]

    }
}