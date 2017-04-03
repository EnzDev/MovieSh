package fr.enzomallard.moviesh.movie;

import android.widget.ImageView;


import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by Enzo on 29/03/2017.
 */

public class Movie {
    int id;
    String title;
    String overview;
    Calendar releaseDate;
    double note; // Over 10
    ImageView poster;
    LinkedList<Integer> genres; // int identifiers
    String posterUrl;
    String bannerUrl;

    private static final long serialVersionUID = 0L;
    // see : https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>&language=fr-FR


    public Movie(String title, String overview, Calendar releaseDate, double note, ImageView poster, LinkedList<Integer> genres, int id, String posterUrl, String bannerUrl) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.note = note;
        this.poster = poster;
        this.genres = genres;
        this.id = id;
        this.posterUrl = posterUrl;
        this.bannerUrl = bannerUrl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
