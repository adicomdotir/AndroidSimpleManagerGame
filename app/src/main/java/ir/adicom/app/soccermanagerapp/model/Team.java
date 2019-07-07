package ir.adicom.app.soccermanagerapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by adicom on 2/23/18.
 */

@Entity
public class Team {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int div;
    private int group;
    @Generated(hash = 2112868744)
    public Team(Long id, String name, int div, int group) {
        this.id = id;
        this.name = name;
        this.div = div;
        this.group = group;
    }
    @Generated(hash = 882286361)
    public Team() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDiv() {
        return this.div;
    }
    public void setDiv(int div) {
        this.div = div;
    }
    public int getGroup() {
        return this.group;
    }
    public void setGroup(int group) {
        this.group = group;
    }
}
