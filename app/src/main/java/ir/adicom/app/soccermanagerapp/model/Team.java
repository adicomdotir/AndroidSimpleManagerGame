package ir.adicom.app.soccermanagerapp.model;

/**
 * Created by adicom on 2/23/18.
 */

public class Team {
    private int id;
    private String name;
    private String nickname;
    private int overral;
    private int teamId;
    private int game;
    private int win;
    private int lose;
    private int draw;
    private int gf;
    private int ga;
    private int pts;

    public Team() {}

    public Team(int id, String name, String nickname, int overral) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.overral = overral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getOverral() {
        return overral;
    }

    public void setOverral(int overral) {
        this.overral = overral;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getGa() {
        return ga;
    }

    public void setGa(int ga) {
        this.ga = ga;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }
}
