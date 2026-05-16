package com.esgaspar.programacao.service;

import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.repository.RoleRepository;
import com.esgaspar.programacao.repository.UserRepository;
import com.esgaspar.programacao.util.CrawlerStatistics;
import com.esgaspar.programacao.util.HtmlCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReuniaoService {
    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthService authService;

    public UserDto find() {
        CrawlerStatistics stats = new CrawlerStatistics();
        HtmlCrawler crawler = new HtmlCrawler(stats);

        String seedUrl = "https://wol.jw.org/pt/wol/meetings/r5/lp-t/2024/21";

        if (crawler.shouldVisit(seedUrl)) {
            crawler.visit(seedUrl);
        }

        return null;
    }
}
