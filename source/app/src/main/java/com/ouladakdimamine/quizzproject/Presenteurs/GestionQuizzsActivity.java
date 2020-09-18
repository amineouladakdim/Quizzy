package com.ouladakdimamine.quizzproject.Presenteurs;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import com.ouladakdimamine.quizzproject.Config;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.AddCategorieActivity;
import com.ouladakdimamine.quizzproject.R;
import com.ouladakdimamine.quizzproject.Presenteurs.Tabs.CategorieTab;
import com.ouladakdimamine.quizzproject.Presenteurs.Tabs.PropositionTab;
import com.ouladakdimamine.quizzproject.Presenteurs.Tabs.QuestionTab;
import com.ouladakdimamine.quizzproject.Presenteurs.Tabs.QuizzTab;

public class GestionQuizzsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static int currentTab = 0;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_quizzs);

        //sugar lib


        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);

        tabLayout = (TabLayout) findViewById(R.id.tabs);


        //  Log.i("tab index start :","tab index"+Config.tab_index);
        //tabLayout.getTabAt(Config.tab_index).select();


        tabLayout.setScrollPosition(Config.tab_index, 0f, true);
        mViewPager.setCurrentItem(Config.tab_index);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = tab.getPosition();

                Config.tab_index = currentTab;


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(GestionQuizzsActivity.this, HomeActivity.class);
                GestionQuizzsActivity.this.startActivity(myIntent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    CategorieTab tab1 = new CategorieTab();
                    return tab1;
                case 1:
                    QuizzTab tab2 = new QuizzTab();
                    return tab2;
                case 2:
                    QuestionTab tab3 = new QuestionTab();
                    return tab3;
                case 3:
                    PropositionTab tab4 = new PropositionTab();
                    return tab4;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
