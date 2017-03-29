package fr.enzomallard.moviesh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import fr.enzomallard.moviesh.movie.Movie;

/**
 * TODO: document your custom view class.
 */
public class MovieItem extends AdapterView<Movie> {


    public MovieItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Movie getAdapter() {
        return null;
    }

    @Override
    public void setAdapter(Movie adapter) {

    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }
}
