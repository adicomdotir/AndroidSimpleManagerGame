package ir.adicom.app.soccermanagerapp;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import ir.adicom.app.soccermanagerapp.model.DaoMaster;
import ir.adicom.app.soccermanagerapp.model.DaoSession;

public class App extends Application {

    public static final String TAG = "TAG";
    public static int weekIndex = 1;
    public static int day = 1;
    public static int size = 8;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/IRANSansMobile(FaNum).ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "game-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
