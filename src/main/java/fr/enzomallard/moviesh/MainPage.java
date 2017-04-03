package fr.enzomallard.moviesh;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.enzomallard.moviesh.adapter.MovieAdapter;
import fr.enzomallard.moviesh.listener.onChangeCTL;
import fr.enzomallard.moviesh.movie.Movie;

public class MainPage extends AppCompatActivity {
    static boolean HAS_FIRST_LOAD = false;
    MovieAdapter movieadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { /* todo: Create a search activity mixed with search result */
                Snackbar.make(view, "Search", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        movieadapter = new MovieAdapter(this, R.layout.movie_item, new ArrayList<Movie>());
        ((GridView)findViewById(R.id.movie_grid_popular)).setAdapter(movieadapter);

        movieadapter.clear();
        if(HAS_FIRST_LOAD) {
            movieadapter.addAll(moviedb.Populars);
        } else {
            HAS_FIRST_LOAD = true;
            movieadapter.addAll(moviedb.getNextPopulars(this, movieadapter));
        }

        ((GridView)findViewById(R.id.movie_grid_popular)).setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { /* Will be replaced with an intent switch */
                Toast.makeText(MainPage.this, ((Movie)parent.getItemAtPosition(position)).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        SwipeRefreshLayout refreshLayout = ((SwipeRefreshLayout)findViewById(R.id.refresh_popular));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviedb.refreshPopulars(MainPage.this, movieadapter, ((SwipeRefreshLayout)findViewById(R.id.refresh_popular)));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);

        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar);

        appbar.addOnOffsetChangedListener(new onChangeCTL() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) { // Used to add the search icon on collapse
                MenuItem m = menu.findItem(R.id.action_search);
                if(state == State.COLLAPSED)
                    m.setVisible(true);
                else
                    m.setVisible(false);

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        Toast.makeText(MainPage.this, getResources().getResourceEntryName(id) , Toast.LENGTH_SHORT).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh_popular)
            moviedb.refreshPopulars(MainPage.this, movieadapter, ((SwipeRefreshLayout)findViewById(R.id.refresh_popular)));

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
