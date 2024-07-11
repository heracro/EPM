package org.epm.tag.controller;

import org.epm.common.controller.AbstractEntityController;
import org.epm.tag.model.TagDTO;
import org.epm.tag.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{projectId}/tags")
public class TagController extends AbstractEntityController<TagDTO> {

    public TagController(TagService tagService) {
        super(tagService);
    }

    @Override
    public String getMapping() {
        return "/api/projects/{projectId}/tags";
    }
}
