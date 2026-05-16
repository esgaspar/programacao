package com.esgaspar.programacao.service;

import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.repository.RoleRepository;
import com.esgaspar.programacao.repository.UserRepository;
import com.esgaspar.programacao.util.CrawlerStatistics;
import com.esgaspar.programacao.util.HtmlCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class ReuniaoService {
    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthService authService;

    @SneakyThrows
    public UserDto find() {
        File crawlStorageBase = new File("src/main/resources/crawler4j");


        CrawlConfig htmlConfig = new CrawlConfig();
        htmlConfig.setCrawlStorageFolder(crawlStorageBase.getAbsolutePath());
        htmlConfig.setPolitenessDelay(300);
        htmlConfig.setUserAgentString("baeldung demo (https://github.com/yasserg/crawler4j/)");



// Configure storage folders and other configurations

        PageFetcher pageFetcherHtml = new PageFetcher(htmlConfig);

        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcherHtml);

        CrawlController htmlController
                = new CrawlController(htmlConfig, pageFetcherHtml, robotstxtServer);

// add seed URLs

//        htmlController.addSeed("https://wol.jw.org/pt/wol/meetings/r5/lp-t/2024/21");
        htmlController.addSeed("https://stackoverflow.com/questions/34427901/java-net-socketexception-caught-when-processing-request-connection-reset-by-p");

        CrawlerStatistics stats = new CrawlerStatistics();
        CrawlController.WebCrawlerFactory<HtmlCrawler> htmlFactory = () -> new HtmlCrawler(stats);


        htmlController.startNonBlocking(htmlFactory, 1);

        htmlController.waitUntilFinish();

       return null;
    }
}
