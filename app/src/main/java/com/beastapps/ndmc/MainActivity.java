package com.beastapps.ndmc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;


public class MainActivity extends AppCompatActivity {


    BottomNavigationView navigation;
    MenuItem selectedItem;
    ActionBar actionBar;
    BottomSheetLayout bottomSheet;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(bottomSheet.isSheetShowing())
                bottomSheet.dismissSheet();
            if(item == selectedItem)
                return true;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,new HomePage()).commit();
                    //Snackbar.make(findViewById(R.id.content),R.string.title_home,Snackbar.LENGTH_SHORT).show();
                    setTheme(android.R.style.Theme_Black);
                    selectedItem = navigation.getMenu().getItem(0);

                    updateToolbar("Home");
                    return true;
                case R.id.navigation_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,new Profile()).commit();
                    //Snackbar.make(findViewById(R.id.content),R.string.title_profile,Snackbar.LENGTH_SHORT).show();
                    selectedItem = navigation.getMenu().getItem(1);
                    updateToolbar("Profile");
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,new NotificationPAge()).commit();
                    selectedItem = navigation.getMenu().getItem(2);
                    updateToolbar("Notifications");
                    return true;
                case R.id.navigation_complaint:
                    if(!bottomSheet.isSheetShowing())
                    {
                        View view =  LayoutInflater.from(MainActivity.this).inflate(R.layout.complaint_layout,bottomSheet,false);
                        bottomSheet.showWithSheetView(view);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Toast.makeText(MainActivity.this,"Write A Complaint",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                                String address[] = {"bhawani.singh@ndmc.co.in"};
                                intent.putExtra(Intent.EXTRA_EMAIL,address);

                                if (intent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(intent);
                                }

                            }
                        });
                        bottomSheet.peekSheet();}
                    return true;
            }
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.content,new HomePage()).commit();
            selectedItem = navigation.getMenu().getItem(0);
            updateToolbar("Home");
        }
    }

    private void updateToolbar(String title) {
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        if(selectedItem==navigation.getMenu().getItem(0))
            super.onBackPressed();
        else
            getSupportFragmentManager().beginTransaction().add(R.id.content,new HomePage()).commit();
            selectedItem = navigation.getMenu().getItem(0);
            updateToolbar("Home");
    }
}
