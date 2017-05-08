package at.fh_joanneum.newsly.newsly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.fh_joanneum.newsly.newsly.adapter.RssArrayAdapter;
import at.fh_joanneum.newsly.newsly.db.entity.LinkSourceRessort;
import at.fh_joanneum.newsly.newsly.parser.DownloadRssTask;
import at.fh_joanneum.newsly.newsly.parser.RssEntry;

/**
 * Created by aneuh on 29.04.2017.
 */

public class TabNews extends ListFragment implements DownloadRssTask.AsyncResponse {

    private RssArrayAdapter adapter;

    private ListView listView;
    private RessortService ressortService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_news, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new RssArrayAdapter(getActivity(), new ArrayList<RssEntry>());
        listView = getListView();

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
        ressortService = new RessortService(getActivity().getApplicationContext());
        final List<LinkSourceRessort> links = ressortService.getAllFeasibleLinks();

        DownloadRssTask task = new DownloadRssTask(this);
        task.execute(links);
    }

    @Override
    public void processFinish(List<RssEntry> output) {
        adapter.addAll(output);
        setListAdapter(adapter);
    }
}


