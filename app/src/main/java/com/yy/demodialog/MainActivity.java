package com.yy.demodialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yy.bottomdialog.BottomDialog;
import com.yy.bottomdialog.DialogListItem;
import com.yy.bottomdialog.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Context mContext;
    List<DialogListItem> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        textView=(TextView)findViewById(R.id.textView);
        DialogListItem listItem1=new DialogListItem(null,"item1","1");
        DialogListItem listItem2=new DialogListItem(null,"item2","2");
        DialogListItem listItem3=new DialogListItem(null,"item3","3");
        items.add(listItem1);
        items.add(listItem2);
        items.add(listItem3);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog();
            }
        },1000);

        textView.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    public void showDialog(){
        new BottomDialog(mContext)
                .title("bottomDialog")
                .addItems(items)
                .itemClick(new OnItemClickListener() {
                    @Override
                    public void click(DialogListItem item) {
                        Toast.makeText(mContext,item.value,Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
