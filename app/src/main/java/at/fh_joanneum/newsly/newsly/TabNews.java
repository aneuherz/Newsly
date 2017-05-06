package at.fh_joanneum.newsly.newsly;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import at.fh_joanneum.newsly.newsly.adapter.RssArrayAdapter;
import at.fh_joanneum.newsly.newsly.parser.DownloadRssTask;
import at.fh_joanneum.newsly.newsly.parser.RssEntry;

/**
 * Created by aneuh on 29.04.2017.
 */

public class TabNews extends ListFragment implements DownloadRssTask.AsyncResponse {
    private ArrayList<String> listItems=new ArrayList<String>();
    private RssArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_news, container, false );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DownloadRssTask task = new DownloadRssTask(this);
        task.execute("https://www.ots.at/rss/index");
    }

    @Override
    public void processFinish(List<RssEntry> output) {
        adapter = new RssArrayAdapter(getActivity(), output);
        setListAdapter(adapter);
    }
}


