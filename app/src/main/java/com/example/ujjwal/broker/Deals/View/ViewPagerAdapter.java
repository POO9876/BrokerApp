package com.example.ujjwal.broker.Deals.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ujjwal on 19/12/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList =new ArrayList<>();
	private List<String> fragmentTitleList =new ArrayList<>();

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		return fragmentList.get(position);
	}

	@Override
	public int getCount() {

		return fragmentList.size();
	}
	public void setTabData(List<Fragment> fragmentList,List<String > fragmentTitleList){
		this.fragmentList=fragmentList;
		this.fragmentTitleList=fragmentTitleList;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return fragmentTitleList.get(position);
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}
