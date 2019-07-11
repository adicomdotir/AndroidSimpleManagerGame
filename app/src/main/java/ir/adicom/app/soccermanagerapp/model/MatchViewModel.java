package ir.adicom.app.soccermanagerapp.model;

import java.util.List;

/**
 * Created by adicom on 7/11/19.
 */

public class MatchViewModel {
    private List<Match> matches;
    private int weekId;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
}
