package fr.enzomallard.moviesh;

import android.content.Context;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.SearchView;

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
    public  static List<Movie> Research = new ArrayList<>();
    private static String POSTER_SIZE = "w154";
    private static int LOADED_POP_PAGES = 0;
    public  static int updateCounterPop = 0;
    public  static int objectiveCounterPop = 0;

    public static Movie toDisplay;


    public static List<Movie> getNextPopulars(Context context, MovieAdapter movieadapter) { /* French by default */
        LOADED_POP_PAGES++;
        return getNextPopulars(LOADED_POP_PAGES,"fr-FR", context, movieadapter);
    }

    public static List<Movie> getNextPopulars(int page,String lang, final Context context, final MovieAdapter movieadapter){ /* Context will often be "this" when called */

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

//                                Picasso.with(context.getApplicationContext()).setIndicatorsEnabled(true);
                                ImageView iv = new ImageView(context.getApplicationContext());
                                Picasso.with(context.getApplicationContext())
                                        .load("https://image.tmdb.org/t/p/"+ POSTER_SIZE +item.get("poster_path"))
                                        .placeholder(R.drawable.load_data)
                                        .error(R.drawable.stat_notify_error)
                                        .into(iv, new com.squareup.picasso.Callback() {
                                            @Override
                                            public void onSuccess() {
                                                updateCounterPop++;
                                                movieadapter.notifyDataSetChanged(Populars);
                                            }

                                            @Override
                                            public void onError(){/* Just handled with the .error but we need to notify so we do the same thing as success*/
                                                updateCounterPop++;
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

                                LinkedList<Integer> genres = new LinkedList<>();
                                JSONArray ids = item.getJSONArray("genre_ids");
                                for (int j = 0; j < ids.length(); j++) { genres.add(ids.getInt(j)); }

                                Movie m = new Movie(title, overview, date, note, iv, genres,item.getInt("id"),item.getString("poster_path") , item.getString("backdrop_path"));
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
                        if(Populars.size() == 0) View_MainPage.HAS_FIRST_LOAD = false;
                        System.out.println("Mmm...");
                    }
                });

        Requester.getInstance(context).addToRequestQueue(jsObjRequest);

        return Populars;
    }

    public static List<Movie> refreshPopulars(Context context, MovieAdapter movieadapter, SwipeRefreshLayout refreshLayout){
        int torefresh = LOADED_POP_PAGES;
        updateCounterPop = 0;
        objectiveCounterPop = 20*torefresh;

        Populars.clear();

        movieadapter.notifyDataSetChanged(Populars);

        (new Thread( new Refresher(refreshLayout, context) )).start();

        for (LOADED_POP_PAGES = 0; LOADED_POP_PAGES < torefresh; LOADED_POP_PAGES++)
            getNextPopulars(LOADED_POP_PAGES+1,"fr-FR", context, movieadapter);

        return Populars;
    }

    public static List<Movie> search(final Context context, final MovieAdapter movieadapter, String query, final int nreqs) {
        Research.clear();
        double pageToLoad = Math.ceil(((double)nreqs)/20);
        System.out.println(pageToLoad + " " + nreqs + " " + ((double)nreqs)/20);
        final int[] loaded = {0};

        for (int i = 0; i < pageToLoad; i++) {
            int page = i+1;
            String url = "https://api.themoviedb.org/3/search/movie?api_key=" + MOVIEDB_API_KEY +
                    "&language=" + "fr-FR" +
                    "&page=" + page +
                    "&query=" + Uri.encode(query);

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd", Locale.FRENCH);
                            try {
                                JSONArray results = response.getJSONArray("results");
                                for (int i = 0; i < results.length() && loaded[0] < nreqs; i++) {
                                    loaded[0]++;
                                    JSONObject item = results.getJSONObject(i);

                                    Picasso.with(context.getApplicationContext()).setIndicatorsEnabled(true);
                                    ImageView iv = new ImageView(context.getApplicationContext());
                                    Picasso.with(context.getApplicationContext())
                                            .load("https://image.tmdb.org/t/p/"+ POSTER_SIZE +item.get("poster_path"))
                                            .placeholder(R.drawable.load_data)
                                            .error(R.drawable.stat_notify_error)
                                            .into(iv, new com.squareup.picasso.Callback() {
                                                @Override
                                                public void onSuccess() {
                                                    movieadapter.notifyDataSetChanged(Research);
                                                }

                                                @Override
                                                public void onError(){ /* Just handled with the .error but we need to notify so we do the same thing as success*/
                                                    movieadapter.notifyDataSetChanged(Research);
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

                                    LinkedList<Integer> genres = new LinkedList<>();
                                    JSONArray ids = item.getJSONArray("genre_ids");
                                    for (int j = 0; j < ids.length(); j++) { genres.add(ids.getInt(j)); }

                                    Movie m = new Movie(title, overview, date, note, iv, genres,item.getInt("id"),item.getString("poster_path") , item.getString("backdrop_path"));
                                    Research.add(m);

                                    movieadapter.notifyDataSetChanged(Research);
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

        }



        return Research;
    }
}
