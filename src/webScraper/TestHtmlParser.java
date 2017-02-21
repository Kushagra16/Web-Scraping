package pitchbook.webScraper;

import static org.junit.Assert.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class TestHtmlParser 
{

	
	@Test
	public void nullUrl() throws IOException
	{
		HtmlParse ht = new HtmlParse(null,"output.txt");
		String s = ht.checkValid();
		assertEquals("Not Valid", s);
	}
	
	
	@Test
	public void invalidUrl() throws IOException
	{
		HtmlParse ht = new HtmlParse("smtp://pitchbook.com","output.txt");
		String s = ht.checkValid();
		assertEquals("No http:// or https://", s);
	}
	
	
	@Test
	public void validUrl() throws IOException
	{
		HtmlParse ht = new HtmlParse("pitchbook.com","output.txt");
		String s = ht.checkValid();
		assertEquals("http://pitchbook.com", s);
	}
	
	
	@Test
	public void testLinks() throws IOException
	{
		HtmlParse ht = new HtmlParse("http://pitchbook.com/about-pitchbook","output.txt");
		String html = ht.parsing();
		Document doc = Jsoup.parse(html);
		String s = ht.extractLinks(doc).get(0);
		assertEquals("/favicon.ico?uq=nRIsDrOQ", s);
	}
	
	
	@Test
	public void testTags() throws IOException
	{
		HtmlParse ht = new HtmlParse("http://pitchbook.com/about-pitchbook","output.txt");
		String html = ht.parsing();
		
		// For string html, character 14 - 20 = "<html>"
		
		assertEquals("<html>", ht.extractTags(html).substring(14,20));
	}
	
	
	@Test
	public void testSequences() throws IOException
	{
		HtmlParse ht = new HtmlParse("http://pitchbook.com/about-pitchbook","output.txt");
		String html = ht.parsing();
		Document doc = Jsoup.parse(html);
		String s = ht.extractSequences(doc).get(0);
		assertEquals("About PitchBook", s);
	}
}
