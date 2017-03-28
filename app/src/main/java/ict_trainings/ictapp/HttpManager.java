package ict_trainings.ictapp;

import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by FaiZi bUtt on 8/8/2016.
 */
public class HttpManager {
    // TODO: 3/20/2017 make a static function passing a string
    public static String getData(String uri){
        // make a BUFF to store the incoming data
        BufferedReader bufferedReader = null;
        try{
            // create an object of class URL and pass string from static function namely getData(String uri)
            URL url = new URL(uri);
            // create an object of okhttp library okhttpClient with no argument constructor
            OkHttpClient okHttpClient = new OkHttpClient();
            // create object of httpurlConnection and do polymorphism and pass okhttpClient function open passing an argument of
            // URL that was assigned after a call to buff
            HttpURLConnection httpURLConnection = okHttpClient.open(url);
            // create stringBuilder object to save retrived data from url
            StringBuilder sb = new StringBuilder();
            // declare one argument constructor of buf (create inputStreamReader class anonymus object passing httpurlConnection
            // object and getting function namely inputStream
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            // create a string to hold stringbuilder objct
            String line;
            // retrive data using while loop and saving buff data using function readLine giving condition not null and saving
            // it ultimately to string object
            while((line = bufferedReader.readLine()) != null){
                // using sb function append to save data and giving next line keyword to move it to next line
                sb.append(line).append("\n");
            }
            // at last give sb to string using toString function
            return sb.toString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        } finally
        {
            // if it's not null then give buff close in finally
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
    }
}

