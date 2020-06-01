package Lab3;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
	// ����������� URL 
	URL url;
	// ����������� ������� ������ 
	int depth;
	// ������� URL 
	public static final String URL_PREFIX = "http://";
	
	// ������� �����������
	public URLDepthPair(String url, int depth) throws MalformedURLException {
		this.url = new URL(url);
		this.depth = depth;
	}
	
	// ���������� ��������� ������������� url � �������
	public String toString(){
		String out = url + "\t" + depth;
		return out;
	}
	
	// ���������� hostname
	public String getHost() {
		return url.getHost();
	}
	
	// ���������� ����
	public String getPath() {
			return url.getPath();
		}
	
	// ���������� ������� ������
	public int getDepth() {
		return depth;
	}
	
	// ���������� url
		public String getURLString() {
			return url.toString();
		}
	
}
