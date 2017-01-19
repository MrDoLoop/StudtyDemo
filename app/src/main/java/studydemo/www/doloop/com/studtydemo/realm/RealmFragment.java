package studydemo.www.doloop.com.studtydemo.realm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import studydemo.www.doloop.com.studtydemo.R;
import studydemo.www.doloop.com.studtydemo.base.BaseFragmnet;
import studydemo.www.doloop.com.studtydemo.utils.L;

/**
 * Created by zhaonan on 17/1/11.
 */

public class RealmFragment extends BaseFragmnet {

    private TextView mTv;
    private Realm mRealm;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .encryptionKey(new byte[64])
                .schemaVersion(2)
                .build();
        // Use the config
        mRealm = Realm.getInstance(config);

        mTv = $(R.id.text_view);
        mTv.setText("模板Fragment");
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                queryData();
            }
        });
    }

    private void insertData() {
        // Create an object
//        Country countryRealm = mRealm.createObject(countryRealm);
//
//        // Set its fields
//        countryRealm.setName("Norway");
//        countryRealm.setPopulation(5165800);
//        countryRealm.setCode("NO");
//        mRealm.commitTransaction();

        Country country = mRealm.where(Country.class).equalTo("name","Russia").findFirst();
        if(country == null) {
            Country country2 = new Country();
            country2.setName("Russia");
            country2.setPopulation(146430430);
            country2.setCode("RU");

            mRealm.beginTransaction();
            mRealm.copyToRealm(country2);
            mRealm.commitTransaction();
        }
    }


    private void queryData() {
        RealmResults<Country> results1 = mRealm.where(Country.class).findAll();

        for (Country c : results1) {
            L.i("realm data "+c.getName());
        }
    }

}
