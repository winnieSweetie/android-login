package com.example.hello;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PaChong {

    private String url;
    private Document doc;

    public PaChong(String url) throws IOException {
        this.url = url;
        doc = Jsoup.connect(url).get();
    }

    public String getImgSrcs() throws IOException
    {
        Elements imgs = doc.select("img[src]");
        String all = "";
        for(Element ele: imgs)
        {
            String src = ele.attr("src");
            all += "\n" + src;
        }
        return all;
    }
    public void printImgs()
    {
        Elements imgs = doc.select("img[src]");
        String title;
        String src;
        for(Element ele: imgs)
        {
            title = ele.text();
            src = ele.attr("abs:src");
            System.out.println("image title: "+ title + "image src " + src);
        }

    }
}
