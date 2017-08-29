package xutils.bwie.com.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import bean.NewsBean;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private String url="http://v.juhe.cn/toutiao/index";
    private String type="tiyu";
    private String key="22a108244dbb8d1f49967cd74a0c144d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);

        RequestParams requestParams=new RequestParams(url);
        requestParams.addQueryStringParameter("type",type);
        requestParams.addQueryStringParameter("key",key);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("json=="+result);
                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });




    }

    /**
     * 解析Json
     * @param result
     */
    private void parseJson(String result) {
        Gson gson=new Gson();
        NewsBean newsBean = gson.fromJson(result, NewsBean.class);

    }
}
