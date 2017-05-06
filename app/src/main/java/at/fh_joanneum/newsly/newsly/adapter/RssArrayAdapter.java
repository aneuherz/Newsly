package at.fh_joanneum.newsly.newsly.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import at.fh_joanneum.newsly.newsly.R;
import at.fh_joanneum.newsly.newsly.parser.RssEntry;

/**
 * Created by aneuh on 06.05.2017.
 */

public class RssArrayAdapter extends ArrayAdapter<RssEntry> {
    public RssArrayAdapter(@NonNull Context context, @NonNull List<RssEntry> objects) {
        super(context, R.layout.rss_row_item, objects);
    }

    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDateAndAuthor;
        TextView txtDescription;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        RssEntry entry = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final View result;
        ViewHolder viewHolder; //
        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.rss_row_item, parent, false);
        viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.itemTitle);
        viewHolder.txtDateAndAuthor = (TextView) convertView.findViewById(R.id.itemDateAndAuthor);
        viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.itemDescription);

        convertView.setTag(viewHolder);

        viewHolder.txtTitle.setText(entry.getTitle());
        viewHolder.txtDateAndAuthor.setText(entry.getFormattedDateAndAuthor());
        viewHolder.txtDescription.setText(entry.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }
}
