package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.InfoBean;
import xutils.bwie.com.xutils.R;

/**
 * Created by 李英杰 on 2017/8/29.
 */

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<InfoBean> list;

    public NewsAdapter(Context context, List<InfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.news_item,null);
            holder.textView=view.findViewById(R.id.tv);
            holder.imageView=view.findViewById(R.id.iv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i).title);
        ImageLoader.getInstance().displayImage(list.get(i).url,holder.imageView);
        return view;
    }

    static class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }
}
