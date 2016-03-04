package com.example.rachel.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.util.ArrayList;


/**
 * Created by rachel on 3/2/16.
 */
public class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private ArrayList<SimpleRow> mPages;

    public SampleGridPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        initPages();
    }

    private void initPages() {
        mPages = new ArrayList<SimpleRow>();

        SimpleRow row1 = new SimpleRow();
        row1.addPages(new SimplePage("Barbara Lee", "Democrat", R.drawable.barbara_lee, R.drawable.democrat));
        row1.addPages(new SimplePage("Barbara Boxer", "Democrat", R.drawable.barbara_boxer, R.drawable.democrat));
        row1.addPages(new SimplePage("Dianne Feinstein", "Democrat", R.drawable.dianne_feinstein, R.drawable.democrat));


//        SimpleRow row2 = new SimpleRow();
//        row2.addPages(new SimplePage("Title3", "Text3", R.drawable.ic_ic_dh_bat, R.drawable.rose));
//
//        SimpleRow row3 = new SimpleRow();
//        row3.addPages(new SimplePage("Title4", "Text4", R.drawable.ic_error_loadingorangesmall, R.drawable.sea));
//
//        SimpleRow row4 = new SimpleRow();
//        row4.addPages(new SimplePage("Title5", "Text5", R.drawable.ic_launcher, R.drawable.water));
//        row4.addPages(new SimplePage("Title6", "Text6", R.drawable.ic_launcher, R.drawable.mountain));

        mPages.add(row1);
//        mPages.add(row2);
//        mPages.add(row3);
//        mPages.add(row4);
    }

    @Override
    public Fragment getFragment(int row, int col) {
        SimplePage page = ((SimpleRow)mPages.get(row)).getPages(col);
        CustomFragment person = new CustomFragment();
        CardFragment fragment = CardFragment.create(page.repName, page.repParty, page.bg);
        return fragment;
    }

//    @Override
//    public ImageReference getBackground(int row, int col) {
//        SimplePage page = ((SimpleRow)mPages.get(row)).getPages(col);
//        return ImageReference.forDrawable(page.mBackgroundId);
//    }

    @Override
    public int getRowCount() {
        return mPages.size();
    }

    @Override
    public int getColumnCount(int row) {
        return mPages.get(row).size();
    }
}
