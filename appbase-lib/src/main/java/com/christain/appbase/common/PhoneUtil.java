package com.christain.appbase.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * 类描述 ：手机工具类
 * Author: Christain
 * Date  : 16/3/18
 */
public class PhoneUtil {

    private static int screenHeight = 0;
    private static int screenWidth = 0;

    private PhoneUtil() {
        throw new AssertionError();
    }

    /**
     * sd卡是否插入
     *
     * @return
     */
    public static boolean isSdCardExit() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡根目录路径
     *
     * @return
     */
    public static String getSdCardRootPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取app安装根目录
     *
     * @return
     */
    @SuppressLint("SdCardPath")
    public static String getAppRootPath(Context context) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getApplicationContext().getPackageName();
    }

    /**
     * 复制单个文件
     */
    public static void copyFile(Context context, File fromeFile, File toFile) {
        try {
            int bytesum = 0;
            int byteread = 0;
            if (fromeFile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(fromeFile); //读入原文件
                FileOutputStream fs = new FileOutputStream(toFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                if (toFile.setLastModified(System.currentTimeMillis())) {
                    context.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + toFile.getPath())));
                }
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 获取包信息
     *
     * @return 包信息
     */
    public static PackageInfo getVersionInfo(Context context) {
        try {
            PackageManager pm = context.getApplicationContext().getPackageManager();
            PackageInfo pInfo = pm.getPackageInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前系统版本大于等于输入的版本号
     *
     * @param versionCode
     * @return
     */
    public static boolean gtVersion(int versionCode) {
        return Build.VERSION.SDK_INT >= versionCode;
    }

    /**
     * 检查网络是否可用
     *
     * @return true可用， false不可用
     */
    public static boolean isNetWorkAvailable(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 获取屏幕信息
     *
     * @param activity
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) activity).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }
        return screenHeight;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }
        return screenWidth;
    }

    /**
     * 根据手机分辨率从dp转成px
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 从sp到px
     *
     * @param spValue
     * @return
     */
    public static int px2dip(Context context, float spValue) {
        final float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (spValue / fontScale + 0.5f);
    }

    /**
     * 隐藏输入法键盘
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        try {
            View view = activity.getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示输入法键盘
     *
     * @param activity
     */
    public static void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(activity.getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 根据item高度重新设置ListView高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()));
        listView.setLayoutParams(params);
    }

    /**
     * 可用存储大小
     *
     * @param rootPath
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getAvailableSize(String rootPath) {
        StatFs inStatFs = new StatFs(rootPath);
        long blockSize = inStatFs.getBlockSize();
        long availaBlocks = inStatFs.getAvailableBlocks();
        int availaSize = (int) (availaBlocks * blockSize / 1024 / 1024);
        return availaSize;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHigh(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getApplicationContext().getResources().getDimensionPixelSize(x);
            return sbar;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return 0;
    }

    /**
     * 将bitmap保存为本地图片
     *
     * @param context
     * @param format
     * @param orgBmp
     * @param file
     * @param quality
     * @return
     */
    public static boolean saveBitmapToImageFile(Context context, Bitmap.CompressFormat format, Bitmap orgBmp, File file, int quality) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            orgBmp.compress(format, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            if (file.setLastModified(System.currentTimeMillis())) {
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将图片文件转换成bitmap
     *
     * @return
     */
    public static Bitmap getBitmapFromPath(String path, int maxNumOfPixels) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, opts);
            opts.inJustDecodeBounds = false;
            if (maxNumOfPixels <= 0) {
                opts.inSampleSize = computeSampleSize(opts, 3000 * 3000);
            } else {
                opts.inSampleSize = computeSampleSize(opts, maxNumOfPixels);
            }
            bitmap = BitmapFactory.decodeFile(path, opts);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    private static int computeSampleSize(BitmapFactory.Options options, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, -1, maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }
}
