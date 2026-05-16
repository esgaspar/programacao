package com.esgaspar.programacao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerStatistics {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerStatistics.class);

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
        this.title = title;
        logger.debug("stats: {}", title);
    }

    public String getTitle() {
        return title;
    }
}
