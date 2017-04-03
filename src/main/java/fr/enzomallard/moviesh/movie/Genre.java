package fr.enzomallard.moviesh.movie;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;

import java.util.LinkedHashMap;
import fr.enzomallard.moviesh.R;

/**
 * Created by Enzo on 03/04/2017.
 */

public class Genre {
    LinkedHashMap<Integer, String> genres;
    private Context context;


    public Genre(Context context){
        this.genres = new LinkedHashMap<>();
        genres.put(28,   "ACTION");
        genres.put(12,   "AVENTURE");
        genres.put(16,   "ANIMATION");
        genres.put(35,   "COMEDIE");
        genres.put(80,   "CRIME");
        genres.put(99,   "DOCUMENTAIRE");
        genres.put(18,   "DRAME");
        genres.put(10751,"FAMILIAL");
        genres.put(14,   "FANTASTIQUE");
        genres.put(36,   "HISTOIRE");
        genres.put(27,   "HORREUR");
        genres.put(10402,"MUSIQUE");
        genres.put(9648, "MYSTERE");
        genres.put(10749,"ROMANCE");
        genres.put(878,  "SCIENCEFICTION");
        genres.put(10770,"TELEFILM");
        genres.put(53,   "THRILLER");
        genres.put(10752,"GUERRE");
        genres.put(37 ,  "WESTERN");

        this.context = context;
    }

    public String toString(int id){
        return context.getResources().getString(
                context.getResources().getIdentifier(
                        genres.containsKey(id) ? genres.get(id) : "UNK",
                        "string",
                        context.getPackageName()
                )
        );
    }

}
