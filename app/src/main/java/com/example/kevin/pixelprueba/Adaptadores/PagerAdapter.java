package com.example.kevin.pixelprueba.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kevin.pixelprueba.Fragmentos.TabProductos;
import com.example.kevin.pixelprueba.Fragmentos.TabServicios;

/**
 * Created by kevin on 22/01/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numTabs;

    public PagerAdapter(FragmentManager fm, int num){
        super(fm);
        numTabs = num;

    }
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                TabProductos tab1 = new TabProductos();
                return tab1;
            case 1:
                TabServicios tab2= new TabServicios();
                return tab2;
            default:
                return  null;

        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
