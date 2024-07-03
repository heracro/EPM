package org.epm.project;

import org.epm.bom.model.Bom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.epm.project.model.Project;
import org.epm.project.model.ProjectSearchCriteria;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/createProject")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        return null;
    }

    @GetMapping("/findProject/{id}")
    public ResponseEntity<?> findProject(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/findProjects")
    public ResponseEntity<?> findProjects(@RequestBody ProjectSearchCriteria criteria) {
        return null;
    }

    @PutMapping("/updateProject/{id}")
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        return null;
    }

    @DeleteMapping("/deleteProject")
    public ResponseEntity<?> deleteProject(@RequestBody Project project) {
        return null;
    }

    @PostMapping("/createBom")
    public ResponseEntity<?> createBom(@RequestBody Bom bom) {
        return null;
    }

    @PostMapping("/createBomList")
    public ResponseEntity<?> createBomList(@RequestBody List<Bom> bomList) {
        return null;
    }

    @PutMapping("/updateBom")
    public ResponseEntity<?> updateBom(@RequestBody Bom bom) {
        return null;
    }

    @DeleteMapping("/deleteBom")
    public ResponseEntity<?> deleteBom(@RequestBody Bom bom) {
        return null;
    }

    @DeleteMapping("/deleteAllBoms")
    public ResponseEntity<?> deleteAllBoms(@RequestBody Project project) {
        return null;
    }

    @PatchMapping("/startProject")
    public ResponseEntity<?> startProject(@RequestParam Long id) {
        return null;
    }

    @PatchMapping("/stopProject")
    public ResponseEntity<?> stopProject(@RequestParam Long id) {
        return null;
    }

    @GetMapping("/checkProject")
    public ResponseEntity<?> checkProject(@RequestParam Long id) {
        return null;
    }

    @PostMapping("/projectReport")
    public ResponseEntity<?> buildProjectReport(@RequestBody List<Project> projectList) {
        return null;
    }
}




