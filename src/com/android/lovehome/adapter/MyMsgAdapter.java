package com.android.lovehome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.entity.GoodEntity;
import com.android.lovehome.entity.MyMsgEntity;

import java.util.List;

/**
 * Created by yongbing.chen on 2015/11/4.
 */
public class MyMsgAdapter extends BaseAdapter<MyMsgEntity> {

    public MyMsgAdapter(Context context, List<MyMsgEntity> datas) {
        super(context, datas);
    }

    @Override
    protected View getView(int position, View view, MyMsgEntity data) {

        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_mymsg_item, null);

            viewHolder.imgPic = (ImageView) view.findViewById(R.id.img_mymsg_pic);
            viewHolder.txtTitle = (TextView) view.findViewById(R.id.txt_mymsg_title);
            viewHolder.txtTime = (TextView) view.findViewById(R.id.txt_mymsg_time);
            viewHolder.txtContent = (TextView) view.findViewById(R.id.txt_mymsg_content);
            viewHolder.txtNum = (TextView) view.findViewById(R.id.txt_mymsg_num);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txtNum.setText(data.getNum());
        return view;
    }


    class ViewHolder {
       ImageView imgPic ;
        TextView txtTitle ;
        TextView  txtTime ;
        TextView txtContent ;
        TextView txtNum;
    }
}
