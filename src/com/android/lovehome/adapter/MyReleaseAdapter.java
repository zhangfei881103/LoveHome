package com.android.lovehome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.activity.MyReleaseDetailActivity;
import com.android.lovehome.entity.MyReleaseEntity;
import com.android.lovehome.utils.uiutils.DialogUtil;

import java.util.List;

/**
 * Created by yongbing.chen on 2015/11/4.
 */
public class MyReleaseAdapter extends BaseAdapter<MyReleaseEntity> {
    private String tag;

    public MyReleaseAdapter(Context context, List<MyReleaseEntity> datas) {
        super(context, datas);

    }

    public MyReleaseAdapter(Context context, List<MyReleaseEntity> datas , String tag) {
        super(context, datas);
        this.tag = tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    protected View getView(int position, View view, MyReleaseEntity data) {

        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_release_item, null);

            viewHolder.imgUrl = (ImageView)view.findViewById(R.id.img_release_item_url);
            viewHolder.txtName = (TextView) view.findViewById(R.id.txt_release_item_name);
            viewHolder.imgVip = (ImageView)view.findViewById(R.id.img_release_item_vip);
            viewHolder.imgWai = (ImageView)view.findViewById(R.id.img_release_item_wai);
            viewHolder.txtPrice = (TextView) view.findViewById(R.id.txt_release_item_price);
            viewHolder.txtAdress = (TextView) view.findViewById(R.id.txt_release_item_address);
            viewHolder.txtType = (TextView) view.findViewById(R.id.txt_release_item_type);
            viewHolder.txtTime = (TextView) view.findViewById(R.id.txt_release_item_time);
            viewHolder.layoutModify = (LinearLayout)view.findViewById(R.id.layout_release_item_modify);
            viewHolder.layoutShare = (LinearLayout)view.findViewById(R.id.layout_release_item_share);
            viewHolder.layoutDelete = (LinearLayout)view.findViewById(R.id.layout_release_item_delete);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if("release".equals(tag)){
            viewHolder.layoutModify.setVisibility(View.VISIBLE);
            viewHolder.layoutShare.setVisibility(View.VISIBLE);
        }else if("draft".equals(tag)){
            viewHolder.layoutModify.setVisibility(View.VISIBLE);
            viewHolder.layoutShare.setVisibility(View.GONE);
        }else if("collection".equals(tag)){
            viewHolder.layoutModify.setVisibility(View.GONE);
            viewHolder.layoutShare.setVisibility(View.VISIBLE);
        }

        viewHolder.layoutModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext , MyReleaseDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

        viewHolder.layoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.getInstance(mContext).IgnoreDialog(new DialogUtil.DialogButtonCallBack() {

                    @Override
                    public void onRigthButtonClick() {

                    }

                    @Override
                    public void onLeftButtonClick() {

                    }
                });
            }
        });

        viewHolder.txtPrice.setText(data.getPrice());

        return view;
    }


    class ViewHolder {
        ImageView imgUrl ;
        TextView txtName ;
        ImageView imgVip ;
        ImageView imgWai ;
        TextView txtPrice;
        TextView txtAdress ;
        TextView txtType ;
        TextView txtTime ;
        LinearLayout layoutModify ;
        LinearLayout layoutShare ;
        LinearLayout layoutDelete ;
    }
}
