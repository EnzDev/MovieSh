package fr.enzomallard.moviesh;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.enzomallard.moviesh.adapter.MovieAdapter;
import fr.enzomallard.moviesh.movie.Movie;

public class View_Search extends AppCompatActivity {
    MovieAdapter movieadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_search);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_max);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.search, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        movieadapter = new MovieAdapter(this, R.layout.movie_item, new ArrayList<Movie>());
        ((GridView)findViewById(R.id.movie_grid)).setAdapter(movieadapter);




        SearchView searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieadapter.addAll(moviedb.search(getApplicationContext(), movieadapter, query, Integer.parseInt(((Spinner)findViewById(R.id.spinner_max)).getSelectedItem().toString()) ));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ((GridView)findViewById(R.id.movie_grid)).setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { /* Will be replaced with an intent switch */
                Intent viewMovie = new Intent(parent.getContext(), View_Movie.class);
                moviedb.toDisplay = ((Movie)parent.getItemAtPosition(position));
                startActivity(viewMovie);
            }
        });

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
