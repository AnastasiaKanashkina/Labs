package Lab3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Crawler {
	// HTML href ���
	static final String HREF_TAG = "<a href=\"http";
	// ������ ���� ������������� ������ .
	static LinkedList<URLDepthPair> allSitesSeen = 
			new LinkedList<URLDepthPair>();
	// ������ ���� ��������������� ������
	static LinkedList<URLDepthPair> sitesToVisit = new LinkedList<URLDepthPair>();
	
	/**
	 * ����� ��������� ��������, ������� � ���������� ����� � �� ��������� �������
	 * @param startURL - ��������� URL
	 * @param maxDepth - ������������ ������� ������
	 * @throws MalformedURLException - ���� URL ������ �����������
	 */
	public static void crawl(String startURL, int maxDepth)
			throws MalformedURLException {
		
		URLDepthPair urlPair = new URLDepthPair(startURL, 0);
		sitesToVisit.add(urlPair);
		int depth;
		HashSet<String> seenURLs = new HashSet<String>();
		seenURLs.add(startURL);
		
		//���������� �� ��� ��� ���� �� ����� �������������� ��� ��������
		//�� ������������ ������� ������
		while (!sitesToVisit.isEmpty()) {
			URLDepthPair currPair = sitesToVisit.removeFirst();
			depth = currPair.getDepth();
			// ��������� ����������
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(currPair.getHost(), 80), 30000);
				socket.setSoTimeout(30000);
				System.out.println("�������������� ���������� � " + currPair.getURLString());
				PrintWriter output =
				        new PrintWriter(socket.getOutputStream(), true);
			    BufferedReader input =
			        new BufferedReader(
			            new InputStreamReader(socket.getInputStream()));
			    //�������� HTTP �������
			    output.println("GET " + currPair.getPath() + " HTTP/1.1");
			    output.println("Host: " + currPair.getHost());
			    output.println("Connection: close");
			    output.println();
			    output.flush();

			 // ���������� ������ �� ��������
			    String line;
			    int lineLength;
			    int shiftIdx;
			    while ((line = input.readLine()) != null) {
			    	// �������� ���� �� � ������ ������
				    boolean foundFullLink = false;
			    	int idx = line.indexOf(HREF_TAG);
			    	if (idx > 0) {
			    		// ��������� ������
			    		StringBuilder sb = new StringBuilder();
			    		shiftIdx = idx + 9;
			    		char c = line.charAt(shiftIdx);
			    		lineLength = line.length();
			    		while (c != '"' && shiftIdx < lineLength - 1) {
			    			sb.append(c);
			    			shiftIdx++;
			    			c = line.charAt(shiftIdx);
			    			if (c == '"') {
			    				foundFullLink = true;
			    			}
			    		}
				    		// �������� ����� ���� ��� ���� ������
			    			String newUrl = sb.toString();
			    			if (foundFullLink && depth < maxDepth && 
			    					!seenURLs.contains(newUrl)) {
			    				URLDepthPair newPair = 
					    				new URLDepthPair(newUrl, depth + 1);
					    		//���������� ����� ���� � ���
			    				sitesToVisit.add(newPair);
					    		seenURLs.add(newUrl);
			    			}
				    	}
			    	}	

			    // ������� �������� ��� ������������� � �������� ������.
			    socket.close();
			    allSitesSeen.add(currPair);
			}
			catch (IOException e) {
			}
		}
		// ������ ���� ��������� �������.
		for (URLDepthPair pair : allSitesSeen) {
			System.out.println(pair.toString());
		}
	}
	
	/**
	 * ���� ����� ��������� ��������, ������� � ��������� � args[0] �� ������������ �������
	 * arg[1]
	 **/
	public static void main(String[] args) throws MalformedURLException {
		if (args.length != 2) {
			System.out.println("usage: java Crawler <URL> <maximum_depth>");
			return;
		}
			
		String firstURL = args[0];
		int maxDepth = Integer.parseInt(args[1]);
		crawl(firstURL, maxDepth);	
	}
}
