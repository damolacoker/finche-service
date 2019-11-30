package com.finche.db.resource;

import com.finche.db.handle.ProjectHandle;
import com.finche.db.model.Project;
import com.finche.db.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/project/")
public class ProjectResource {

        @Autowired
        private ProjectService projectService;

    @PostMapping(value = "/createproject", consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createProject(@Valid @RequestBody Project project){
        try {
            projectService.create(project);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping( value = "/getprojects" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getProjects(final Pageable paging){
        return projectService
                .findAll(paging)
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(ProjectHandle.handleGetProjectsFailure);
    }

    @GetMapping(value = "/{projectId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getProjectById(@PathVariable final String projectId) {
        return projectService
                .findOneById(projectId)
                .thenApply(ProjectHandle.mapMaybeProjectToResponse)
                .exceptionally(ProjectHandle.handleGetProjectFailure.apply(projectId));
    }

    @PutMapping(value = "/delete/{projectId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deletePropject(@PathVariable final String projectId){
        try {
            projectService.delete(projectId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
