package backendcnab.controller;

import backendcnab.service.CNABService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cnab")
public class CNABController {

    private final CNABService service;

    public CNABController(CNABService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception{
        service.uploadCNABFile(file);
        return "Processamento iniciado";
    }
}
