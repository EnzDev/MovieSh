package fr.enzomallard.moviesh.movie;

import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Enzo on 29/03/2017.
 */

public class Movie  {
    String title;
    String overview;
    Calendar releaseDate;
    double note; // Over 10

    ImageView poster;

    LinkedList<Integer> genres; // int identifiers

    // see : https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>&language=fr-FR


    public Movie(String title, String overview, Calendar releaseDate, double note, ImageView poster, LinkedList<Integer> genres) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.note = note;
        this.poster = poster;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public ImageView getPoster() {
        return poster;
    }

    public void setPoster(ImageView poster) {
        this.poster = poster;
    }

    public LinkedList<Integer> getGenres() {
        return genres;
    }

    public void setGenres(LinkedList<Integer> genres) {
        this.genres = genres;
    }
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
