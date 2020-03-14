package com.kitsudev.indohistory.indonesiahistory;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.laporan.laporan;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getFragmentManager();
        // tampilan default awal ketika aplikasii dijalankan
        if (savedInstanceState == null) {
            fragment = new pengenalan();
            callFragment(fragment);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // Untuk memanggil layout dari menu yang dipilih
        if (id == R.id.nav_pengenalan) {
            fragment = new pengenalan();
            callFragment(fragment);
        } else if (id == R.id.nav_materi) {
            fragment = new kelola_materi();
            callFragment(fragment);
        } else if (id == R.id.nav_kuis) {
            fragment = new kelola_kuis();
            callFragment(fragment);
        }  else if (id == R.id.nav_kat) {
            fragment = new kelola_kategori();
            callFragment(fragment);
        } else if (id == R.id.nav_tentangkami) {
            fragment = new tentangkami();
            callFragment(fragment);
        } else if (id == R.id.nav_kuisgambar) {
            fragment = new laporan();
            callFragment(fragment);
        } else if (id == R.id.nav_logout) {
            signOut();
            Toast.makeText(MainActivity.this, "Berhasil Logout...", Toast.LENGTH_SHORT).show();
            finish();
        }
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // untuk mengganti isi kontainer menu yang dipiih
    private void callFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

    private void signOut(){
    }

}