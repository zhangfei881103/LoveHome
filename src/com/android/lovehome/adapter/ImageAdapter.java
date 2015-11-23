
package com.android.lovehome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.lovehome.R;
/*
 * 数据装载类
 *
 * 图片轮番适配器
 */
public class ImageAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
    //图片过大     可能显示不出来  如 R.drawable.test1
	private static final int[] ids = {R.drawable.test3, R.drawable.test2, R.drawable.test3,R.drawable.test2,R.drawable.test3 };

	public ImageAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;   //返回很大的值使得getView中的position不断增大来实现循环。
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_item, null);
		}
		((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(ids[position%ids.length]);
		return convertView;
	}

}
