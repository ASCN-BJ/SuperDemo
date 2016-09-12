package com.example.bj.superdemo.ui.customview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * description:一個ViewPager的demo
 */
public class ViewPagerActivity extends BaseActivity {
    private ViewPager vp_normal;
    private ViewPager vp_second;
    private int[] imageViews=new int[]{R.drawable.bg,R.drawable.fengjing1,R.drawable.fenjing2,R.drawable.dvq};//,R.drawable.dvq
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        vp_normal= (ViewPager) findViewById(R.id.vp_normal);
        vp_second= (ViewPager) findViewById(R.id.vp_second);
        vp_normal.setPageMargin(30);
//        vp_normal.setOffscreenPageLimit(3);
       vp_normal.setPageTransformer(true,new AlphaPageTransformer());

        initV();
        vp_normal.setAdapter(new NormalViewPagerAdapter(mImageViews));
        //需要放在适配器后面才管用
        vp_normal.setCurrentItem(Integer.MAX_VALUE/2);
       // vp_second.setAdapter(new NormalViewPagerAdapter(mImageViews));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 渐变动画,并且旋转
     */
    private class AlphaPageTransformer implements ViewPager.PageTransformer{
        private static final float DEFAULT_MIN_ALPHA = 0.5f;
        private float mMinAlpha = DEFAULT_MIN_ALPHA;
        private static final float DEFAULT_MAX_ROTATE = 15.0f;
        private float mMaxRotate = DEFAULT_MAX_ROTATE;
        private int maxValue=Integer.MAX_VALUE;
        @Override
        public void transformPage(View page, float position) {
//            System.out.println("page"+page.toString()+"------position"+position);
            if (position<-1){//在pager的左面
                page.setPivotX(page.getWidth()/2);
                page.setPivotY(page.getHeight());
                page.setRotation(position*180f);

                page.setAlpha(mMinAlpha);//渐变动画
            }else if (position>=-1&&position<0){
                page.setPivotX(page.getWidth()/2);
                page.setPivotY(page.getHeight());
                page.setRotation(mMaxRotate*position);

                page.setAlpha(mMinAlpha+(1+position)*mMinAlpha);
            }else if(position>=0&&position<1){
                page.setPivotX(page.getWidth()/2);
                page.setPivotY(page.getHeight());
                page.setRotation(mMaxRotate*position);

                page.setAlpha(mMinAlpha+(1-position)*mMinAlpha);
            }else {
                page.setPivotX(page.getWidth()/2);
                page.setPivotY(page.getHeight());
                page.setRotation(180f*position);

                page.setAlpha(mMinAlpha);
            }

        }
    }

    /**
     * 实现ViewPager的轮播
     */
    private class NormalViewPagerAdapter extends PagerAdapter{
        public NormalViewPagerAdapter() {

        }
        List<ImageView> list ;
        public NormalViewPagerAdapter(List<ImageView> list) {
            this.list=list;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        /**
         * 初始化一个实例的时候不要在外面new 一个对象的数组， 应该是在里面new一个对象，因为ViewPager没有办法remove掉在数组中new出来的对象
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
//            if((ViewPager)list.get(position%list.size()).getParent()!=null){
//                ((ViewPager) list.get(position % list.size())
//                        .getParent()).removeView(list.get(position
//                        % list.size()));
//            }
//            container.getChildCount();
//            container.addView(list.get(position % list.size()));
//            Toast.makeText(container.getContext(),container.getChildCount()+"" ,Toast.LENGTH_SHORT).show();
//            System.out.println("instantiateItem"+position+"----");
            ImageView view = new ImageView(ViewPagerActivity.this);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(imageViews[position % list.size()]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ViewPagerActivity.this,"postion"+position,Toast.LENGTH_SHORT).show();
                }
            });

            container.addView(view);
            return view;
            //return list.get(position % list.size());
//            container.addView(list.get(position % list.size()));
//            return list.get(position % list.size());
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container,position,object);
//            System.out.println("destroyItem"+position+"----");
//            container.removeView(list.get(position % list.size()));
//            container.removeView(list.get(position % list.size()));
           container.removeView((View) object);
        }
    }
    private void initV(){
        for (int imgId : imageViews)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }
}
