package ir.adicom.app.soccermanagerapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by adicom on 7/9/19.
 */

@Entity
public class Table {
    @Id(autoincrement = true)
    private Long id;
    private Long teamId;
    @ToOne(joinProperty = "teamId")
    private Team team;
    private int win;
    private int lose;
    private int draw;
    private int gf;
    private int ga;
    private int pts;
    private int div;
    private int group;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1200932703)
    private transient TableDao myDao;
    @Generated(hash = 1896076272)
    public Table(Long id, Long teamId, int win, int lose, int draw, int gf, int ga,
            int pts, int div, int group) {
        this.id = id;
        this.teamId = teamId;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.gf = gf;
        this.ga = ga;
        this.pts = pts;
        this.div = div;
        this.group = group;
    }
    @Generated(hash = 752389689)
    public Table() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTeamId() {
        return this.teamId;
    }
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    public int getWin() {
        return this.win;
    }
    public void setWin(int win) {
        this.win = win;
    }
    public int getLose() {
        return this.lose;
    }
    public void setLose(int lose) {
        this.lose = lose;
    }
    public int getDraw() {
        return this.draw;
    }
    public void setDraw(int draw) {
        this.draw = draw;
    }
    public int getGf() {
        return this.gf;
    }
    public void setGf(int gf) {
        this.gf = gf;
    }
    public int getGa() {
        return this.ga;
    }
    public void setGa(int ga) {
        this.ga = ga;
    }
    public int getPts() {
        return this.pts;
    }
    public void setPts(int pts) {
        this.pts = pts;
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
    @Generated(hash = 1834174654)
    private transient Long team__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1932727645)
    public Team getTeam() {
        Long __key = this.teamId;
        if (team__resolvedKey == null || !team__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team teamNew = targetDao.load(__key);
            synchronized (this) {
                team = teamNew;
                team__resolvedKey = __key;
            }
        }
        return team;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2066406844)
    public void setTeam(Team team) {
        synchronized (this) {
            this.team = team;
            teamId = team == null ? null : team.getId();
            team__resolvedKey = teamId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1052514995)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTableDao() : null;
    }
}
