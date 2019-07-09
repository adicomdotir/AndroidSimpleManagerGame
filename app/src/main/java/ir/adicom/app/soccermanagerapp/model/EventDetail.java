package ir.adicom.app.soccermanagerapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by adicom on 7/9/19.
 */

@Entity
public class EventDetail {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private int eventType; // 0-7 Chance, 8 Booking, 9 Injury
    @Generated(hash = 493928593)
    public EventDetail(Long id, String title, int eventType) {
        this.id = id;
        this.title = title;
        this.eventType = eventType;
    }
    @Generated(hash = 1365267797)
    public EventDetail() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getEventType() {
        return this.eventType;
    }
    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
