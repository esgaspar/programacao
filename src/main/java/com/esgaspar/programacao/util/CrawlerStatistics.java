package com.esgaspar.programacao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrawlerStatistics {
    static final Logger logger = LogManager.getLogger(HtmlCrawler.class.getName());

    private int processedPageCount = 0;
    private int totalLinksCount = 0;
    private String title = "";

    public void incrementProcessedPageCount() {
        processedPageCount++;
    }

    public void incrementTotalLinksCount(int linksCount) {
        totalLinksCount += linksCount;
    }

    public void showTitle(String title) {
        title = title;
        logger.debug("stats: " + title);
    }

}
