package com.example.bj.superdemo.ui.customview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 一個ViewPager的demo
 */
public class ViewPagerActivity extends BaseActivity {
    private ViewPager vp_normal;
    private ViewPager vp_second;
    private int[] imageViews=new int[]{R.drawable.bg,R.drawable.fengjing1,R.drawable.fenjing2};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        vp_normal= (ViewPager) findViewById(R.id.vp_normal);
        vp_second= (ViewPager) findViewById(R.id.vp_second);
        vp_normal.setPageMargin(30);
        //vp_normal.setOffscreenPageLimit(3);
      //  vp_normal.setPageTransformer(true,new AlphaPageTransformer());

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
                page.setRotation(position*mMaxRotate);

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
                page.setRotation(mMaxRotate*position);

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
private int count=-1;
        @Override
        public Object instantiateItem(ViewGroup container, int position) {


//if(count-position==list.size()-1)
//
//
//            if(count> position)
//                if(container.getChildAt(position % list.size()+1)!=null)
//                    container.removeViewAt(position % list.size()+1);
//                else  if(count< position)
//                    if(container.getChildAt(position % list.size()-1)!=null)
//                        container.removeViewAt(position % list.size()-1);


            if((ViewPager)list.get(position%list.size()).getParent()!=null){
             }


            count=position;


            container.addView(list.get(position % list.size()));
            System.out.println("instantiateItem"+position+"----");
            return list.get(position % list.size());
//            container.addView(list.get(position % list.size()));
//            return list.get(position % list.size());
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
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
