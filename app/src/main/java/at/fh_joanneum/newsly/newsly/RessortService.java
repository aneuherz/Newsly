package at.fh_joanneum.newsly.newsly;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import at.fh_joanneum.newsly.newsly.db.RessortSettingsRepository;
import at.fh_joanneum.newsly.newsly.db.SourceRepository;
import at.fh_joanneum.newsly.newsly.db.SourceSettingsRepository;
import at.fh_joanneum.newsly.newsly.db.entity.LinkSourceRessort;
import at.fh_joanneum.newsly.newsly.db.entity.RessortSetting;
import at.fh_joanneum.newsly.newsly.db.entity.Source;
import at.fh_joanneum.newsly.newsly.db.entity.SourceSetting;

/**
 * Created by edi on 07/05/2017.
 */

class RessortService {
    private final Context context;
    private List<RessortSetting> ressortSettings;
    private List<SourceSetting> sourceSettings;

    RessortService(final Context context) {
        this.context = context;
        loadSettings();
    }

    private void loadSettings() {
        SourceSettingsRepository sourceSettingsRepository = new SourceSettingsRepository(context);
        sourceSettings = sourceSettingsRepository.findAllActiveSourceSettings();

        RessortSettingsRepository ressortSettingsRepository = new RessortSettingsRepository(context);
        ressortSettings = ressortSettingsRepository.findAllActiveRessortSettings();
    }

    public List<LinkSourceRessort> getAllFeasibleLinks() {
        final List<String> names = new ArrayList<>();
        for (SourceSetting setting : sourceSettings) {
            names.add(setting.getName());
        }

        final SourceRepository sourceRepository = new SourceRepository(context);
        final List<Source> sources = sourceRepository.findAllSourceByNames(names);

        final List<LinkSourceRessort> linkSourceRessorts = new ArrayList<>();
        // @richi: einfach nicht beachten... war zu faul
        for (Source source : sources) {
            for (RessortSetting ressortSetting : ressortSettings) {
                if (ressortSetting.getCategory() == null) {
                    continue;
                }
                switch (ressortSetting.getCategory()) {
                    case CULTURE:
                        linkSourceRessorts.add(new LinkSourceRessort(source.getCultureLink(), source.getName(), ressortSetting.getCategory().getValue()));
                        break;
                    case ECONOMY:
                        linkSourceRessorts.add(new LinkSourceRessort(source.getEconomyLink(), source.getName(), ressortSetting.getCategory().getValue()));
                        break;
                    case EDUCATION:
                        linkSourceRessorts.add(new LinkSourceRessort(source.getEducationLink(), source.getName(), ressortSetting.getCategory().getValue()));
                        break;
                    case LIFE:
                        linkSourceRessorts.add(new LinkSourceRessort(source.getLifeLink(), source.getName(), ressortSetting.getCategory().getValue()));
                        break;
                    case POLITICS:
                        linkSourceRessorts.add(new LinkSourceRessort(source.getPoliticsLink(), source.getName(), ressortSetting.getCategory().getValue()));
                        break;
                    case SPORT:
                        linkSourceRessorts.add(new LinkSourceRessort(source.getSportLink(), source.getName(), ressortSetting.getCategory().getValue()));
                        break;
                    default:
                        throw new IllegalStateException("Requesting Unsupported Category");
                }
            }
        }

        return linkSourceRessorts;
    }
}