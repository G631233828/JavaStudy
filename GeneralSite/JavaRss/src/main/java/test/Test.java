package test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Test {
	
	
	 public static void main(String[] args) {
	        try {
	            parseXml(new URL("http://tgw.qpedu.cn/info/rss.jsp?cat_id=10068&tm_id=321&cur_page=1&size=0"));
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	
	
	
	
	

	  private static void parseString(String xml) throws Exception {
		    
	        SyndFeedInput input = new SyndFeedInput();
	        SyndFeed feed = null;
	        ByteArrayInputStream inputStream=new ByteArrayInputStream(xml.getBytes("UTF-8"));
	        feed = input.build(new XmlReader(inputStream));
	        List entries = feed.getEntries();// 得到所有的标题<title></title>
	        for (int i = 0; i < entries.size(); i++) {
	            SyndEntry entry = (SyndEntry) entries.get(i);
	            System.out.println(entry.getTitle());
	            System.out.println(entry.getLink());
	            System.out.println(entry.getDescription());
	            System.out.println("-----------------------------------------------");
	            
	        }
	        System.out.println("feed size:" + feed.getEntries().size());
	    }

	    /**
	     * 根据链接地址得到数据
	     */
	    public static void parseXml(URL url) throws IllegalArgumentException,
	            FeedException {
	        try {
	            SyndFeedInput input = new SyndFeedInput();
	            SyndFeed feed = null;
	            URLConnection conn;
	            conn = url.openConnection();
	            String content_encoding = conn.getHeaderField("Content-Encoding");
	            if (content_encoding != null && content_encoding.contains("gzip")) {
	                System.out.println("conent encoding is gzip");
	                GZIPInputStream gzin = new GZIPInputStream(
	                        conn.getInputStream());
	                feed = input.build(new XmlReader(gzin));
	            } else {
	                feed = input.build(new XmlReader(conn.getInputStream()));
	            }

	            List entries = feed.getEntries();// 得到所有的标题<title></title>
	            for (int i = 0; i < entries.size(); i++) {
	                SyndEntry entry = (SyndEntry) entries.get(i);
	                System.out.println(entry.getTitle());
	                System.out.println(entry.getLink());
	                System.out.println(entry.getDescription());
		            System.out.println("-----------------------------------------------");
	            }
	            System.out.println("feed size:" + feed.getEntries().size());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	}
	
