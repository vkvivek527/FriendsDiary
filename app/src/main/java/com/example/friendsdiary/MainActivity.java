package com.example.friendsdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TabAccessorAdapter myTabAccessorAdapter;
  private FirebaseUser currentUser;
  private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        Toolbar mToolbar = findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar();
        ViewPager myViewPager = findViewById(R.id.main_tabs_pager);
        myTabAccessorAdapter=new TabAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabAccessorAdapter);
        TabLayout myTabLayout = findViewById(R.id.main_tabs);

        myTabLayout.setupWithViewPager(myViewPager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser==null)
        {
            sendUserToLoginActivity();
        }
    }

    private void sendUserToLoginActivity() {
        Intent loginIntent =new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_option,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.logout_option)
        {
            mAuth.signOut();
            sendUserToLoginActivity();
        }
        if (item.getItemId()==R.id.find_friends_option)
        {

        }
        if (item.getItemId()==R.id.setting_option)
        {

        }
        return true;
    }

}
