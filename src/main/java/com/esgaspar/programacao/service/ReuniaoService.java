package com.esgaspar.programacao.service;

import com.esgaspar.programacao.util.CrawlerStatistics;
import com.esgaspar.programacao.util.HtmlCrawler;
import org.springframework.stereotype.Service;

@Service
public class ReuniaoService {

    private static final String SEED_URL = "https://wol.jw.org/pt/wol/meetings/r5/lp-t/2024/21";

    public void crawl() {
        var stats = new CrawlerStatistics();
        var crawler = new HtmlCrawler(stats);
        if (crawler.shouldVisit(SEED_URL)) {
            crawler.visit(SEED_URL);
        }
    }
}
