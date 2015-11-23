package com.android.lovehome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.entity.ImageEntity;

import java.util.List;

/**
 * Created by yongbing.chen on 2015/11/4.
 *
 * 发布弹出框适配器
 */
public class PublishDialogAdapter extends BaseAdapter<String>{

    public PublishDialogAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected View getView(int position, View view, String data) {

        final ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            view=LayoutInflater.from(mContext).inflate(R.layout.publish_dialog_item,null);
            viewHolder.textView=(TextView)view.findViewById(R.id.type_dialog_name);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(data);

        return view;
    }


    class ViewHolder {
        private TextView textView;
    }
}
