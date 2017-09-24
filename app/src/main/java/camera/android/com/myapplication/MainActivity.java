package camera.android.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import camera.android.com.myapplication.Constants.Constants;
import camera.android.com.myapplication.Fragment.DetailsFragment;
import camera.android.com.myapplication.Fragment.ItemFragment;
import camera.android.com.myapplication.Objects.MyItem;


public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private boolean mTwoPain=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(findViewById(R.id.detailsFragmentContainer)!=null){
            mTwoPain=true;

            if(savedInstanceState!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.detailsFragmentContainer,new DetailsFragment()).commit();
            }
        }else{
            mTwoPain=false;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_action; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.logged_in:
                Intent intent=new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListFragmentInteraction(MyItem item) {
       if(!mTwoPain){
           /*start details activity*/
           Intent intent=new Intent(this,DetailActivity.class);
           intent.putExtra(Constants.ITEM,item);
           startActivity(intent);
       }else {
           /*start details fragment*/
           Fragment fragment = DetailsFragment.newInstance(item);
           getSupportFragmentManager().beginTransaction().replace(R.id.detailsFragmentContainer, fragment).commit();
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
