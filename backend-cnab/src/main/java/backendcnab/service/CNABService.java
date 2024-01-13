package backendcnab.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CNABService {

    private final Path fileStorageLocation;
    private final Job job;
    private final JobLauncher launcher;

    public CNABService(@Value("${file.upload-dir}") String fileUploadDir,
                       Job job,
                       @Qualifier("jobLauncherAsync") JobLauncher launcher) {

        this.fileStorageLocation = Paths.get(fileUploadDir);
        this.launcher = launcher;
        this.job = job;
    }

    public void uploadCNABFile(MultipartFile file) throws Exception{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = fileStorageLocation.resolve(fileName);
        file.transferTo(targetLocation);

        var jobParameters = new JobParametersBuilder()
            .addJobParameter("cnab", file.getOriginalFilename(), String .class, true)
            .addJobParameter("cnabFile", "file:" + targetLocation.toString(), String.class, false)
            .toJobParameters();

        launcher.run(job, jobParameters);
    }
}
