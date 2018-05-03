package com.example.mytopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AI on 2017/12/14.
 */

public class Topbar extends RelativeLayout{

    private Button leftButton,rightButton;
    private TextView tvTitle;

    /**
     * 左边Button的属性
     */
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    /**
     *右边Button的属性
     */
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    /**
     *title的属性
     */
    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams,rightParams,titleParams;

    private topbarClickListener listener;

    public interface topbarClickListener{
        public void leftClick();
        public void rightClick();
    }

    public void setOnTopbarClickListener(topbarClickListener listener){
        this.listener=listener;
    }

    public Topbar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 获取到的自定义属性
         */
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.Topbar);
        /**
         * getColor()这个方法接受两个参数，第一个是自定义属性的key，第二个是属性的默认值
         */
        leftTextColor=ta.getColor(R.styleable.Topbar_leftTextColor,0);
        leftBackground=ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText=ta.getString(R.styleable.Topbar_leftText);

        rightTextColor=ta.getColor(R.styleable.Topbar_rightTextColor,0);
        rightBackground=ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText=ta.getString(R.styleable.Topbar_rightText);

        titleTextSize=ta.getDimension(R.styleable.Topbar_titleTextSize,0);
        titleTextColor=ta.getColor(R.styleable.Topbar_titleTextColor,0);
        title=ta.getString(R.styleable.Topbar_title);

        /**
         * 回收，避免浪费资源，避免缓存引起的错误
         */
        ta.recycle();

        leftButton=new Button(context);
        rightButton=new Button(context);
        tvTitle=new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);

        /**
         * 设置整个Topbar的背景
         */
        setBackgroundColor(0xFFF59563);
        /**
         * 宽高布局参数
         */
        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        /**
         * 在父控件里居左对齐
         */
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        /**
         * 把左Button加到布局中
         */
        addView(leftButton,leftParams);

        /**
         * 宽高布局参数
         */
        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        /**
         * 在父控件里居右对齐
         */
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        /**
         * 把右Button加到布局中
         */
        addView(rightButton,rightParams);

        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(tvTitle,titleParams);


        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    /**
     * 设置左button的可见性
     * @param flag
     */
    public void setLeftButtonIsVisable(boolean flag){
        if (flag){
            leftButton.setVisibility(View.VISIBLE);
        }else{
            leftButton.setVisibility(View.GONE);
        }
    }
}
