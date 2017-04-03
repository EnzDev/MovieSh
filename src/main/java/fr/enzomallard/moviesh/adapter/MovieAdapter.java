package fr.enzomallard.moviesh.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import fr.enzomallard.moviesh.R;
import fr.enzomallard.moviesh.movie.Movie;

/**
 * TODO: document your custom view class.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    private Activity context;
    private List<Movie> movieList;
    private int ilayout;

    public MovieAdapter(Activity context, int layout, List<Movie> movieList) {
        super(context, layout, movieList);
        this.context = context;
        this.movieList = movieList;
        this.ilayout = layout;
    }

    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView poster;
        TextView title;
        TextView dateY;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(ilayout, null, true);
            viewHolder = new ViewHolder();
            viewHolder.poster = (ImageView) rowView.findViewById(R.id.movie_poster);
            viewHolder.title = (TextView) rowView.findViewById(R.id.movie_title);
            viewHolder.dateY = (TextView) rowView.findViewById(R.id.movie_date);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        Movie item = movieList.get(position);
        viewHolder.poster.setImageDrawable( item.getPoster().getDrawable() );
        viewHolder.title.setText(item.getTitle());
        viewHolder.dateY.setText("(" + item.getReleaseDate().get(Calendar.YEAR) + ")");

        return rowView;
    }


    public void notifyDataSetChanged(List<Movie> movieList) {
        this.clear();
        this.addAll(movieList);
        super.notifyDataSetChanged();
    }

}
