# BottomDialog
### Screenshots
 ![image](https://github.com/yaoyao0719/DemoDialog/blob/master/screenshots/1.png?raw=true)

### Step

 ```
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
 ```