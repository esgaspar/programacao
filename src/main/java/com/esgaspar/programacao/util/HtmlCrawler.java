package com.esgaspar.programacao.util;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class HtmlCrawler extends WebCrawler {
    static final Logger logger = LogManager.getLogger(HtmlCrawler.class.getName());

    private final static Pattern EXCLUSIONS
            = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");

    private CrawlerStatistics stats;

    public HtmlCrawler(CrawlerStatistics stats) {
        this.stats = stats;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        logger.debug("url: "+url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            logger.debug("title: "+title);
//            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            stats.showTitle(title);

            // do something with collected data
        }
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String urlString = url.getURL().toLowerCase();
        return !EXCLUSIONS.matcher(urlString).matches()
                && (urlString.startsWith("https://wol.jw.org/") || urlString.startsWith("https://jw.org/"));
    }


}
