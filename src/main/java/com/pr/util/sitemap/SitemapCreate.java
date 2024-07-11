package com.pr.util.sitemap;

import com.pr.board.domain.Board;
import com.pr.board.repository.BoardRepository;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SitemapCreate {
    private final BoardRepository boardRepository;

    private final ServletContext servletContext;

    //@Scheduled(cron = "0 0 3 * * ?") // 매일 새벽 3시에 실행
    @Scheduled(cron = "0 02 0 * * ?")
    public void generateSitemap() {
        try {
            // 실제 sitemap 생성 로직
            List<Board> boards = boardRepository.findAll();

            // 절대 경로 얻기
            String rootPath = servletContext.getRealPath("/");
            String filePath = rootPath + "sitemap.xml";

            // 파일로 sitemap을 생성
            File sitemapFile = new File(filePath);
            PrintWriter out = new PrintWriter(new FileWriter(sitemapFile));

            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

            for (Board board : boards) {
                out.println("<url>");
                out.println("<loc>https://dongga.net/Board/" + board.getId() + "</loc>"); // 예시 URL
                out.println("<changefreq>daily</changefreq>"); // 변경 빈도 설정 (예: 매일 변경)
                out.println("</url>");
            }

            out.println("</urlset>");

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}