package at.fh_joanneum.newsly.newsly.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import at.fh_joanneum.newsly.newsly.db.entity.RessortSetting;
import at.fh_joanneum.newsly.newsly.ressorts.RessortCategory;

/**
 * Created by edi on 06/05/2017.
 */

public class RessortSettingsRepository {
    private SettingsDBHelper settingsHelper;
    private SQLiteDatabase database;

    public RessortSettingsRepository(Context context) {
        settingsHelper = new SettingsDBHelper(context);
    }

    public List<RessortSetting> findAllActiveRessortSettings() {
        database = settingsHelper.getWritableDatabase();

        final List<RessortSetting> ressortSettings = new ArrayList<>();
        final String query = "SELECT  * FROM " + settingsHelper.TABLE_RESSORT_SETTINGS +
                " WHERE " + settingsHelper.COLUMN_SOURCE_SETTING_ACTIVE + " = 1";
        database = settingsHelper.getWritableDatabase();

        final Cursor cursor = database.rawQuery(query, null);

        final RessortSetting ressortSetting = new RessortSetting();

        if (cursor.moveToFirst()) {
            do {
                ressortSetting.setId(cursor.getLong(0));
                ressortSetting.setCategory(RessortCategory.getRessortCategory(cursor.getString(1)));
                ressortSetting.setActive(cursor.getInt(2) == 1);
                ressortSettings.add(ressortSetting);
            } while (cursor.moveToNext());
        }
        return ressortSettings;
    }
}
