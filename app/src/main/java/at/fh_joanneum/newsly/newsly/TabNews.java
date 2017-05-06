package at.fh_joanneum.newsly.newsly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.fh_joanneum.newsly.newsly.adapter.RssArrayAdapter;
import at.fh_joanneum.newsly.newsly.parser.DownloadRssTask;
import at.fh_joanneum.newsly.newsly.parser.RssEntry;

import static android.R.id.list;

/**
 * Created by aneuh on 29.04.2017.
 */

public class TabNews extends ListFragment implements DownloadRssTask.AsyncResponse {

    private RssArrayAdapter adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_news, container, false );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView =  getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                RssEntry entry = (RssEntry) parent.getItemAtPosition(position);

                                                NewsWebViewActivity.CURRENT_RSS_ENTRY = entry;
                                                Intent intent = new Intent(getActivity(), NewsWebViewActivity.class);
                                                startActivity(intent);
                                            }
                                        }
        );
        DownloadRssTask task = new DownloadRssTask(this);
        task.execute("http://derStandard.at/?page=rss&ressort=Sport");
    }

    @Override
    public void processFinish(List<RssEntry> output) {
        adapter = new RssArrayAdapter(getActivity(), output);
        setListAdapter(adapter);
    }
}


