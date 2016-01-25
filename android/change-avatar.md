 选择本地图片，更换头像
 ```
 private void changeAvatar() {
        Intent intent=new Intent();
        /* 开启Pictures画面Type设定为image */ 
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case -1:
                Uri uri=data.getData();
                ContentResolver cr=getContext().getContentResolver();
                Bitmap bitmap= null;
                try {
                    bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /*
                1、iv.setImageBitmap(Bitmap bmp);传入参数为Bitmap
                2、iv.setImageDrawable(Drawable able);传入参数为 BitmapDrawable；
                3、iv.setImageResource(int rid); 传入参数为图片资源ID                */
                
                //<a href="http://blog.csdn.net/hezhipin610039/article/details/7899248">drawable和bitmap互换的方法</a>
                Drawable drawable=new BitmapDrawable(bitmap);
                ((CircleImageView)v.findViewById(R.id.avatar)).setImageDrawable(drawable);
                break;
            default:
                break;

        }
    }
    ```
