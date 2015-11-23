package com.android.lovehome.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.entity.GoodEntity;

/**
 * Created by yongbing.chen on 2015/11/4.
 *
 * 美食适配器
 */
public class GoodAdapter extends BaseAdapter<GoodEntity>{

    public GoodAdapter(Context context, List<GoodEntity> datas) {
        super(context, datas);
    }

    @Override
    protected View getView(int position, View view, GoodEntity data) {

        final ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            view=LayoutInflater.from(mContext).inflate(R.layout.good_item,null);
            viewHolder.textView=(TextView)view.findViewById(R.id.textview);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(data.getShopName());

        return view;
    }


    class ViewHolder {
        //private LinearLayout time_layout;
        private TextView textView;
//        private ImageView item_image;
//        private TextView name;
//        private TextView weight;
//        private TextView weight_text;
//        private TextView num;

    }
}
