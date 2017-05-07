package at.fh_joanneum.newsly.newsly.db.entity;

/**
 * Created by edi on 07/05/2017.
 */

public class SourceSetting {

    private String name;
    private long id;
    private boolean active;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
