package test;
import java.net.URL;  
import java.util.List;  
  
import com.sun.syndication.feed.synd.SyndCategory;  
import com.sun.syndication.feed.synd.SyndContent;  
import com.sun.syndication.feed.synd.SyndEnclosure;  
import com.sun.syndication.feed.synd.SyndEntry;  
import com.sun.syndication.feed.synd.SyndFeed;  
import com.sun.syndication.io.SyndFeedInput;  
import com.sun.syndication.io.XmlReader;  
//http://zmfkplj.iteye.com/blog/461398  
public class TestParse {  
    public static void main(String[] args) {  
        TestParse test = new TestParse();  
        test.parseRss();  
    }  
    public void parseRss() {  
        String rss = "http://hqxx.fxedu.cn/info/rss.jsp?cat_id=11352&tm_id=406&cur_page=2";  
       // String rss = "http://rss.sina.com.cn/ent/hot_roll.xml";   
        try {  
            URL url = new URL(rss);  
            // ��ȡRssԴ     
            XmlReader reader = new XmlReader(url);        
            System.out.println("RssԴ�ı����ʽΪ��" + reader.getEncoding());  
            SyndFeedInput input = new SyndFeedInput();  
            // �õ�SyndFeed���󣬼��õ�RssԴ���������Ϣ     
            SyndFeed feed = input.build(reader);  
            //System.out.println(feed);           
            // �õ�Rss�����������б�     
            List entries = feed.getEntries();  
            // ѭ���õ�ÿ��������Ϣ     
            for (int i = 0; i < entries.size(); i++) {  
                SyndEntry entry = (SyndEntry) entries.get(i);                                 
                // ���⡢���ӵ�ַ�������顢ʱ����һ��RssԴ�����������ɲ���     
                System.out.println("���⣺" + entry.getTitle());  
                System.out.println("���ӵ�ַ��" + entry.getLink());  
                SyndContent description = entry.getDescription();  
                System.out.println("�����飺" + description.getValue());  
                System.out.println("����ʱ�䣺" + entry.getPublishedDate());                               
                // ������RssԴ���ȵļ�������     
                System.out.println("��������ߣ�" + entry.getAuthor());                 
                // �˱��������ķ���     
                List categoryList = entry.getCategories();  
                if (categoryList != null) {  
                    for (int m = 0; m < categoryList.size(); m++) {  
                        SyndCategory category = (SyndCategory) categoryList.get(m);  
                        System.out.println("�˱��������ķ��룺" + category.getName());  
                    }  
                }                             
                // �õ���ý�岥���ļ�����Ϣ�б�     
                List enclosureList = entry.getEnclosures();  
                if (enclosureList != null) {  
                    for (int n = 0; n < enclosureList.size(); n++) {  
                        SyndEnclosure enclosure = (SyndEnclosure) enclosureList.get(n);  
                        System.out.println("��ý�岥���ļ���" + entry.getEnclosures());  
                    }  
                }  
                System.out.println();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}