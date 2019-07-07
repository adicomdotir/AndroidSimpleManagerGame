package ir.adicom.app.soccermanagerapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by adicom on 2/23/18.
 */

@Entity
public class Player {
    @Id(autoincrement = true)
    private Long id;
    private Long teamId;
    @ToOne(joinProperty = "teamId")
    private Team team;
    private String name;
    private float scoring;
    private float goalkeeper;
    private float defending;
    private int injury;
    private int morale;
    private int stamina;
    private int age;
    private int ageSub;
    private int shirtNumber;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2108114900)
    private transient PlayerDao myDao;
    @Generated(hash = 1834174654)
    private transient Long team__resolvedKey;

    public Player() {}

    public Player(Long id, String name, Long teamId) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
    }

    @Generated(hash = 2037801544)
    public Player(Long id, Long teamId, String name, float scoring,
            float goalkeeper, float defending, int injury, int morale, int stamina,
            int age, int ageSub, int shirtNumber) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.scoring = scoring;
        this.goalkeeper = goalkeeper;
        this.defending = defending;
        this.injury = injury;
        this.morale = morale;
        this.stamina = stamina;
        this.age = age;
        this.ageSub = ageSub;
        this.shirtNumber = shirtNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInjury() {
        return injury;
    }

    public void setInjury(int injury) {
        this.injury = injury;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public float getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(float goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public float getScoring() {
        return scoring;
    }

    public void setScoring(float scoring) {
        this.scoring = scoring;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getDefending() {
        return defending;
    }

    public void setDefending(float defending) {
        this.defending = defending;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getAgeSub() {
        return ageSub;
    }

    public void setAgeSub(int ageSub) {
        this.ageSub = ageSub;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

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
    @Generated(hash = 1600887847)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlayerDao() : null;
    }
}
