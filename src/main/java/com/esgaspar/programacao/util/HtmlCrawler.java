package com.esgaspar.programacao.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Pattern;

public class HtmlCrawler {
    private static final Logger logger = LoggerFactory.getLogger(HtmlCrawler.class);

    private static final Pattern EXCLUSIONS =
            Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");

    private static final String USER_AGENT = "Mozilla/5.0 (compatible; programacao-crawler/1.0)";
    private static final int TIMEOUT_MS = 10_000;

    private final CrawlerStatistics stats;

    public HtmlCrawler(CrawlerStatistics stats) {
        this.stats = stats;
    }

    public boolean shouldVisit(String url) {
        String lower = url.toLowerCase();
        return !EXCLUSIONS.matcher(lower).matches()
                && (lower.startsWith("https://wol.jw.org/") || lower.startsWith("https://jw.org/"));
    }

    public void visit(String url) {
        logger.debug("url: {}", url);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT_MS)
                    .get();
            String title = doc.title();
            logger.debug("title: {}", title);
            stats.showTitle(title);
        } catch (IOException e) {
            logger.warn("Erro ao acessar {}: {}", url, e.getMessage());
        }
    }
}
