package com.example.myapplication2;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.ImageAdapter;
import com.example.myapplication2.Adapter.SentenceAdapter;
import com.example.myapplication2.CardData.PasteImages;
import com.example.myapplication2.CardData.PictureCreate;
import com.example.myapplication2.CardData.Sentences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView editList;
    GridView imageList;
    private ArrayList<Map<String,Object>> listItem;
    private ArrayList<Map<String, Object>> imageItem;
    SentenceAdapter sentenceAdapter;
    ImageAdapter imageAdapter;
    LinearLayout confirmButton;
    Boolean hasSentence;//是否传入句子
    String value;//句子
    int image_position = -1;

    //创建好图片和不能点击的三个部分
    LinearLayout l1;
    LinearLayout l2;
    RelativeLayout linearLayout;

    Button closeButton;
    Button toMeButton;
    Button shareButton;
    Button downloadButton;
    Bitmap bitmap;

    PictureCreate pictureCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // 获取 Intent 数据
        Intent intent = getIntent();
        hasSentence = intent.getBooleanExtra("hasSentence",false); // 接收整数，默认值为 0
        value = intent.getStringExtra("sentence"); // 接收字符串

        initView();
        initView2();

        confirmButton = findViewById(R.id.start_create);
        confirmButton.setOnClickListener(this);

        l1 = findViewById(R.id.linearLayout);
        l2 = findViewById(R.id.img_select);
        linearLayout = findViewById(R.id.show_picture);

        closeButton = findViewById(R.id.close_button);
        downloadButton = findViewById(R.id.download_button);
        shareButton = findViewById(R.id.share_button);
        toMeButton = findViewById(R.id.tome_button);


    }

    public void initView(){
        editList = findViewById(R.id.editList);
        editList.setOnClickListener(this);
        if(hasSentence){
            listItem = new Sentences(value).getListItem();
        }
        else {
            listItem = new Sentences().getListItem();
        }
        //为ListView绑定适配器
        sentenceAdapter = new SentenceAdapter(listItem);
        editList.setLayoutManager(new LinearLayoutManager(this));
        editList.setAdapter(sentenceAdapter);

        int position = listItem.size();
        editList.scrollToPosition(position);
    }

    public void initView2(){
        imageList = findViewById(R.id.imglist);
        PasteImages pasteImages = new PasteImages();
        imageItem = pasteImages.getListItem();
        //为ListView绑定适配器
        imageAdapter = new ImageAdapter(this, imageItem);
        imageList.setAdapter(imageAdapter);
        imageList.setOnItemClickListener((parent, view, position, id) -> {
            image_position = position;
            pasteImages.updateSelection(position);
            imageAdapter.notifyDataSetChanged();
            Log.e("item",position+" id:"+id);
        });
    }

    public void getInputTextFromEditList() {
        imageList.setEnabled(false);
        confirmButton.setEnabled(false);
        editList.setEnabled(false);

        closeButton.setEnabled(true);
        toMeButton.setEnabled(true);
        shareButton.setEnabled(true);
        downloadButton.setEnabled(true);

        linearLayout.setVisibility(View.VISIBLE);
        ImageView imageView = findViewById(R.id.myImageView);
        closeButton.setOnClickListener(this);
        //toMeButton.setOnClickListener(this);
        downloadButton.setOnClickListener(this);
        List<String> inputTexts = sentenceAdapter.getInputTexts();
//        for (String input : inputTexts) {
//            Log.d("InputText", input);
//        }
        Map<String, Object> item = imageItem.get(image_position);
        int image_id = (Integer) item.get("image");
        pictureCreate = new PictureCreate(this, inputTexts,image_id);
        bitmap = pictureCreate.getBitmap();//获取位图

        imageView.setImageBitmap(bitmap);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start_create){
            if (image_position == -1){
                Toast.makeText(this,"请选择图片", Toast.LENGTH_LONG).show();
            }
            else{
                getInputTextFromEditList();
            }
//            Intent intent = new Intent(this, CreateActivity.class);
//            intent.putExtra("sentence", "");
//            startActivity(intent);
        }
        if(view.getId() == R.id.close_button){
            linearLayout.setVisibility(View.INVISIBLE);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null; // 避免悬空引用
            }
            imageList.setEnabled(true);
            confirmButton.setEnabled(true);
            editList.setEnabled(true);

            closeButton.setEnabled(false);
            toMeButton.setEnabled(false);
            shareButton.setEnabled(false);
            downloadButton.setEnabled(false);
        }
        if (view.getId() == R.id.download_button){
            pictureCreate.saveBitmapToFile(bitmap);
            Toast.makeText(this,"已保存到相册", Toast.LENGTH_LONG).show();
        }
    }
}