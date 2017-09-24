package camera.android.com.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import camera.android.com.myapplication.Constants.Constants;
import camera.android.com.myapplication.Fragment.DetailsFragment;
import camera.android.com.myapplication.Objects.MyItem;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getData();
    }

    private void getData() {
        MyItem item = (MyItem) getIntent().getExtras().getSerializable(Constants.ITEM);
        initViews(item);
    }

    private void initViews(MyItem item) {

        Fragment fragment = DetailsFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.detailsFragmentContainer, fragment).commit();

    }

}
