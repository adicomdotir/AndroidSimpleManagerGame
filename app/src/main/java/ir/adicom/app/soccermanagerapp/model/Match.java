package ir.adicom.app.soccermanagerapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by adicom on 4/20/18.
 */

@Entity
public class Match {
    @Id(autoincrement = true)
    private Long id;
    private Long teamHomeId;
    @ToOne(joinProperty = "teamHomeId")
    private Team teamHome;
    private Long teamAwayId;
    @ToOne(joinProperty = "teamAwayId")
    private Team teamAway;
    private int goalTeamHome;
    private int goalTeamAway;
    private int weekId;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 522467795)
    private transient MatchDao myDao;
    @Generated(hash = 1289570287)
    public Match(Long id, Long teamHomeId, Long teamAwayId, int goalTeamHome,
            int goalTeamAway, int weekId) {
        this.id = id;
        this.teamHomeId = teamHomeId;
        this.teamAwayId = teamAwayId;
        this.goalTeamHome = goalTeamHome;
        this.goalTeamAway = goalTeamAway;
        this.weekId = weekId;
    }
    @Generated(hash = 1834681287)
    public Match() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTeamHomeId() {
        return this.teamHomeId;
    }
    public void setTeamHomeId(Long teamHomeId) {
        this.teamHomeId = teamHomeId;
    }
    public Long getTeamAwayId() {
        return this.teamAwayId;
    }
    public void setTeamAwayId(Long teamAwayId) {
        this.teamAwayId = teamAwayId;
    }
    public int getGoalTeamHome() {
        return this.goalTeamHome;
    }
    public void setGoalTeamHome(int goalTeamHome) {
        this.goalTeamHome = goalTeamHome;
    }
    public int getGoalTeamAway() {
        return this.goalTeamAway;
    }
    public void setGoalTeamAway(int goalTeamAway) {
        this.goalTeamAway = goalTeamAway;
    }
    public int getWeekId() {
        return this.weekId;
    }
    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
    @Generated(hash = 1082614709)
    private transient Long teamHome__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1404278113)
    public Team getTeamHome() {
        Long __key = this.teamHomeId;
        if (teamHome__resolvedKey == null || !teamHome__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team teamHomeNew = targetDao.load(__key);
            synchronized (this) {
                teamHome = teamHomeNew;
                teamHome__resolvedKey = __key;
            }
        }
        return teamHome;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1252169474)
    public void setTeamHome(Team teamHome) {
        synchronized (this) {
            this.teamHome = teamHome;
            teamHomeId = teamHome == null ? null : teamHome.getId();
            teamHome__resolvedKey = teamHomeId;
        }
    }
    @Generated(hash = 25535182)
    private transient Long teamAway__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 166574429)
    public Team getTeamAway() {
        Long __key = this.teamAwayId;
        if (teamAway__resolvedKey == null || !teamAway__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team teamAwayNew = targetDao.load(__key);
            synchronized (this) {
                teamAway = teamAwayNew;
                teamAway__resolvedKey = __key;
            }
        }
        return teamAway;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 786345455)
    public void setTeamAway(Team teamAway) {
        synchronized (this) {
            this.teamAway = teamAway;
            teamAwayId = teamAway == null ? null : teamAway.getId();
            teamAway__resolvedKey = teamAwayId;
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
    @Generated(hash = 88911878)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMatchDao() : null;
    }
}
