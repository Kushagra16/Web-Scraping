package pitchbook.webScraper;

import java.io.*;
import java.util.*;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParse 
{
	String url;
	List<String> links;
	String tags;
	List<String> sequences;
	String output;
	
	// constructor for instantiation
	 
	public HtmlParse(String url, String output) 
	{
		this.url = url;
		this.output = output;
		links = new ArrayList<String>();
		sequences = new ArrayList<String>();
	}
	
	// Parsing the html and writing it in output.txt
	 
	public void parse() throws IOException
	{
		String html = parsing();
		Document doc = Jsoup.parse(html);
		extractLinks(doc);
		extractTags(html);
		extractSequences(doc);
		outputFile();
	}

	public String parsing() throws IOException
	{
		checkValid();
		String line = null, st = null;
	    URL myUrl = null;
	    BufferedReader in = null;
	    try 
	    {
	        myUrl = new URL(url);
	        in = new BufferedReader(new InputStreamReader(myUrl.openStream()));
	        while ((line = in.readLine()) != null) 
	        {
	            st = st + line;
	        }
	    } 
	    finally 
	    {
	        if (in != null) 
	        {
	            in.close();
	        }
	    }
	    return st;
	}
	
	// Checking the validity of url
	 
	public String checkValid()
	{
		if(url == null) 
			return "Not Valid";
		if(!url.startsWith("http"))
		{
			if(url.contains("//"))
				return "No http:// or https://";
			url = "http://" + url;
		}
		return url;
	}
	
	// Extracting list of all links​ embedded within the HTML page
	 
	public List<String> extractLinks(Document doc) 
	{
		Elements link = doc.select("link");
		for(Element e: link )
		{
			links.add(e.attr("href"));
		}
		Elements anchor = doc.select("a");
		for(Element e: anchor )
		{
			links.add(e.attr("href"));
		}
		return links;
	}

	// Extracting set of raw HTML tags
	 
	public String extractTags(String html) 
	{
		String sr = null;
		while(true)
		{
				int start = html.indexOf("<");
				int end = html.indexOf(">", start);
				if(end == -1) break;
				int endSpace = html.indexOf(" ", start);
				if(endSpace < end && endSpace > -1)
				{
					sr = sr + html.substring(html.indexOf("<"), endSpace);
				}
				else if(end > -1)
				{
					sr = sr + html.substring(html.indexOf("<"), end);
				}
				if(html.charAt(end-1) == '/') sr = sr + "/";
				sr = sr + ">";
				html = html.substring(end + 1);
				if(html.indexOf("<") == -1) break;
		}
		tags = sr;
		return tags;
	}
	
	// All sequences​ of two or more words that have the first letter in each word capitalized
	
	public List<String> extractSequences(Document doc) 
	{
		String si = null;
		for (Element e:doc.select("*")) 
		{
			String text = e.ownText();
			if(text.isEmpty()) continue;
			String[] words = text.split(" ");
		    int count = 0;
			for(int i = 0; i < words.length; i++)
			{
				if(words[i].matches("[A-Z][A-Za-z]+"))
				{
					si = si + words[i] + " ";
					count++;
				}
				else if(words[i].matches("[A-Z][A-Za-z]+,") && count > 0)
				{
					si = si + words[i].substring(0, words[i].length()-1);
					sequences.add(si.trim());
					si = "";
					count=0;
				}
				else if(count > 1)	
				{
						sequences.add(si.trim());
						count = 0;
						si ="";
				}
				else 
				{
					si ="";
					count = 0;
				}
			 }
			if(si.length() != 0 && count > 1) 	
				sequences.add(si.trim());
		}
		return sequences;
	}
	
	// Writing to output.txt
	 
	public void outputFile() throws IOException 
	{
		BufferedWriter bw = null;
		FileWriter fw = null;
		if(output == null || output.isEmpty()) output = "output.txt";
		try {
			fw = new FileWriter(output, true);
			bw = new BufferedWriter(fw);
			bw.write("[links]");
			bw.newLine();
			for(String l: links)
			{
				bw.write(l);
				bw.newLine();
			}
			bw.write("[HTML]");
			bw.newLine();
			bw.write(tags);
			bw.newLine();
			bw.write("[sequences]");
			bw.newLine();
			for(String l: sequences)
			{
				bw.write(l);
				bw.newLine();
			}
			bw.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
				if(fw != null) fw.close();
				if(bw != null) bw.close();
		}
	}
	
	// Main method
	
	public static void main(String[] args) throws Exception
	{
		/* 
		  To run the code, use Run Configuration -> Arguments . In the Program arguments, type the 2 inputs as String.
		  Eg. "http://pitchbook.com/about-pitchbook"  "output.txt"
        */
		
		HtmlParse pa = new HtmlParse(args[0], args[1]);
		pa.parse();		
	}

}
