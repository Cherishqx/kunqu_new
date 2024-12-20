package com.example.myapplication2;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.ShowDIYAdapter;
import com.example.myapplication2.Data.ShowDIY;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.Manifest;
import android.widget.Toast;

public class HistoryFragment extends Fragment {

    private static final String TAG = "HistoryFragment"; // 用于日志的标签
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 2;
    private RecyclerView recyclerView;
    private ShowDIYAdapter adapter;
    private boolean isUp = true;
    private ImageButton upDownButton;
    private EmoCalendarView emoCalendarView;
    private List<ShowDIY> items;
//    private static final String ARG_IMAGE_PATH = "imagePath";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        Locale locale = new Locale("zh");
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        emoCalendarView = view.findViewById(R.id.emoCalendarView);
        if (emoCalendarView == null) {
            Log.e(TAG, "EmoCalendarView is not found in the layout.");
            return view;
        }

        emoCalendarView.setOnDateClickListener(new EmoCalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(int day) {
                Log.d(TAG, "Date clicked: " + day);
                showBackgroundSelectionDialog(day);
            }
        });

        // 初始化RecyclerView
        recyclerView = view.findViewById(R.id.show_diy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShowDIYAdapter(items, recyclerView);
        recyclerView.setAdapter(adapter);

        // 添加按钮以选择图片
        ImageButton selectImageButton = view.findViewById(R.id.select_image_button);
        selectImageButton.setOnClickListener(v -> requestStoragePermission());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        upDownButton = view.findViewById(R.id.up_down);
        if (upDownButton == null) {
            Log.e(TAG, "UpDownButton is not found in the layout.");
            return;
        }
        upDownButton.setImageResource(R.drawable.ic_up);

        upDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUp) {
                    upDownButton.setImageResource(R.drawable.ic_down);
                    emoCalendarView.setVisibility(View.GONE); // 隐藏日历视图
                } else {
                    upDownButton.setImageResource(R.drawable.ic_up);
                    emoCalendarView.setVisibility(View.VISIBLE); // 显示日历视图
                }
                isUp = !isUp;
            }
        });
    }

    public void showBackgroundSelectionDialog(final int day) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_background_selection, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        ImageButton image1 = dialogView.findViewById(R.id.emotion1);
        ImageButton image2 = dialogView.findViewById(R.id.emotion2);
        ImageButton image3 = dialogView.findViewById(R.id.emotion3);
        ImageButton image4 = dialogView.findViewById(R.id.emotion4);
        ImageButton image5 = dialogView.findViewById(R.id.emotion5);
        ImageButton customEmotion = dialogView.findViewById(R.id.custom_emotion);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Background selection clicked: " + v.getId());
                Drawable selectedImage = null;
                int id = v.getId();
                if (id == R.id.emotion1) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion1, null);
                } else if (id == R.id.emotion2) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion2, null);
                } else if (id == R.id.emotion3) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion3, null);
                } else if (id == R.id.emotion4) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion4, null);
                } else if (id == R.id.emotion5) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion5, null);
                } else if (id == R.id.custom_emotion) {
                    // Handle custom emotion logic here
                }
                if (selectedImage != null) {
                    emoCalendarView.setDateImage(String.valueOf(day), selectedImage);
                    Log.d(TAG, "Date image set for day: " + day);
                }
                dialog.dismiss();
            }
        };

        image1.setOnClickListener(listener);
        image2.setOnClickListener(listener);
        image3.setOnClickListener(listener);
        image4.setOnClickListener(listener);
        image5.setOnClickListener(listener);
        customEmotion.setOnClickListener(listener);

        dialog.show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            showInputDialog(selectedImageUri); // 弹出对话框来输入文本
        }
    }

    private void showInputDialog(Uri imagePath) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_input, null); // 创建包含 EditText 的布局

        builder.setView(dialogView);
        EditText editText = dialogView.findViewById(R.id.input_text); // 假设您有这样一个 EditText
        builder.setPositiveButton("确定", (dialog, which) -> {
            String text = editText.getText().toString();
            ShowDIY newItem = new ShowDIY(imagePath.toString(), text); // 使用选中的图片路径和输入的文本
            items.add(newItem); // 添加到列表
            adapter.notifyDataSetChanged(); // 通知适配器数据已更改
        });

        builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    private void requestStoragePermission() {
        Log.e(TAG, "请求存储权限");
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "权限未被授予，申请权限");
            requestPermissions(
                    new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                    REQUEST_CODE_STORAGE_PERMISSION); // 使用正确的请求码
            Log.e(TAG, "请求成功了嘛");
        } else {
            Log.e(TAG, "权限已被授予，打开图库");
            openGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) { // 检查正确的请求码
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery(); // 如果权限被授予，打开图库
                Log.e("权限已打开", "权限");
            } else {
                Log.e("权限未被授予", "权限"); // 用户拒绝了权限
                Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}