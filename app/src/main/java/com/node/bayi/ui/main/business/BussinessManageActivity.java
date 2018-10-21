package com.node.bayi.ui.main.business;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.ui.main.introduce.EnergyIntroduceActivity;
import com.node.bayi.ui.main.introduce.LectricActivity;
import com.node.bayi.ui.main.introduce.SecurityActivity;

import java.io.*;

/**
 * 业务内容介绍管理
 */
public class BussinessManageActivity extends BaseActivity {

    /**
     * 进入业务内容介绍管理的方法
     *
     * @param activity
     */
    public static void startBussinessManageActivity(Activity activity) {
        activity.startActivity(new Intent(activity, BussinessManageActivity.class));
    }

    private static final String TAG = "BussinessManageActivity";

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected int getContentId() {
        return R.layout.activity_bussiness_manage;
    }

    @Override
    protected void initView() {
        tvTitle.setText("业务内容介绍管理");
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.ivBack, R.id.btnUpload1, R.id.btnUpload2, R.id.btnUpload3, R.id.btnUpload4, R.id.btnLook1, R.id.btnLook2, R.id.btnLook3, R.id.btnLook4})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnUpload1:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 1);

                break;
            case R.id.btnLook1:
                /**
                 * 节能用电的预览
                 */
                LectricActivity.startLectricActivity(this);
                break;
            case R.id.btnUpload2:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnLook2:
//                Toast.makeText(this, "预览", Toast.LENGTH_SHORT).show();
                SecurityActivity.startSecurityActivity(this);
                break;
            case R.id.btnUpload3:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnLook3:
//                Toast.makeText(this, "预览", Toast.LENGTH_SHORT).show();
                EnergyIntroduceActivity.startEnergyIntroduceActivity(this);
                break;
            case R.id.btnUpload4:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnLook4:
                Toast.makeText(this, "预览", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = getPath(uri);
                File file = new File(path);
                /* 取得扩展名 */
                String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
                if (end.equals("pdf")) {
                    try {//pdf
                        Log.e(TAG, "onActivityResult: " + path);
                        copy(path, Environment.getExternalStorageDirectory() + "/bayi/zhidao.pdf");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "不支持该文件类型的上传", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //得到正确路径
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getPath(Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;//sdk版本是否大于4.4
        if (isKitKat && DocumentsContract.isDocumentUri(this, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(this, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(this, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {

            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(this, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    public int copy(String fromFile, String toFile) {
        File[] currentFiles;
        File root = new File(fromFile);
        if (!root.exists()) {
            return -1;
        }
        //如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();
        //目标目录
        File targetDir = new File(toFile);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
            {
                copy(currentFiles[i].getPath() + "/", toFile + currentFiles[i].getName() + "/");
            } else
            //如果当前项为文件则进行文件拷贝
            {
                CopySdcardFile(currentFiles[i].getPath(), toFile + currentFiles[i].getName());
            }
        }
        return 0;
    }


    //文件拷贝    //要复制的目录下的所有非子目录(文件夹)文件拷贝
    public int CopySdcardFile(String fromFile, String toFile) {
        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;
        } catch (Exception ex) {
            return -1;
        }
    }
}
