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
public class Event {
    @Id(autoincrement = true)
    private Long id;
    private Long eventDetailId;
    @ToOne(joinProperty = "eventDetailId")
    private EventDetail eventDetail;
    private Long playerId;
    @ToOne(joinProperty = "playerId")
    private Player player;
    private Long matchId;
    @ToOne(joinProperty = "matchId")
    private Match match;
    private Long teamId;
    @ToOne(joinProperty = "teamId")
    private Team team;
    private boolean isGoal;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1542254534)
    private transient EventDao myDao;
    @Generated(hash = 311486488)
    public Event(Long id, Long eventDetailId, Long playerId, Long matchId,
            Long teamId, boolean isGoal) {
        this.id = id;
        this.eventDetailId = eventDetailId;
        this.playerId = playerId;
        this.matchId = matchId;
        this.teamId = teamId;
        this.isGoal = isGoal;
    }
    @Generated(hash = 344677835)
    public Event() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEventDetailId() {
        return this.eventDetailId;
    }
    public void setEventDetailId(Long eventDetailId) {
        this.eventDetailId = eventDetailId;
    }
    public Long getPlayerId() {
        return this.playerId;
    }
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
    public Long getMatchId() {
        return this.matchId;
    }
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    public Long getTeamId() {
        return this.teamId;
    }
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    public boolean getIsGoal() {
        return this.isGoal;
    }
    public void setIsGoal(boolean isGoal) {
        this.isGoal = isGoal;
    }
    @Generated(hash = 934211067)
    private transient Long eventDetail__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 296040032)
    public EventDetail getEventDetail() {
        Long __key = this.eventDetailId;
        if (eventDetail__resolvedKey == null
                || !eventDetail__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EventDetailDao targetDao = daoSession.getEventDetailDao();
            EventDetail eventDetailNew = targetDao.load(__key);
            synchronized (this) {
                eventDetail = eventDetailNew;
                eventDetail__resolvedKey = __key;
            }
        }
        return eventDetail;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1317768516)
    public void setEventDetail(EventDetail eventDetail) {
        synchronized (this) {
            this.eventDetail = eventDetail;
            eventDetailId = eventDetail == null ? null : eventDetail.getId();
            eventDetail__resolvedKey = eventDetailId;
        }
    }
    @Generated(hash = 570499689)
    private transient Long player__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1928466470)
    public Player getPlayer() {
        Long __key = this.playerId;
        if (player__resolvedKey == null || !player__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerDao targetDao = daoSession.getPlayerDao();
            Player playerNew = targetDao.load(__key);
            synchronized (this) {
                player = playerNew;
                player__resolvedKey = __key;
            }
        }
        return player;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1850710237)
    public void setPlayer(Player player) {
        synchronized (this) {
            this.player = player;
            playerId = player == null ? null : player.getId();
            player__resolvedKey = playerId;
        }
    }
    @Generated(hash = 74816300)
    private transient Long match__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 720517776)
    public Match getMatch() {
        Long __key = this.matchId;
        if (match__resolvedKey == null || !match__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MatchDao targetDao = daoSession.getMatchDao();
            Match matchNew = targetDao.load(__key);
            synchronized (this) {
                match = matchNew;
                match__resolvedKey = __key;
            }
        }
        return match;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 181590624)
    public void setMatch(Match match) {
        synchronized (this) {
            this.match = match;
            matchId = match == null ? null : match.getId();
            match__resolvedKey = matchId;
        }
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
    @Generated(hash = 1459865304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEventDao() : null;
    }
}
