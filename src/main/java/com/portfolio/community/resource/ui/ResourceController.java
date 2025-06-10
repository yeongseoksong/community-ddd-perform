package com.portfolio.community.resource.ui;

import com.portfolio.community.common.response.Resp;
import com.portfolio.community.resource.application.DeleteResourceSerivce;
import com.portfolio.community.resource.application.LoadResourceService;
import com.portfolio.community.resource.application.PersistResourceService;
import com.portfolio.community.resource.domain.Resource;
import com.portfolio.community.resource.domain.ResourceId;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resources")
public class ResourceController {

    private final DeleteResourceSerivce deleteResourceSerivce;
    private final LoadResourceService loadResourceService;
    private final PersistResourceService persistResourceService;

    @PostMapping()
    public Resp<Resource> saveResource(MultipartFile multipartFile){
        Resource resource = persistResourceService.persistMultipartFile(multipartFile);
        return Resp.ok(resource);
    }

    @PostMapping("/{resourceId}")
    public void loadResource(@PathVariable ResourceId resourceId, HttpServletResponse response) throws IOException {
        Resource resource = loadResourceService.loadResource(resourceId);

        File file = new File (resource.getPath());

        Path path =  Paths.get(file.getPath());
        if (!file.exists())
            throw new IllegalArgumentException("File not found ");
        String filename = resource.getFileName();

        String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20"); // 공백 처리

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(Files.probeContentType(path),
                "filename=" + encodedFilename);

        try (InputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


}
