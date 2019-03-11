package com.example.administrator.laundry.util;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.laundry.R;
import com.lzy.imagepicker.loader.ImageLoader;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 图片加载器
 * author:lq
 */
public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
            Glide.with(activity)                             //配置上下文
                    .load(path)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .error(R.mipmap.collect_mrtp)           //设置错误图片
                    .placeholder(R.mipmap.collect_mrtp)     //设置占位图片
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView);


    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        if(null==path|| TextUtils.isEmpty(path)){
            return;
        }
        if (isHttpUrl(path)) {
            Glide.with(activity)                             //配置上下文
                    .load(path)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView);
        } else {
            Glide.with(activity)                             //配置上下文
                    .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView);
        }


    }

    @Override
    public void clearMemoryCache() {
    }


    /**
     * 判断字符串是否为URL
     *
     * @return true:是URL、false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
        String regex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();//判断是否匹配
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }

}
