package com.atguigu.newmall.home.adapger;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.GoodsBean;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaoMiao extends AppCompatActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_name_id)
    Button tvNameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_miao);
        ButterKnife.bind(this);

        GoodsBean goodbean = (GoodsBean) getIntent().getSerializableExtra("good_id");
        Log.e("TAG", "+++" + goodbean.getName());
        Log.e("TAG", "+++" + goodbean.getCover_price());
        Log.e("TAG", "+++" + goodbean.getFigure());
        Log.e("TAG", "+++" + goodbean.getProduct_id());
        Bitmap bitmap = encodeAsBitmap(goodbean.getProduct_id() + "," + goodbean.getCover_price() + "," + goodbean.getFigure() + "," + goodbean.getName()+",");
        ivImage.setImageBitmap(bitmap);

    }


    Bitmap encodeAsBitmap(String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(result);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) { // ?
            return null;
        }

        // 如果不使用 ZXing Android Embedded 的话，要写的代码

//        int w = result.getWidth();
//        int h = result.getHeight();
//        int[] pixels = new int[w * h];
//        for (int y = 0; y < h; y++) {
//            int offset = y * w;
//            for (int x = 0; x < w; x++) {
//                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
//            }
//        }
//        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels,0,100,0,0,w,h);

        return bitmap;
    }

    @OnClick(R.id.tv_name_id)
    public void onClick() {
        finish();
    }
}
