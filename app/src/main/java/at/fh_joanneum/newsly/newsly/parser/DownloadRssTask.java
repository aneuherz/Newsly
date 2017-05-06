package at.fh_joanneum.newsly.newsly.parser;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import at.fh_joanneum.newsly.newsly.R;

/**
 * Created by aneuh on 29.04.2017.
 */

public class DownloadRssTask extends AsyncTask<String, Void, List<RssEntry>> {
    @Override
    protected List<RssEntry> doInBackground(String... urls) {
        return loadXmlFromNetwork(urls[0]);
    }

    public interface AsyncResponse {
        void processFinish(List<RssEntry> output);
    }

    public AsyncResponse delegate = null;

    public DownloadRssTask(AsyncResponse delegate){
        this.delegate = delegate;
    }
    @Override
    protected void onPostExecute(List<RssEntry> result) {
        delegate.processFinish(result);
    }

    public List<RssEntry> loadXmlFromNetwork(String urlString)  {
        InputStream stream = null;
        // Instantiate the parser
        RssParser rssParser = new RssParser();
        List<RssEntry> entries = null;

        try {
            stream = downloadUrl(urlString);
            entries = rssParser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch (XmlPullParserException e) {
            Log.e("Error", e.getStackTrace().toString());
        } catch (IOException e) {
            Log.e("Error", e.getStackTrace().toString());
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return entries;
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }
}