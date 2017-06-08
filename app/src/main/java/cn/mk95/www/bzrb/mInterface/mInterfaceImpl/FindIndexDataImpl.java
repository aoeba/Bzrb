package cn.mk95.www.bzrb.mInterface.mInterfaceImpl;

import com.google.gson.Gson;

import java.io.IOException;

import cn.mk95.www.bzrb.mInterface.FindIndexData;
import cn.mk95.www.bzrb.model.IndexPageOneDataEntity;
import cn.mk95.www.bzrb.utils.MyUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 11149 on 2017/6/7.
 */

public class FindIndexDataImpl implements FindIndexData {

    @Override
    public void findIndexPageOndeData() {
        IndexPageOneDataEntity indexPageOneDataEntity = null;
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url(MyUrl.INDEX_URL)
                .build();
        try {
            Response response = client.newCall(request).execute();
            indexPageOneDataEntity = gson.fromJson(response.body().string(), IndexPageOneDataEntity.class);
        } catch (IOException e) {

        } finally {
            if (indexPageOneDataEntity == null) {
                System.out.println("==========失败========");
            } else {
                System.out.println("===========成功=========");
                System.out.println(indexPageOneDataEntity.getData().toString()
                        + "\n" + indexPageOneDataEntity.getTop_stories().toString()
                        + "\n" + indexPageOneDataEntity.getTimestamp());
            }
            indexDataCallBack.indexPageOneData(indexPageOneDataEntity);
        }
    }

    IndexDataCallBack indexDataCallBack=null;

    public interface IndexDataCallBack{
        public void indexPageOneData(IndexPageOneDataEntity indexPageOneDataEntity);
    }

    public void setFindIndexPageOneListener(IndexDataCallBack indexDataCallBack){
        this.indexDataCallBack=indexDataCallBack;
        findIndexPageOndeData();
    }
}
