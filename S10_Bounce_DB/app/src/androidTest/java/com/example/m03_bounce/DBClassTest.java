package com.example.m03_bounce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.List;

// Use this so the methods take place in alphabetical order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBClassTest {
    DBClass db;

    // Make a db with a few balls.
    @Before
    public void setUp() throws Exception {
        Log.w("AndroidTest", "setUp()");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new DBClass(appContext);

        int doCount = db.count();
        if (!(doCount > 0)) {
            Log.v("DBClass", "dd some rows");
            DataModel a = new DataModel("Test1", 20.0f, 20.0f, -4.0f, -4.0f, 111111);
            db.save(a);
            a = new DataModel("Test2", 30f, 30f, 3f, 3f, 222222);
            db.save(a);

            List<DataModel> ALL = db.findAll();
            for (DataModel one : ALL) {
                Log.w("AndroidTest", "Setup()=> Item => " + one.toString());
            }
        }
        Log.v("DBClass", "already rows in DB");
    }

    // Find all balls, we know there should be 2, so assert that
    @Test
    public void test01_findAll() {
        List<DataModel> ALL = db.findAll();
        for (DataModel one : ALL) {
            Log.w("AndroidTest", "testFind()=> Item => " + one.toString());
        }
        assert (ALL.size() == 2);
    }

    // Test the ability to count (very hard)
    @Test
    public void test02_count() {
        int num = db.count();
        assert (num == 2);
    }

    // Save a new ball, we know there should be 3, so assert that
    @Test
    public void test03_save() {
        DataModel a = new DataModel("Test3", 21.0f, 21.0f, -5.0f, 5.0f, 333333);
        db.save(a);
        List<DataModel> ALL = db.findAll();
        for (DataModel one : ALL) {
            Log.w("AndroidTest", "testSave()=> Item => " + one.toString());
        }
        Log.w("AndroidTest", "testSave()=> size=" + ALL.size());
        assert (ALL.size() == 3);
    }


    // Find by tests, two normal, one invalid
    @Test
    public void test04_findNameById_01() {
        String name = db.getNameById(1L);
        Log.w("AndroidTest", "testFind()=> Item => " + name);
        assert (name.equals("Test1"));
    }

    @Test
    public void test04_findNameById_02() {
        String name = db.getNameById(2L);
        Log.w("AndroidTest", "testFind()=> Item => " + name);
        assert (name.equals("Test2"));
    }

    @Test
    public void test04_findNameById_03() {
        String name = db.getNameById(13L);
        Log.w("AndroidTest", "testFind()=> Item => " + name);
        assert (name == null);
    }

    @Test
    public void test05_findByName_01() {
        DataModel a = db.findByName("Test1");
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelName().equals("Test1"));
    }

    @Test
    public void test05_findByName_02() {
        DataModel a = db.findByName("Test2");
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelName().equals("Test2"));
    }

    @Test
    public void test05_findByName_03() {
        DataModel a = db.findByName("Test1421");
        //Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a == null);
    }

    @Test
    public void test06_findByX_01() {
        DataModel a = db.findByX(20.0f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelX() == 20.0f);
    }

    @Test
    public void test06_findByX_02() {
        DataModel a = db.findByX(30f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelX() == 30f);
    }

    @Test
    public void test06_findByX_03() {
        DataModel a = db.findByX(300f);
        //Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a == null);
    }

    @Test
    public void test06_findByY_01() {
        DataModel a = db.findByY(20.0f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelY() == 20.0f);
    }

    @Test
    public void test06_findByY_02() {
        DataModel a = db.findByY(30f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelY() == 30f);
    }

    @Test
    public void test06_findByY_03() {
        DataModel a = db.findByY(300f);
        //Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a == null);
    }

    @Test
    public void test06_findByDX_01() {
        DataModel a = db.findByDX(-4.0f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelDX() == -4.0f);
    }

    @Test
    public void test06_findByDX_02() {
        DataModel a = db.findByDX(3f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelDX() == 3f);
    }

    @Test
    public void test06_findByDX_03() {
        DataModel a = db.findByDX(300f);
        //Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a == null);
    }

    @Test
    public void test06_findByDY_01() {
        DataModel a = db.findByDY(-4.0f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelDY() == -4.0f);
    }

    @Test
    public void test06_findByDY_02() {
        DataModel a = db.findByDY(3f);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelDY() == 3f);
    }

    @Test
    public void test06_findByDY_03() {
        DataModel a = db.findByDY(-300f);
        //Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a == null);
    }

    @Test
    public void test06_findByColor_01() {
        DataModel a = db.findByColor(111111);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelColor() == 111111);
    }

    @Test
    public void test06_findByColor_02() {
        DataModel a = db.findByColor(222222);
        Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a.getModelColor() == 222222);
    }

    @Test
    public void test06_findByColor_03() {
        DataModel a = db.findByColor(4444);
        //Log.w("AndroidTest", "testFind()=> Item => " + a.toString());
        assert (a == null);
    }

    // The extra stuff other than wipeDb makes it so the ids start at the same number every time
    @After
    public void tearDown() throws Exception {
        db.wipeDatabase();
        SQLiteDatabase writableDatabase = db.getWritableDatabase();
        writableDatabase.execSQL("DELETE FROM sqlite_sequence WHERE name = '" + db.getTableName() + "'");
        writableDatabase.close();
    }

}
