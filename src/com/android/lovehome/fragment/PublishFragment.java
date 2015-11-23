package com.android.lovehome.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.lovehome.R;
import com.android.lovehome.activity.PublishActivity;
import com.android.lovehome.adapter.PublishAdapter;
import com.android.lovehome.base.BaseFragment;
import com.android.lovehome.entity.ImageEntity;
import com.android.lovehome.utils.uiutils.DialogUtil;
import com.android.lovehome.utils.uiutils.DialogUtil.DialogListClickCallBack;
import com.android.lovehome.utils.uiutils.ToastUtils;

/**
 * PublishFragment
 *
 * @description 发布
 * @createtime 2015-11-5
 * @updatetime 2015-11-5
 */
public class PublishFragment extends BaseFragment implements DialogListClickCallBack{


    private GridView gridView;

    private PublishAdapter publishAdapter;
    private List<ImageEntity> imageEntityList;

    private List<String> meishiList= new ArrayList<String>(){{
        add("酒店");add("饭店"); add("西点"); add("夜宵");
        add("外卖");add("茶馆"); add("零食特产"); add("其它");}};
    private List<String> yuleList=new ArrayList<String>(){{
            add("KTV");add("电影");add("酒吧");
            add("宾馆");add("足疗按摩");add("娱乐其它");}};
    private List<String> fanchanList=new ArrayList<String>(){{
        add("买卖");add("租赁");add("房产其它");}};
    private List<String> carList=new ArrayList<String>(){{
        add("买卖");add("租赁");add("代驾");
        add("学车");add("修理");add("车其它");}};
    private List<String> zhuangxiuList=new ArrayList<String>(){{
        add("家/公装");add("建材家居");add("装修工人");add("装修其它");}};
    private List<String> hunqianList=new ArrayList<String>(){{
        add("礼仪庆典");add("婚车");add("摄影");
        add("鲜花");add("婚庆其他");}};
    private List<String> jioayuList=new ArrayList<String>(){{
        add("学校");add("培训");add("家教");add("教育其它");}};
    private List<String> workList=new ArrayList<String>(){{
        add("全职");add("兼职");add("工作其它");}};


    @Override
    protected int getLayoutId() {
        return R.layout.publish;
    }

    @Override
    protected void initTitleBar() {
        head_title.setText("选择发布类型");
        rigth_image_button.setVisibility(View.GONE);
        left_image_button.setVisibility(View.GONE);
        rigth_image_button2.setVisibility(View.GONE);
    }

    @Override
    protected void initView() {

        initList();
        gridView=(GridView)view.findViewById(R.id.publish_gridview);

        publishAdapter=new PublishAdapter(getActivity(),imageEntityList);
        gridView.setAdapter(publishAdapter);
        gridView.setOnItemClickListener(new MyOnItemClicklistener());
    }

    @Override
    protected void initEvent() {

    }

    public void initList(){
        imageEntityList=new ArrayList<ImageEntity>();

        int[] imageUrl=new int[]{R.drawable.meishi,R.drawable.yule,R.drawable.fangchan,
                R.drawable.che,R.drawable.hunqing,R.drawable.zhuangxiu,R.drawable.jiaoyu,
                R.drawable.gongzuo,R.drawable.baihuo,R.drawable.tiaozhao,R.drawable.shangwu,
                R.drawable.bianmin,R.drawable.laoxianghui,R.drawable.qita,};
        String[] types=new String[]{"美食","娱乐","房产","车","婚庆","装修","教育","工作",
                "百货","跳蚤","商务","便民","老乡会","其他",};

        for (int i=0; i<types.length; i++){
            ImageEntity imageEntity=new ImageEntity();
            imageEntity.setTitle(types[i]);
            imageEntity.setLocation_imageUrl(imageUrl[i]);
            imageEntityList.add(imageEntity);
        }
    }

    public class MyOnItemClicklistener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          /*  Intent intent=new Intent(getActivity(),GoodDetailActivity.class);
            startActivity(intent);*/
            switch (i){
                case 0:
                    DialogUtil.getInstance(getActivity()).showDialogList(meishiList,PublishFragment.this);
                    break;
                case 1:
                    DialogUtil.getInstance(getActivity()).showDialogList(yuleList,PublishFragment.this);
                    break;
                case 2:
                    DialogUtil.getInstance(getActivity()).showDialogList(fanchanList,PublishFragment.this);
                    break;
                case 3:
                    DialogUtil.getInstance(getActivity()).showDialogList(carList,PublishFragment.this);
                    break;
                case 4:
                    DialogUtil.getInstance(getActivity()).showDialogList(hunqianList,PublishFragment.this);
                    break;
                case 5:
                    DialogUtil.getInstance(getActivity()).showDialogList(zhuangxiuList,PublishFragment.this);
                    break;
                case 6:
                    DialogUtil.getInstance(getActivity()).showDialogList(jioayuList,PublishFragment.this);
                    break;
                case 7:
                    DialogUtil.getInstance(getActivity()).showDialogList(workList,PublishFragment.this);
                    break;

            }
        }
    }


    @Override
    public void onItemClicklistener() {
        Intent intent=new Intent(getActivity(), PublishActivity.class);
        startIntent(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            
        }
    }


    @Override
    protected void init() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void startRequest(int taskId) {
        showDialog("加载中...");
    }


    @Override
    public void succeed(int taskId, JSONObject jObject) {
       
    }

    @Override
    public void failed(int taskId, JSONObject jObject) {
    }

    @Override
    public void exceptioned(int taskId, String msg) {
        dimissDialog();
    }


}
