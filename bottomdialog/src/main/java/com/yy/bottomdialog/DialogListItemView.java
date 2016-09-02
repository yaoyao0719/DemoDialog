package com.yy.bottomdialog;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by yaoyao on 16/8/23.
 */

public  class DialogListItemView extends FrameLayout {

    public DialogListItemView(Context context) {
        super(context);
        init();
    }

    public DialogListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DialogListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextView lbTitle;
    private View pop_succ;

    private DialogListItem listItem;

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.text_list_item, this);
        lbTitle = (TextView) findViewById(R.id.text);
        pop_succ=findViewById(R.id.pop_succ);
    }

    public void setListItem(DialogListItem listItem) {
        this.listItem = listItem;
        lbTitle.setText(listItem.value == null ? "" : listItem.value);
    }
    public void setPop_succ(boolean flag){
        if(flag){
            pop_succ.setVisibility(View.VISIBLE);
        }else{
            pop_succ.setVisibility(View.GONE);
        }
    }
    public void setLbTextColor(String strColor){
        lbTitle.setTextColor(Color.parseColor(strColor));
    }
    public DialogListItem getListItem() {
        return listItem;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        findViewById(R.id.panelContainer).setOnClickListener(l);
    }
}
