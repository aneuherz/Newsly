package at.fh_joanneum.newsly.newsly.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import at.fh_joanneum.newsly.newsly.db.entity.SourceSetting;

/**
 * Created by edi on 07/05/2017.
 */

public class SourceSettingsRepository {
    private SettingsDBHelper settingsHelper;
    private SQLiteDatabase database;

    public SourceSettingsRepository(Context context) {
        settingsHelper = new SettingsDBHelper(context);
    }

    public List<SourceSetting> findAllActiveSourceSettings() {
        database = settingsHelper.getWritableDatabase();

        final List<SourceSetting> sourceSettings = new ArrayList<SourceSetting>();
        final String query = "SELECT  * FROM " + settingsHelper.TABLE_SOURCE_SETTINGS +
                " WHERE " + settingsHelper.COLUMN_SOURCE_SETTING_ACTIVE + " = 1";
        database = settingsHelper.getWritableDatabase();

        final Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                final SourceSetting sourceSetting = new SourceSetting();
                sourceSetting.setId(cursor.getLong(0));
                sourceSetting.setName(cursor.getString(1));
                sourceSetting.setActive(cursor.getInt(2) == 1);
                sourceSettings.add(sourceSetting);
            } while (cursor.moveToNext());
        }
        return sourceSettings;
    }
}
