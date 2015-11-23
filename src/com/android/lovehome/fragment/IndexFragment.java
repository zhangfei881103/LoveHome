package com.android.lovehome.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.activity.GoodActivity;
import com.android.lovehome.activity.GoodDetailActivity;
import com.android.lovehome.adapter.ImageAdapter;
import com.android.lovehome.adapter.GoodAdapter;
import com.android.lovehome.adapter.IndexGridviewAdapter;
import com.android.lovehome.base.BaseFragment;
import com.android.lovehome.entity.GoodEntity;
import com.android.lovehome.entity.ImageEntity;
import com.android.lovehome.utils.uiutils.PageControlView;
import com.android.lovehome.utils.uiutils.ScrollLayout;

import org.json.JSONObject;
import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * IndexFragment
 *
 * @description 主fragment
 * @createtime 2015-11-5
 * @updatetime 2015-11-5
 */
public class IndexFragment extends Fragment implements View.OnClickListener{

    private TextView tv_telephone;

    private ListView listView;
    private GoodAdapter goodAdapter;
    private List<GoodEntity> goodEntityList;

    private ScrollLayout mScrollLayout;
    private PageControlView pageControl;
    private DataLoading dataLoad;

    private IndexGridviewAdapter indexGridviewAdapter;
    private List<ImageEntity> imageEntityList;

    private ViewFlow viewFlow;


    private View view;
    /**
     * 初始化
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        IntentFilter filter = new IntentFilter();
        view = inflater.inflate(getLayoutId(), null);
        initTitleBar();
        initView();
        init();
        initEvent();
        return view;
    }

    protected int getLayoutId() {
        return R.layout.index;
    }


    protected void initView() {

        initImageList();
        dataLoad = new DataLoading();
        mScrollLayout = (ScrollLayout) view.findViewById(R.id.ScrollLayoutTest);
        pageControl = (PageControlView) view.findViewById(R.id.pageControl);

        listView = (ListView) view.findViewById(R.id.index_listview);

        initList();

        goodAdapter = new GoodAdapter(getActivity(), goodEntityList);
        listView.setAdapter(goodAdapter);


        /////图片轮番
        viewFlow = (ViewFlow) view.findViewById(R.id.viewflow);
        viewFlow.setAdapter(new ImageAdapter(getActivity()));
        viewFlow.setmSideBuffer(5); // 实际图片张数， 我的ImageAdapter实际图片张数为3       决定着下面的白点有多少个

        CircleFlowIndicator indic = (CircleFlowIndicator) view.findViewById(R.id.viewflowindic);
        viewFlow.setFlowIndicator(indic);

        viewFlow.setSelection(3 * 1000); // 设置初始位置
        viewFlow.startAutoFlowTimer(); // 启动自动播放
    }

    protected void initEvent() {

        listView.setOnItemClickListener(new MyOnItemClicklistener());
    }


    protected void initTitleBar() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), GoodActivity.class);
        startActivity(intent);
    }


    protected void init() {
        for (int i = 0; i < 2; i++) {
            //生成Gridview控件
            GridView appPage = new GridView(getActivity());
            // get the "i" page data
            indexGridviewAdapter=new IndexGridviewAdapter(getActivity(),imageEntityList,i);
            appPage.setAdapter(indexGridviewAdapter);
            appPage.setNumColumns(4);
            appPage.setVerticalSpacing(10);
            appPage.setHorizontalSpacing(10);
            appPage.setOnItemClickListener(new MyOnItemClicklistener());
            //这里确定页数
            mScrollLayout.addView(appPage);
        }
        pageControl.bindScrollViewGroup(mScrollLayout);
        //加载分页数据
        //dataLoad.bindScrollViewGroup(mScrollLayout);

    }

    public void initList() {
        goodEntityList = new ArrayList<GoodEntity>();

        for (int i = 0; i < 10; i++) {
            GoodEntity goodEntity = new GoodEntity();
            goodEntity.setShopName("龙湾大酒店");
            goodEntityList.add(goodEntity);
        }
    }

    public void initImageList() {
        imageEntityList = new ArrayList<ImageEntity>();
        int[] imageUrl=new int[]{R.drawable.meishi,R.drawable.yule,R.drawable.fangchan,
                R.drawable.che,R.drawable.hunqing,R.drawable.zhuangxiu,R.drawable.jiaoyu,
                R.drawable.gongzuo,R.drawable.baihuo,R.drawable.tiaozhao,R.drawable.shangwu,
                R.drawable.bianmin,R.drawable.laoxianghui,R.drawable.qita,};
        String[] types=new String[]{"美食","娱乐","房产","车","婚庆","装修","教育","工作",
                "百货","跳蚤","商务","便民","老乡会","其他",};

        for (int i = 0; i < imageUrl.length; i++) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setTitle(types[i]);
            imageEntity.setLocation_imageUrl(imageUrl[i]);
            imageEntityList.add(imageEntity);
        }
    }


    public class MyOnItemClicklistener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), GoodActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public class DataLoading {
        //分页数据
        private int count;

        public void bindScrollViewGroup(ScrollLayout scrollViewGroup) {
            this.count = scrollViewGroup.getChildCount();


            //在分页的时候调用这个方法
            scrollViewGroup.setOnScreenChangeListener(new ScrollLayout.OnScreenChangeListener() {

                @Override
                public void onScreenChange(int currentIndex) {
                    //变动页数
                    //TextView pagecount = (TextView) findViewById(R.id.pagecount);
                   // pagecount.setText(String.valueOf(currentIndex + 1));
                }
            });

            //只有在滑动的时候才会调用这个方法
//            scrollViewGroup.setOnScreenChangeListenerDataLoad(new OnScreenChangeListenerDataLoad() {
//                public void onScreenChange(int currentIndex) {
//                    // TODO Auto-generated method stub
//                    generatePageControl(currentIndex);
//
//                }
//            });
        }

    }
}