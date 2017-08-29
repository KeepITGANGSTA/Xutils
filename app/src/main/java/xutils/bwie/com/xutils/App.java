package xutils.bwie.com.xutils;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by 李英杰 on 2017/8/29.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initXUtils();
        initImageLoader();
    }

    /**
     * 初始化ImageLoader
     */
    private void initImageLoader() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 初始化xUtils
     */
    private void initXUtils() {
        ImageOptions options=new ImageOptions.Builder()
                .setFadeIn(true)
                .build();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
