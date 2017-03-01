package com.atguigu.newmall.home.adapger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.GoodsBean;
import com.atguigu.newmall.home.bean.H5Bean;
import com.atguigu.newmall.home.bean.WebViewBean;
import com.atguigu.newmall.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.atguigu.newmall.home.adapger.HomeAdapger.GOOD_BEAN;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_more)
    ImageButton ibMore;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.activity_web_view)
    LinearLayout activityWebView;
    private WebViewBean mWebViewBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        getData();
    }

    public void getData() {
        mWebViewBean = (WebViewBean) getIntent().getSerializableExtra(HomeAdapger.WEBVIEW_BEAN);
        //设置标题
        tvTitle.setText(mWebViewBean.getName());

        //加载链接地址
        WebSettings settings = webview.getSettings();

        //设置支持js
        settings.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        settings.setBuiltInZoomControls(true);
        //设置双击单击
        settings.setUseWideViewPort(true);

        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });
        //1.添加addJavacriptInterface
        webview.addJavascriptInterface(new MyJavacriptInterface(), "cyc");
        webview.loadUrl(Constants.BASE_URL_IMAGE + mWebViewBean.getUrl());
    }

    @OnClick({R.id.ib_back, R.id.ib_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_more:
                break;
        }
    }

    private class MyJavacriptInterface {
        @JavascriptInterface
        public void jumpForAndroid(String data) {
            if (!TextUtils.isEmpty(data)) {
                H5Bean h5Bean = JSON.parseObject(data, H5Bean.class);
                //创建商品信息Bean对象
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setProduct_id(h5Bean.getValue().getProduct_id() + "");
                goodsBean.setCover_price("10080");
                goodsBean.setFigure("/1478770583834.png");
                goodsBean.setName("尚硅谷Android");
                Intent intent = new Intent(WebViewActivity.this, GoodsInfoActivity.class);
                intent.putExtra(GOOD_BEAN, goodsBean);
                startActivity(intent);
            }
        }
    }
}
