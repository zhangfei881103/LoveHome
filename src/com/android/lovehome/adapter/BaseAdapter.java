package com.android.lovehome.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author yongbing.chen
 * @updateauthor feng.xiao
 * @description 基类适配器(泛型)
 * @createtime  2015-1-20
 * @updatetime  2015-7-13
 * @param
 */
public abstract class BaseAdapter<E> extends android.widget.BaseAdapter {

	protected List<E> mDatas;
	protected Context mContext;

	public BaseAdapter(Context context, List<E> datas) {
		this.mContext = context;
		this.mDatas = datas;
	}

	public void setData(List<E> datas){
		this.mDatas = datas;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if (mDatas == null)
			return 0;
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		if (mDatas == null)
			return null;
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup group) {
		return getView(position, convertView, mDatas.get(position));
	}

	protected abstract View getView(int position, View convertView, E data);
}
