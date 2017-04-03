package fr.enzomallard.moviesh;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Enzo on 02/04/2017.
 */

public class Refresher implements Runnable {
    private SwipeRefreshLayout myRefreshLayout;
    private Activity daddy;

    public Refresher(SwipeRefreshLayout myRefreshLayout, Context daddy) {
        this.myRefreshLayout = myRefreshLayout;
        this.daddy = (Activity)daddy;
    }

    @Override
    public void run() {
        while(moviedb.updateCounterPop < moviedb.objectiveCounterPop){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        daddy.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                myRefreshLayout.setRefreshing(false);
            }
        });
    }
}
