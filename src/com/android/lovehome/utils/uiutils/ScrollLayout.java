package com.android.lovehome.utils.uiutils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;


/** 
 * 仿Launcher中的WorkSapce，可以左右滑动切换屏幕的类 
 *  
 */
public class ScrollLayout extends ViewGroup{

	private static final String TAG = "ScrollLayout";  
	private Scroller mScroller;  
	private VelocityTracker mVelocityTracker;  
	private int mCurScreen;  
	private int mDefaultScreen = 0;  
	 
	private static final int TOUCH_STATE_REST = 0;  
	private static final int TOUCH_STATE_SCROLLING = 1;  
	
	private static final int SNAP_VELOCITY = 600;  
	
	private int mTouchState = TOUCH_STATE_REST;  
	private int mTouchSlop;  
	private float mLastMotionX;  
	private float mLastMotionY; 
	
	private int currentScreenIndex = 0;
	
	
	public ScrollLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public int getCurrentScreenIndex() {  
		 return currentScreenIndex;  
	}  
		 
	public void setCurrentScreenIndex(int currentScreenIndex) {  
	     this.currentScreenIndex = currentScreenIndex;  
	}  
	public ScrollLayout(Context context, AttributeSet attrs) {  
	     this(context, attrs, 0);  
	    // TODO Auto-generated constructor stub   
	}  
	public ScrollLayout(Context context, AttributeSet attrs, int defStyle) {  
	    super(context, attrs, defStyle);  
	     // TODO Auto-generated constructor stub   
	    mScroller = new Scroller(context);  
	    mCurScreen = mDefaultScreen;  
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();  
	}  
	/*
	 * 当View分配所有的子元素的大小和位置时触发
	 * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
	 */
	@Override  
	protected void onLayout(boolean changed, int l, int t, int r, int b) {  
		      // TODO Auto-generated method stub   
		int childLeft = 0;  
	    final int childCount = getChildCount();  
		System.out.println("第三步childCount=" + childCount);  
		for (int i = 0; i < childCount; i++) {  
		     final View childView = getChildAt(i);  
		     if (childView.getVisibility() != View.GONE) {  
	              final int childWidth = childView.getMeasuredWidth();  
		          childView.layout(childLeft, 0, childLeft + childWidth,  
	              childView.getMeasuredHeight());  
		          childLeft += childWidth;  
	         }  
	    }  
   }  
		 
   /*
    * 确定所有子元素的大小
    * @see android.view.View#onMeasure(int, int)
    */
   @Override  
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	   Log.i(TAG, "第一步：onMeasure");  
       super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
 
       final int width = MeasureSpec.getSize(widthMeasureSpec);  
       final int widthMode = MeasureSpec.getMode(widthMeasureSpec);

       //MeasureSpec.EXACTLY的意思应该是：布局文件里面是否明确指定该控件的宽高（100dp这样的值）等
       if (widthMode != MeasureSpec.EXACTLY) {  
           throw new IllegalStateException(  
                   "ScrollLayout only canmCurScreen run at EXACTLY mode!");  
       }  
 
       final int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
       if (heightMode != MeasureSpec.EXACTLY) {  
           throw new IllegalStateException(  
                   "ScrollLayout only can run at EXACTLY mode!");  
       }  
 
       // The children are given the same width and height as the scrollLayout   
       final int count = getChildCount();  
       for (int i = 0; i < count; i++) {  
           getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);  
       }  
       System.out.println("moving to screen 第二步" + mCurScreen);  
       scrollTo(mCurScreen * width, 0);  
   }  
 
   /** 
    * According to the position of current layout scroll to the destination 
    * page. 
    */  
   public void snapToDestination() {  
       final int screenWidth = getWidth();  
       final int destScreen = (getScrollX() + screenWidth / 2) / screenWidth;  
       snapToScreen(destScreen);  
   }  
 
   //这方法是用来干嘛的
   public void snapToScreen(int whichScreen) {  
       // get the valid layout page   
       whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));  
       if (getScrollX() != (whichScreen * getWidth())) {  
 
           final int delta = whichScreen * getWidth() - getScrollX();  
           mScroller.startScroll(getScrollX(), 0, delta, 0,  
                   Math.abs(delta) * 2);  
           mCurScreen = whichScreen;  
           invalidate(); // Redraw the layout   
       }  
   }  
 
   public void setToScreen(int whichScreen) {  
       whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));  
       mCurScreen = whichScreen;  
       scrollTo(whichScreen * getWidth(), 0);  
   }  
 
   public int getCurScreen() {  
       return mCurScreen;  
   }  
 
   @Override  
   public void computeScroll() {  
       // TODO Auto-generated method stub   
       if (mScroller.computeScrollOffset()) {  
           scrollTo(mScroller.getCurrX(), mScroller.getCurrY());  
           postInvalidate();  
       }  
   }  
 
   /*
    * 触屏事件
    * @see android.view.View#onTouchEvent(android.view.MotionEvent)
    */
   @Override  
   public boolean onTouchEvent(MotionEvent event) {  
       // TODO Auto-generated method stub   
       if (mVelocityTracker == null) {  
           mVelocityTracker = VelocityTracker.obtain();  
       }  
       mVelocityTracker.addMovement(event);  
 
       final int action = event.getAction();  
       final float x = event.getX();  
       final float y = event.getY();  
 
       switch (action) {  
       case MotionEvent.ACTION_DOWN:  
           Log.i(TAG, "event down!");  
           if (!mScroller.isFinished()) {  
               mScroller.abortAnimation();  
           }  
           mLastMotionX = x;  
           break;  
 
       case MotionEvent.ACTION_MOVE:  
           int deltaX = (int) (mLastMotionX - x);  
           mLastMotionX = x;  
 
           scrollBy(deltaX, 0);  
           break;  
 
       case MotionEvent.ACTION_UP:  
           Log.i(TAG, "event : up");  
 
           // if (mTouchState == TOUCH_STATE_SCROLLING) {   
           final VelocityTracker velocityTracker = mVelocityTracker;  
           velocityTracker.computeCurrentVelocity(1000);  
           int velocityX = (int) velocityTracker.getXVelocity();  
 
           Log.i(TAG, "velocityX:" + velocityX); 
 
           //左移
           if (velocityX > SNAP_VELOCITY && mCurScreen > 0) {  
               // Fling enough to move left   
               Log.i(TAG, " 左移 snap left");  
               onScreenChangeListener.onScreenChange(mCurScreen - 1);  
              // System.out.println("chen sssssssdddddddddddddddddd");
               
               System.out.println("mCurScreen=" + (mCurScreen - 1));  
               snapToScreen(mCurScreen - 1);  
           } //右移
           else if (velocityX < -SNAP_VELOCITY  
                   && mCurScreen < getChildCount() - 1) {  
               // Fling enough to move right   
               Log.i(TAG, "右移   snap right");  
             //  System.out.println("chen sssssssddddddddddddddddddggggg");
               onScreenChangeListener.onScreenChange(mCurScreen + 1);  
               //只往右移动才加载数据   
               //onScreenChangeListenerDataLoad.onScreenChange(mCurScreen+1);  
               snapToScreen(mCurScreen + 1);  
           } else {  
               snapToDestination();  
           }  
 
           if (mVelocityTracker != null) {  
               mVelocityTracker.recycle();  
               mVelocityTracker = null;  
           }  
           // }   
           mTouchState = TOUCH_STATE_REST;  
           break;  
       case MotionEvent.ACTION_CANCEL:  
           mTouchState = TOUCH_STATE_REST;  
           break;  
       }  
 
       return true;  
   }  
 
   @Override  
   public boolean onInterceptTouchEvent(MotionEvent ev) {  
       // TODO Auto-generated method stub   
       Log.i(TAG, "onInterceptTouchEvent-slop:" + mTouchSlop);  
 
       final int action = ev.getAction();  
       if ((action == MotionEvent.ACTION_MOVE)  
               && (mTouchState != TOUCH_STATE_REST)) {  
           return true;  
       }  
 
       final float x = ev.getX();  
       final float y = ev.getY();  
 
       switch (action) {  
       case MotionEvent.ACTION_MOVE:  
           final int xDiff = (int) Math.abs(mLastMotionX - x);  
           if (xDiff > mTouchSlop) {  
               mTouchState = TOUCH_STATE_SCROLLING;  
 
           }  
           break;  
 
       case MotionEvent.ACTION_DOWN:  
           mLastMotionX = x;  
           mLastMotionY = y;  
           mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST  
                   : TOUCH_STATE_SCROLLING;  
           break;  
 
       case MotionEvent.ACTION_CANCEL:  
       case MotionEvent.ACTION_UP:  
           mTouchState = TOUCH_STATE_REST;  
           break;  
       }  
 
       return mTouchState != TOUCH_STATE_REST;  
    } 

	//分页监听   
    public interface OnScreenChangeListener {  
       void onScreenChange(int currentIndex);  
    }  
 
    private OnScreenChangeListener onScreenChangeListener;  
 
    public void setOnScreenChangeListener(  
           OnScreenChangeListener onScreenChangeListener) {  
      this.onScreenChangeListener = onScreenChangeListener;  
    }  
    
     
    //动态数据监听   
    public interface OnScreenChangeListenerDataLoad {  
       void onScreenChange(int currentIndex);  
    }  
	private OnScreenChangeListenerDataLoad onScreenChangeListenerDataLoad;  
	
	public void setOnScreenChangeListenerDataLoad(OnScreenChangeListenerDataLoad onScreenChangeListenerDataLoad) {  
		 this.onScreenChangeListenerDataLoad = onScreenChangeListenerDataLoad;  
	}  	         
 
}
