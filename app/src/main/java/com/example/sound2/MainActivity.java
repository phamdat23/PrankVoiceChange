package com.example.sound2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sound2.framents.favorite;
import com.example.sound2.framents.home;
import com.example.sound2.framents.sound;
import com.example.sound2.framents.voice;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private View statusBar;
    private FrameLayout frameContent;
    public BottomNavigationView bottomMenu;
    FragmentManager fragmentManager;
    String nameFrament;
    String backHome;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screne);
        intent = getIntent();

        statusBar = (View) findViewById(R.id.status_bar);
        frameContent = (FrameLayout) findViewById(R.id.frame_content);
        bottomMenu = (BottomNavigationView) findViewById(R.id.bottom_menu);

       nameFrament = intent.getStringExtra("nameFrament");
       backHome = intent.getStringExtra("detailBackHome");
       if(nameFrament!=null){
           if(nameFrament.equalsIgnoreCase("voice")){
               Log.e("TAG", "onCreate: SetFrament Voice");
               SetFrament(new voice(this));
               bottomMenu.getMenu().findItem(R.id.voice).setChecked(true);
           }
//           else{
//               SetFrament(new home(this));
//               Log.e("TAG", "onCreate: SetFrament home");
//           }
       }
        if(backHome!=null) {
            if (backHome.equalsIgnoreCase("sound")) {
                SetFrament(new voice(MainActivity.this));
                bottomMenu.getMenu().findItem(R.id.sound).setChecked(true);
            }
//            else {
//                SetFrament(new home(MainActivity.this));
//            }
        }
        if(nameFrament==null&&backHome==null){
            SetFrament(new home(MainActivity.this));
        }

        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        SetFrament(new home(MainActivity.this));
                        break;
                    case R.id.sound:
                        SetFrament(new sound(MainActivity.this));
                        break;
                    case R.id.voice:
                        SetFrament(new voice(MainActivity.this));
                        break;
                    case R.id.favorite:
                        SetFrament(new favorite(MainActivity.this));
                        break;
                    default:
                        if(nameFrament!=null){
                            if(nameFrament=="voice"){
                                SetFrament(new voice(MainActivity.this));
                                bottomMenu.getMenu().findItem(R.id.voice).setChecked(true);
                                Log.e("TAG", "onCreate: SetFrament Voice");
                            }
//                            else{
//                                SetFrament(new home(MainActivity.this));
//                                Log.e("TAG", "onCreate: SetFrament home");
//                            }
                        }
                        if(backHome!=null) {
                            if (backHome.equalsIgnoreCase("sound")) {
                                SetFrament(new voice(MainActivity.this));
                                bottomMenu.getMenu().findItem(R.id.sound).setChecked(true);
                            }
//                            else {
//                                SetFrament(new home(MainActivity.this));
//                            }
                        }
                        if(nameFrament==null&&backHome==null){
                            SetFrament(new home(MainActivity.this));
                        }

                        break;


                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        nameFrament = intent.getStringExtra("nameFrament");
        backHome = intent.getStringExtra("detailBackHome");
        if(nameFrament!=null){
            if(nameFrament.equalsIgnoreCase("voice")){
                Log.e("TAG", "onCreate: SetFrament Voice");
                SetFrament(new voice(this));
                bottomMenu.getMenu().findItem(R.id.voice).setChecked(true);
            }
//            else{
//                SetFrament(new home(this));
//                Log.e("TAG", "onCreate: SetFrament home");
//            }
        }
        if(backHome!=null) {
            if (backHome.equalsIgnoreCase("sound")) {
                SetFrament(new voice(this));
                bottomMenu.getMenu().findItem(R.id.sound).setChecked(true);
            }
            else {
                SetFrament(new home(this));
            }
        }
//        if(nameFrament==null&&backHome==null){
//            SetFrament(new home(this));
//        }
    }

    public void SetFrament(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent Main = new Intent(Intent.ACTION_MAIN);
        Main.addCategory(Intent.CATEGORY_HOME);
        startActivity(Main);
        finish();
    }
}