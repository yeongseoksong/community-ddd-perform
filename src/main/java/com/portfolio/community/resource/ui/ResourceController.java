package com.portfolio.community.resource.ui;

import com.portfolio.community.common.response.Resp;
import com.portfolio.community.resource.application.DeleteResourceSerivce;
import com.portfolio.community.resource.application.LoadResourceService;
import com.portfolio.community.resource.application.PersistResourceService;
import com.portfolio.community.resource.domain.Image;
import com.portfolio.community.resource.domain.Resource;
import com.portfolio.community.resource.domain.ResourceId;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
    public Resp<Resource> saveResource(@RequestParam("upload") MultipartFile upload,
                                       @RequestParam("postId")  Long postId){
        Resource resource = persistResourceService.persistMultipartFile(upload);
        return Resp.ok(resource);
    }




    @GetMapping("/{resourceId}")
    public void loadResource(@PathVariable String resourceId, HttpServletResponse response){
        Resource resource = loadResourceService.loadResource(new ResourceId(resourceId));

        File file = new File (resource.getPath());
        if (!file.exists())
            throw new IllegalArgumentException("File not found ");

        String filename = resource.getFileName();
        String encodeFileName = URLEncoder.encode(filename, StandardCharsets.UTF_8);


        if(resource instanceof Image){
            response.setHeader("Content-Disposition", "inline");}
        else {
            response.setHeader("Content-Disposition", "attachment; filename=" + encodeFileName);
        }

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
