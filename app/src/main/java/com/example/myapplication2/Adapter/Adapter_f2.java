package com.example.myapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.example.myapplication2.Data.Data_Knowledge_f2;
import com.example.myapplication2.R;

import java.util.List;

public class Adapter_f2 extends BaseAdapter {

    private List<Data_Knowledge_f2> itemList;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public Adapter_f2(Context context, List<Data_Knowledge_f2> itemList) {
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 如果 convertView 为 null，则需要创建新的视图
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_f2, parent, false);
        }

        // 获取当前项的数据
        Data_Knowledge_f2 item = itemList.get(position);

        // 获取视图中的控件并绑定数据
        TextView textView = convertView.findViewById(R.id.sentence);
        ImageButton imageButton = convertView.findViewById(R.id.button3_5);

        // 设置控件的数据
        textView.setText(item.getTitle());
        imageButton.setImageResource(item.getImageId());

        // 设置点击事件
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });

        return convertView;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
