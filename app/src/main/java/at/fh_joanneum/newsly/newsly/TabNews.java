package at.fh_joanneum.newsly.newsly;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import at.fh_joanneum.newsly.newsly.parser.DownloadRssTask;
import at.fh_joanneum.newsly.newsly.parser.RssEntry;

/**
 * Created by aneuh on 29.04.2017.
 */

public class TabNews extends ListFragment implements DownloadRssTask.AsyncResponse {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

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
        for (RssEntry entry:
                output) {
            listItems.add(entry.toString());
        }


        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
    }
}


