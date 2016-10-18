package com.example.bj.superdemo.ui.customview.gallery_picture;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends BaseActivity {
    private Button btn_waterfall;
    private Button btn_gallery;
    private Fragment galleryFragment, waterFallFragment;
    private List<String> fragmentNames;

    @Override
    public void initData() {
        setContentView(R.layout.activity_main4);
        fragmentNames = new ArrayList<>();
        fragmentNames.add(GalleryFragment.class.getSimpleName());
        fragmentNames.add(WaterFallFragment.class.getSimpleName());
        findViewById(R.id.btn_waterfall).setOnClickListener(this);
        findViewById(R.id.btn_gallery).setOnClickListener(this);
    }

    private void showFragment(String fname, String tag) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fname);
        }

        for (String name : fragmentNames) {
            Fragment toHide = fragmentManager.findFragmentByTag(
                    name);
            if (toHide != null && toHide.isVisible()) {
                ft.hide(toHide);
            }
        }
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            synchronized (this) {

                ft.add(R.id.ll_main4_container, fragment, tag);
            }
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_waterfall:
                showFragment(WaterFallFragment.class.getName(), WaterFallFragment.class.getSimpleName());
                break;
            case R.id.btn_gallery:
                showFragment(GalleryFragment.class.getName(), GalleryFragment.class.getSimpleName());
                break;

        }
    }
}
