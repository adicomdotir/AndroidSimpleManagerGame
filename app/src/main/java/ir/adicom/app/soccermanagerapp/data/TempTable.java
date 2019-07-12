package ir.adicom.app.soccermanagerapp.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adicom on 7/12/19.
 */

public class TempTable {
    public static Map<Long, Integer> myMap = new HashMap<>();

    public static String getTeamRank(Long id) {
        return "" + (myMap.get(id) == null ? "بدون رتبه" : myMap.get(id));
    }
}
