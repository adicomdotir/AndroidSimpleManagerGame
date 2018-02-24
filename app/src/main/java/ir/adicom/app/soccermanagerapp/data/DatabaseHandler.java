package ir.adicom.app.soccermanagerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.Team;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context context;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "manager";

    private static final String TABLE_TEAM = "Teams";
    private static final String TABLE_PLAYER = "Players";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NICKNAME = "nickname";

    private static final String KEY_PLAYER_ID = "id";
    private static final String KEY_PLAYER_NAME = "name";
    private static final String KEY_PLAYER_INJURY = "injury";
    private static final String KEY_PLAYER_MORALE = "morale";
    private static final String KEY_PLAYER_TEAMID = "teamid";
    private static final String KEY_PLAYER_POWER = "power";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEAM_TABLE = "CREATE TABLE " + TABLE_TEAM + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NICKNAME + " TEXT" + ")";
        db.execSQL(CREATE_TEAM_TABLE);

        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYER + "("
                + KEY_PLAYER_ID + " INTEGER PRIMARY KEY," + KEY_PLAYER_NAME + " TEXT,"
                + KEY_PLAYER_INJURY + " INTEGER," + KEY_PLAYER_MORALE + " INTEGER,"
                + KEY_PLAYER_TEAMID + " INTEGER," + KEY_PLAYER_POWER + " REAL" + ")";
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }

    public void deleteAllTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }

    public void addTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // values.put(KEY_ID, team.getId());
        values.put(KEY_NAME, team.getName());
        values.put(KEY_NICKNAME, team.getNickname());

        db.insert(TABLE_TEAM, null, values);
        db.close();
    }

    public Team getTeam(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TEAM, new String[] { KEY_ID,
                        KEY_NAME, KEY_NICKNAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        Team team = null;

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                team = new Team(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2)
                );
            }
        }

        return team;
    }

    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_TEAM + "";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(Integer.parseInt(cursor.getString(0)));
                team.setName(cursor.getString(1));
                team.setNickname(cursor.getString(2));

                teamList.add(team);
            } while (cursor.moveToNext());
        }

        return teamList;
    }

    public int updateTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, team.getId());
        values.put(KEY_NAME, team.getName());
        values.put(KEY_NICKNAME, team.getNickname());

        return db.update(TABLE_TEAM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(team.getId()) });
    }

    public void deleteTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAM, KEY_ID + " = ?",
                new String[] { String.valueOf(team.getId()) });
        db.close();
    }

    public int getTeamsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TEAM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public void addPlayer(Player Player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // values.put(KEY_PLAYER_ID, Player.getId());
        values.put(KEY_PLAYER_NAME, Player.getName());
        values.put(KEY_PLAYER_INJURY, Player.getInjury());
        values.put(KEY_PLAYER_MORALE, Player.getMorale());
        values.put(KEY_PLAYER_POWER, Player.getPower());
        values.put(KEY_PLAYER_TEAMID, Player.getTeamId());

        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    public Player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER, new String[] { KEY_PLAYER_ID,
                        KEY_PLAYER_NAME, KEY_PLAYER_INJURY, KEY_PLAYER_MORALE,
                        KEY_PLAYER_POWER, KEY_PLAYER_TEAMID
        }, KEY_PLAYER_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        Player player = null;

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                player = new Player();
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setName(cursor.getString(1));
                player.setInjury(Integer.parseInt(cursor.getString(2)));
                player.setMorale(Integer.parseInt(cursor.getString(3)));
                player.setPower(Float.parseFloat(cursor.getString(4)));
                player.setTeamId(Integer.parseInt(cursor.getString(5)));
            }
        }

        return player;
    }

    public List<Player> getAllPlayers() {
        List<Player> playerList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER + "";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setName(cursor.getString(1));
                player.setInjury(Integer.parseInt(cursor.getString(2)));
                player.setMorale(Integer.parseInt(cursor.getString(3)));
                player.setPower(Float.parseFloat(cursor.getString(4)));
                player.setTeamId(Integer.parseInt(cursor.getString(5)));

                playerList.add(player);
            } while (cursor.moveToNext());
        }

        return playerList;
    }

    public int updatePlayer(Player Player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_ID, Player.getId());
        values.put(KEY_PLAYER_NAME, Player.getName());
        values.put(KEY_PLAYER_INJURY, Player.getInjury());
        values.put(KEY_PLAYER_MORALE, Player.getMorale());
        values.put(KEY_PLAYER_POWER, Player.getPower());
        values.put(KEY_PLAYER_TEAMID, Player.getTeamId());

        return db.update(TABLE_PLAYER, values, KEY_PLAYER_ID + " = ?",
                new String[] { String.valueOf(Player.getId()) });
    }

    public void deletePlayer(Player Player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER, KEY_PLAYER_ID + " = ?",
                new String[] { String.valueOf(Player.getId()) });
        db.close();
    }

    public int getPlayersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLAYER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
