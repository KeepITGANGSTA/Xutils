package xutils.bwie.com.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adapter.NewsAdapter;
import bean.InfoBean;
import bean.NewsBean;
import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener{

    private String url="http://v.juhe.cn/toutiao/index";
    private String type="tiyu";
    private String key="22a108244dbb8d1f49967cd74a0c144d";
    @ViewInject(R.id.mXListView) XListView xListView;

    private Object data;
    private List<InfoBean> list=new ArrayList<>();
    private NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);

        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        RequestParams requestParams = new RequestParams(url);
        requestParams.addQueryStringParameter("type", type);
        requestParams.addQueryStringParameter("key", key);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("json==" + result);
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
                setAdapter();
            }
        });
    }

    private void setAdapter(){
        if (newsAdapter==null){
            newsAdapter = new NewsAdapter(MainActivity.this,list);
            xListView.setAdapter(newsAdapter);
        }else {
            newsAdapter.notifyDataSetChanged();
        }
        xListView.stopRefresh();
        xListView.stopLoadMore();

    }
    /**
     * 解析Json
     * @param result
     */
    private void parseJson(String result) {
        Gson gson=new Gson();
        NewsBean newsBean = gson.fromJson(result, NewsBean.class);
        NewsBean.ResultBean result1 = newsBean.result;
        List<NewsBean.ResultBean.DataBean> data = result1.data;
        for (NewsBean.ResultBean.DataBean dataBean : data) {
            String title = dataBean.title;
            InfoBean infoBean=new InfoBean();
            infoBean.title=title;
            infoBean.url=dataBean.thumbnail_pic_s;
            list.add(infoBean);
        }
    }

    @Override
    public void onRefresh() {
        setAdapter();
    }

    @Override
    public void onLoadMore() {
        setAdapter();
    }
}
