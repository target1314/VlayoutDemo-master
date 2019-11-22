package com.xl.vlayoutdemo;

public interface Config {
    //不同item必须不同的viewtype
    int BANNER_VIEW_TYPE = 1;
    int MENU_VIEW_TYPE = 2;
    int NEWS_VIEW_TYPE = 3;
    int TITLE_VIEW_TYPE = 4;
    int GRID_VIEW_TYPE = 5;

    int[] IMG_URLS = {R.mipmap.a_1, R.mipmap.a_2, R.mipmap.a_3, R.mipmap.a_4, R.mipmap.a_5, R.mipmap.a_6, R.mipmap.a_7, R.mipmap.a_8, R.mipmap.a_1, R.mipmap.a_4};
    String[] ITEM_NAMES = {"亚马逊", "海淘", "苏宁", "京东", "天猫超市", "拼多多", "商品", "领金币", "拍卖", "分类"};
    int[] GRID_URL = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d};
    int[] ITEM_URL = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.a};
}
