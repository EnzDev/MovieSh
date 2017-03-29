package fr.enzomallard.moviesh.movie;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Enzo on 29/03/2017.
 */

public class Movie implements Adapter {
    String title;
    String overview;
    Date   releaseDate;
    double note; // Over 10

    LinkedList<Integer> genres; // int identifiers

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    // see : https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>&language=fr-FR

}

/*
      "poster_path": "/sGyVDq1mj8oIc9wRIOxcVHfAIdN.jpg",
      "adult": false,
      "overview": "Une nouvelle adaptation live du conte \"La Belle et la Bête\".  Belle, jeune fille rêveuse et passionnée de littérature, vit avec son père, un vieil inventeur farfelu. S'étant perdu une nuit dans la fôret, ce dernier se réfugie au château de la Bête, qui la jette au cachot. Ne pouvant supporter de voir son père emprisonné, Belle accepte alors de prendre sa place, ignorant que sous le masque du monstre se cache un Prince Charmant tremblant d'amour pour elle, mais victime d'une terrible malédiction.",
      "release_date": "2017-03-15",
      "genre_ids": [
        14,
        10402,
        10749
      ],
      "id": 321612,
      "original_title": "Beauty and the Beast",
      "original_language": "en",
      "title": "La Belle et la Bête",
      "backdrop_path": "/6aUWe0GSl69wMTSWWexsorMIvwU.jpg",
      "popularity": 175.570344,
      "vote_count": 912,
      "video": false,
      "vote_average": 7.2
* */
