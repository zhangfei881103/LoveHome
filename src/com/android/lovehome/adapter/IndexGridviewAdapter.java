package com.android.lovehome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.entity.ImageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongbing.chen on 2015/11/6.
 */
public class IndexGridviewAdapter extends BaseAdapter<ImageEntity>{

    private final int PAGE_SIZE=8;
    private List<ImageEntity> mData;

    public IndexGridviewAdapter(Context context, List<ImageEntity> datas, int page) {
        super(context, datas);
        mData=new ArrayList<ImageEntity>();
        int begin=page*PAGE_SIZE;
        int end=begin+PAGE_SIZE;
        while ((begin<datas.size()) && (begin<end)) {
            mData.add(datas.get(begin));
            begin++;
        }
        mDatas=mData;
    }

    @Override
    protected View getView(int position, View view, ImageEntity data) {
        final ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.index_gridview_layout,null);
            viewHolder.textView=(TextView)view.findViewById(R.id.titile);
            viewHolder.index_gradviewImage=(ImageView)view.findViewById(R.id.index_gradviewImage);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.index_gradviewImage.setImageDrawable(mContext.getResources().getDrawable(data.getLocation_imageUrl()));
        viewHolder.textView.setText(data.getTitle());

        return view;
    }

    class ViewHolder {
        private TextView textView;
        private ImageView index_gradviewImage;

    }
}
