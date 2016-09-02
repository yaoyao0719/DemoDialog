package com.yy.bottomdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yaoyao on 16/8/22.
 */

public class BottomDialog {

    public CustomDialog mCustomDialog;

    public BottomDialog(Context context) {
        mCustomDialog = new CustomDialog(context);
    }
    public BottomDialog setHeight(int height){
        mCustomDialog.setHeight(height);
        return this;
    }
    public BottomDialog title(String title) {
        mCustomDialog.setTitle(title);
        return this;
    }
    public BottomDialog subTitle(String title) {
        mCustomDialog.setSubTitle(title);
        return this;
    }
    public BottomDialog addItems(List<DialogListItem> items) {
        mCustomDialog.addItems(items);
        return this;
    }
    public BottomDialog itemClick(OnItemClickListener listener) {
        mCustomDialog.setItemClick(listener);
        return this;
    }
    public BottomDialog itemSelected(String key) {
        mCustomDialog.setItemSelected(key);
        return this;
    }
    public void show() {
        mCustomDialog.show();
    }
    public void dismiss() {
        mCustomDialog.dismiss();
    }

    private final class CustomDialog extends BottomSheetDialog {


        private TextView mLbTitle;
        private TextView mLbSubTitle;
        private LinearLayout mPanelItems;
        private Button mBtnCancel;
        private DialogAdapter adapter;
        private Context mContext;
        private View contentView;

        public CustomDialog(@NonNull Context context) {
            super(context);
            mContext=context;
            init();
        }

        private void init() {
            contentView = View.inflate(getContext(), R.layout.bottom_dialog, null);
            setContentView(contentView);
            mLbTitle=(TextView)findViewById(R.id.lbTitle);
            mLbSubTitle=(TextView)findViewById(R.id.lbSubTitle);
            mPanelItems=(LinearLayout)findViewById(R.id.panelItems);
            mBtnCancel=(Button)findViewById(R.id.btnCancel);
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            mBtnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }

        public void setTitle(String strTitle){
            mLbTitle.setText(strTitle);
        }
        public void setSubTitle(String strTitle){
            mLbSubTitle.setText(strTitle);
            mLbSubTitle.setVisibility(View.VISIBLE);
        }
        public void setCancleText(String strTitle){
            mBtnCancel.setText(strTitle);
        }
        public void addItems(List<DialogListItem> items) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RecyclerView.LayoutManager manager;
            adapter = new DialogAdapter(mContext,items);
            manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            RecyclerView recyclerView = new RecyclerView(getContext());
            recyclerView.setLayoutParams(params);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            mPanelItems.addView(recyclerView);
        }
        public void setItemClick(OnItemClickListener onItemClickListener) {
            adapter.setItemClick(onItemClickListener);
        }
        public void setItemSelected(String key) {
            adapter.setItemSelected(key);
        }
        public void setHeight(int height) {
            BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View)contentView.getParent());
            mBehavior.setPeekHeight(height);
        }
    }
    private class DialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<DialogListItem> mItems = Collections.emptyList();
        private Context mContext;
        private OnItemClickListener itemClickListener;
        private String itemKey;


        public DialogAdapter(Context context,List<DialogListItem> mItems) {
            setList(mItems);
            mContext=context;
        }
        private void setList(List<DialogListItem> items) {
            mItems = items == null ? new ArrayList<DialogListItem>() : items;
        }

        public void setItemClick(OnItemClickListener onItemClickListener) {
            this.itemClickListener = onItemClickListener;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = new DialogListItemView(mContext);
            return new DialogListItemHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
            ((DialogListItemView) holder.itemView).setListItem(mItems.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (itemClickListener != null) itemClickListener.click(mItems.get(position));
                }
            });
            if(mItems.get(position).key.equals(itemKey)||mItems.get(position).value.equals(itemKey)){
                ((DialogListItemView) holder.itemView).setLbTextColor("#ff8800");
                ((DialogListItemView) holder.itemView).setPop_succ(true);
            }
        }
        public  void setItemSelected(String key){
            itemKey=key;
            notifyDataSetChanged();
        }
        @Override
        public int getItemCount() {
            return mItems.size();
        }
        public class DialogListItemHolder extends RecyclerView.ViewHolder {
            public DialogListItemHolder(View itemView) {
                super(itemView);
            }
        }
    }


}
