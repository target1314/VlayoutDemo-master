package com.xl.vlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.xl.vlayoutdemo.adapter.BaseDelegateAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.xl.vlayoutdemo.Config.BANNER_VIEW_TYPE;
import static com.xl.vlayoutdemo.Config.GRID_URL;
import static com.xl.vlayoutdemo.Config.GRID_VIEW_TYPE;
import static com.xl.vlayoutdemo.Config.IMG_URLS;
import static com.xl.vlayoutdemo.Config.ITEM_NAMES;
import static com.xl.vlayoutdemo.Config.ITEM_URL;
import static com.xl.vlayoutdemo.Config.MENU_VIEW_TYPE;
import static com.xl.vlayoutdemo.Config.NEWS_VIEW_TYPE;
import static com.xl.vlayoutdemo.Config.TITLE_VIEW_TYPE;


/**
 * Created by target on 2017/9/15.
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<DelegateAdapter.Adapter> mAdapters; //存放各个模块的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mAdapters = new LinkedList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        //初始化
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);

        //banner
        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper()
                , R.layout.vlayout_banner, 1, BANNER_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("http://goodspics.oss-cn-beijing.aliyuncs.com/1569477854130.jpg");
                arrayList.add("http://goodspics.oss-cn-beijing.aliyuncs.com/1567129846869.png");
                arrayList.add("http://goodspics.oss-cn-beijing.aliyuncs.com/1569477854130.jpg");
                arrayList.add("http://goodspics.oss-cn-beijing.aliyuncs.com/1567129846869.png");
                arrayList.add("http://goodspics.oss-cn-beijing.aliyuncs.com/1569477854130.jpg");
                arrayList.add("http://goodspics.oss-cn-beijing.aliyuncs.com/1567129846869.png");
                // 绑定数据
                Banner mBanner = holder.getView(R.id.banner);
                //设置banner样式
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                mBanner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                mBanner.setImages(arrayList);
                //设置自动轮播，默认为true
                mBanner.isAutoPlay(true);
                //设置轮播时间
                mBanner.setDelayTime(5000);
                //设置指示器位置（当banner模式中有指示器时）
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                mBanner.start();

                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getApplicationContext(), "banner点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        //把轮播器添加到集合
        mAdapters.add(bannerAdapter);

        //menu
        // 在构造函数设置每行的网格个数
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        gridLayoutHelper.setVGap(16);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0);// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.WHITE);
        BaseDelegateAdapter menuAdapter = new BaseDelegateAdapter(this, gridLayoutHelper, R.layout.vlayout_menu
                , 10, MENU_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_menu_title_home, ITEM_NAMES[position] + "");
                holder.setImageResource(R.id.iv_menu_home, IMG_URLS[position]);
                holder.getView(R.id.ll_menu_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), ITEM_NAMES[position], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mAdapters.add(menuAdapter);
        //news
        BaseDelegateAdapter newsAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper()
                , R.layout.vlayout_news, 1, NEWS_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                MarqueeView marqueeView1 = holder.getView(R.id.marqueeView1);

                List<String> info1 = new ArrayList<>();
                info1.add("【秋冬滋润】【爆款】DIOR/迪奥 粉漾魅惑润变色唇膏3.5g多色可选润唇嫩唇超滋润");
                info1.add("【秋冬滋润】【爆款】DIOR/迪奥 粉漾魅惑润变色唇膏3.5g多色可选润唇嫩唇超滋润");
                // 在代码里设置自己的动画
                marqueeView1.startWithList(info1, R.anim.anim_bottom_in, R.anim.anim_top_out);

                marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mAdapters.add(newsAdapter);

        //这里我就循环item 实际项目中不同的ITEM 继续往下走就行
        for (int i = 0; i < ITEM_URL.length; i++) {
            //item1 title
            final int finalI = i;
            BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.vlayout_title, 1, TITLE_VIEW_TYPE) {
                @Override
                public void onBindViewHolder(BaseViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.setImageResource(R.id.iv, ITEM_URL[finalI]);
                }
            };
            mAdapters.add(titleAdapter);
            //item1 gird
            GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(2);
            gridLayoutHelper.setMargin(0, 0, 0, 0);
            gridLayoutHelper.setPadding(0, 0, 0, 0);
            gridLayoutHelper.setVGap(0);// 控制子元素之间的垂直间距
            gridLayoutHelper.setHGap(0);// 控制子元素之间的水平间距
            gridLayoutHelper.setBgColor(Color.WHITE);
            gridLayoutHelper.setAutoExpand(true);//是否自动填充空白区域
            BaseDelegateAdapter girdAdapter = new BaseDelegateAdapter(this, gridLayoutHelper1, R.layout.vlayout_grid
                    , 4, GRID_VIEW_TYPE) {
                @Override
                public void onBindViewHolder(BaseViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    int item = GRID_URL[position];
                    ImageView iv = holder.getView(R.id.iv);
                    Glide.with(getApplicationContext()).load(item).into(iv);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            mAdapters.add(girdAdapter);
        }

        //设置适配器
        delegateAdapter.setAdapters(mAdapters);
    }

    private void initData() {

    }


}
