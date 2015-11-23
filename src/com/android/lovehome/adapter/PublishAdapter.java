package com.android.lovehome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.entity.GoodEntity;
import com.android.lovehome.entity.ImageEntity;

import java.util.List;

/**
 * Created by yongbing.chen on 2015/11/4.
 *
 * 发布适配器
 */
public class PublishAdapter extends BaseAdapter<ImageEntity>{

    public PublishAdapter(Context context, List<ImageEntity> datas) {
        super(context, datas);
    }

    @Override
    protected View getView(int position, View view, ImageEntity data) {

        final ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            view=LayoutInflater.from(mContext).inflate(R.layout.publish_item,null);
            viewHolder.type_image=(ImageView)view.findViewById(R.id.type_image);
            viewHolder.textView=(TextView)view.findViewById(R.id.type_name);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.type_image.setImageDrawable(mContext.getResources().getDrawable(data.getLocation_imageUrl()));
        viewHolder.textView.setText(data.getTitle());

        return view;
    }


    class ViewHolder {
        //private LinearLayout time_layout;
        private TextView textView;
        private ImageView type_image;
//        private TextView name;
//        private TextView weight;
//        private TextView weight_text;
//        private TextView num;

    }
}
