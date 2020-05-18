package com.lixibing.majiangsp.majiang.provider;

import com.alibaba.fastjson.JSON;
import com.lixibing.majiangsp.majiang.dao.githubdao;
import com.lixibing.majiangsp.majiang.dao.githubuser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubPro {

    public String gettock(githubdao githu){
         MediaType metp = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(metp, JSON.toJSONString(githu));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
                String string =response.body().string();
                string = string.split("&")[0].split("=")[1];
                System.out.println(string);
                return string;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public githubuser getuser(String atock){
            OkHttpClient client = new OkHttpClient();


                Request request = new Request.Builder()
                        .url("https://api.github.com/user?access_token"+atock)
                        .build();

            try {
                Response  response = client.newCall(request).execute();
                String string =response.body().string();
                githubuser usert = JSON.parseObject(string,githubuser.class);
                return usert;
            } catch (IOException e) {
            }
            return null;

            }
 }



