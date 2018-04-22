package com.daniel.newsreader;

import android.content.Context;
import android.util.Log;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FileIO {

    //constants for URL and file name
    private final String URL_STRING = "http://rss.cnn.com/rss/cnn_tech.rss";
    private final String FILENAME = "news_feed.xml";
    private Context context = null;

    public FileIO(Context context) {this.context = context; }

    public void downloadFile(){

        try {
            //get URL
            URL url = new URL(URL_STRING);

            InputStream in = url.openStream();

            //get output string
            FileOutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            //read input and write output
            byte[] buffer = new byte[1024];
            int bytesRead =in.read(buffer);

            while(bytesRead != -1){
                out.write(buffer, 0, bytesRead);
                bytesRead = in.read(buffer);
            }
            out.close();
            in.close();

        } catch (IOException e) {
            Log.e("News Reader", e.toString());
        }
    }

    public RSSFeed readFile(){

        try{
            //get XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance().newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();

            //set content handler
            RSSFeedHandler theRSSHandler = new RSSFeedHandler();
            xmlReader.setContentHandler((ContentHandler) theRSSHandler);

            //read file from internal storage
            FileInputStream in = context.openFileInput(FILENAME);

            //parse data
            InputSource is = new InputSource(in);
            xmlReader.parse(is);

            RSSFeed feed = theRSSHandler.getFeed();
            return feed;

        } catch(Exception e){
            Log.e("News Reader", e.toString());
            return null;
        }
    }
}
