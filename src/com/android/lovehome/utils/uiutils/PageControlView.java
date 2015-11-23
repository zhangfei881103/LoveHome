package com.android.lovehome.utils.uiutils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.lovehome.R;
import com.android.lovehome.utils.uiutils.ScrollLayout.OnScreenChangeListener;

public class PageControlView extends LinearLayout{  
    private Context context;  
  
    private int count;  
  
    public void bindScrollViewGroup(ScrollLayout scrollViewGroup) {  
        this.count=scrollViewGroup.getChildCount();  
       // System.out.println("chen count="+count);  
        generatePageControl(scrollViewGroup.getCurrentScreenIndex());  
          
        //在分页的时候调用这个方法
        scrollViewGroup.setOnScreenChangeListener(new OnScreenChangeListener() {
            @Override
            public void onScreenChange(int currentIndex) {
               // System.out.println("chen ssssssssssss 分页监听");
                generatePageControl(currentIndex);
            }
        });
    }  
  
    public PageControlView(Context context) {  
        super(context);  
        this.init(context);  
    }  
    public PageControlView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        this.init(context);  
    }  
  
    private void init(Context context) {  
        this.context=context;  
    }  
  
    private void generatePageControl(int currentIndex) {  
        this.removeAllViews();  
  
        int pageNum = this.count; // 显示多少个    
        int pageNo = currentIndex+1; //第几页      currentIndex  下标从0 开始  所以第二页是加1
        int pageSum = this.count; //总共多少页   
          
          
        if(pageSum>1){  
            int currentNum = (pageNo % pageNum == 0 ? (pageNo / pageNum) - 1    
                     : (int) (pageNo / pageNum))     
                     * pageNum;   
              
             if (currentNum < 0)     
                 currentNum = 0;     
               
             //下面显示的图标  
             for (int i = 0; i < pageSum; i++) {     
                // if ((currentNum + i + 1) > pageSum || pageSum < 2)
            	 if(pageSum<2)
                     break;     
                   
                 ImageView imageView = new ImageView(context);  
                 if(currentNum + i + 1 == pageNo){  
                     imageView.setImageResource(R.drawable.tab_account_press);  
                 }else{  
                     imageView.setImageResource(R.drawable.tab_account_icon);  
                 }  
                 //禁止显示
                 this.addView(imageView);  
             }     

        }  
    }  
}
