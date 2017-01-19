package studydemo.www.doloop.com.studtydemo.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zhaonan on 17/1/19.
 */

public class Country extends RealmObject {

    @PrimaryKey
    private String code;
    private String name;
    private int population;

    public Country() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
