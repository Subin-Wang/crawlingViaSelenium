package net.selenium.crawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
	public static Queue<String> queue = new LinkedList<String>();
	public static Set<String> marked = new HashSet<String>();
	public static String regex = "http[s]*://(\\w+\\.)*(\\w+)";
	
	public static void bfsAlgoritm(String root) throws IOException{
		queue.add(root);
		
		while(!queue.isEmpty()) {
			String crawledUrl = queue.poll();
			System.out.println("\n=== Site crawled : " + crawledUrl + " ===");
			//limit the amount of webs
			if(marked.size() > 100) return;	
			
			boolean ok = false;
			URL url =null;
			BufferedReader br = null;
			
			while(!ok) {
				try {
					url = new URL(crawledUrl);
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					ok=true;
					
					StringBuilder sb = new StringBuilder();
					String tmp = null;
					
					while((tmp=br.readLine())!=null) {
						sb.append(tmp);
					}
					
					tmp = sb.toString();
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(tmp);
					
					while(matcher.find()) {
						String w = matcher.group();
						
						if(!marked.contains(w)) {
							marked.add(w);
							System.out.println("Sited added for crawling : " + w);
							queue.add(w);
						}
					}
			
				}catch(MalformedURLException e) {
					System.out.println("*** IOExceiption for URL : " + crawledUrl);
					crawledUrl = queue.poll();
					ok=false;
				} finally {
					if(br != null) {
						br.close();
					}
				}
			}
			
			
		}
		
		
	}
	public static void showResults() {
		System.out.println("\n\n Results : ");
		System.out.println("Web site crawled : " + marked.size()+"\n");
		
		for(String s : marked) {
			System.out.println("* " + s);
		}
	}
	
	public static void main(String[] args) {
		try {
			bfsAlgoritm("http://www.ssaurel.com");
			showResults();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
