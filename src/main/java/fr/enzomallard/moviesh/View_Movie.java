package fr.enzomallard.moviesh;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import fr.enzomallard.moviesh.movie.Genre;
import fr.enzomallard.moviesh.movie.Movie;

public class View_Movie extends AppCompatActivity {
    private static final String BANNER_SIZE = "w1280";
    private static final String POSTER_SIZE = "w780";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);


        Movie g = moviedb.toDisplay;

        System.out.println(getResources().getConfiguration().orientation);

        if(getResources().getConfiguration().orientation == 2)
            Picasso.with(this)
                .load("https://image.tmdb.org/t/p/"+ POSTER_SIZE + g.getPosterUrl())
//                .placeholder(R.drawable.load_data)
                .error(R.drawable.stat_notify_error)
                .into((ImageView) findViewById(R.id.the_movie_Lposter));
        else
            Picasso.with(this)
                    .load("https://image.tmdb.org/t/p/"+ BANNER_SIZE + g.getBannerUrl())
//                    .placeholder(R.drawable.load_data)
                    .error(R.drawable.stat_notify_error)
                    .into((ImageView) findViewById(R.id.the_movie_Lposter));

        ((TextView)findViewById(R.id.the_movie_overview)).setMovementMethod(new ScrollingMovementMethod());


        ((TextView)findViewById(R.id.the_movie_title)).setText(g.getTitle());
        ((TextView)findViewById(R.id.the_movie_date)).setText("(" + g.getReleaseDate().get(Calendar.YEAR) + ")");
        ((RatingBar)findViewById(R.id.the_movie_rating)).setRating((float)g.getNote()/2);
        ((TextView)findViewById(R.id.the_movie_overview)).setText(g.getOverview());

        String genres = "";
        Genre LGenre = new Genre(this);
        for(int i : g.getGenres()) genres += LGenre.toString(i) + ", ";
        genres = genres.substring(0, genres.length()-2);
        ((TextView)findViewById(R.id.the_movie_genres)).setText(genres);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override /* Action for the back button*/
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
