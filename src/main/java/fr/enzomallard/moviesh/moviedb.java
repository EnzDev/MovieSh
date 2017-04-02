package fr.enzomallard.moviesh;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


import fr.enzomallard.moviesh.adapter.MovieAdapter;
import fr.enzomallard.moviesh.movie.Movie;

/**
 * Created by Enzo on 01/04/2017.
 * moviedb statics functions
 */

public class moviedb {
    private static String MOVIEDB_API_KEY = "0e53faa4f7fc728716545dfe7ab1e1fb";
    public  static List<Movie> Populars = new ArrayList<>();
    private static  String POSTER_SIZE = "w154";
    private static  int LOADED_POP_PAGES = 0;


    public static List<Movie> getNextPopulars(Context context, MovieAdapter movieadapter) { /* French by default */
        LOADED_POP_PAGES++;
        return getNextPopulars(LOADED_POP_PAGES,"fr-FR", context, movieadapter);
    }

    public static List<Movie> getNextPopulars(int page,String lang, final Context context, final MovieAdapter movieadapter){ /* Context will often be this when called */

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + MOVIEDB_API_KEY +
                "&language=" + lang +
                "&page=" + page;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd", Locale.FRENCH);
                        try {
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject item = results.getJSONObject(i);

                                Picasso.with(context.getApplicationContext()).setIndicatorsEnabled(true);
                                ImageView iv = new ImageView(context.getApplicationContext());
                                Picasso.with(context.getApplicationContext())
                                        .load("https://image.tmdb.org/t/p/"+ POSTER_SIZE +item.get("poster_path"))
                                        .placeholder(R.drawable.load_data)
                                        .error(R.drawable.stat_notify_error)
                                        .into(iv, new com.squareup.picasso.Callback() {
                                            @Override
                                            public void onSuccess() { movieadapter.notifyDataSetChanged(Populars);}

                                            @Override
                                            public void onError(){/* Just handled with the .error but we need to notify so we do the same thing as success*/
                                                movieadapter.notifyDataSetChanged(Populars);
                                            }
                                        });


                                String title = (String)item.get("title");
                                String overview = (String)item.get("overview");
                                Calendar date = Calendar.getInstance();
                                date.setTime(formater.parse((String)item.get("release_date")));
                                double note = 0;
                                if(item.get("vote_average") instanceof Double)
                                    note = (double) item.get("vote_average");
                                if(item.get("vote_average") instanceof Integer)
                                    note = ((Integer) item.get("vote_average")).doubleValue();


                                Movie m = new Movie(title, overview, date, note, iv, new LinkedList<Integer>());
                                Populars.add(m);

                                movieadapter.notifyDataSetChanged(Populars);
                            }
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Mmm...");
                    }
                });

        Requester.getInstance(context).addToRequestQueue(jsObjRequest);

        return Populars;
    }
}
