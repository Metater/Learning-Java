package com.github.metater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.System;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {

    public static void main(String[] args) {
	// write your code here
    System.out.println(GetDataFromURL("https://example.com"));
    }

    private static String GetDataFromURL(String url)
    {
        String data = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                    String line;
                    while((line = reader.readLine()) != null) {
                        data +=  line + "\n";
                    }
                } catch (IOException ex) {
                    throw ex;
                } catch (RuntimeException ex) {
                    httpGet.abort();
                    throw ex;
                } finally {
                    instream.close();
                }
            }
        } catch (Exception e)
        {
            data =  e.toString();
        }
        return data;
    }
}
