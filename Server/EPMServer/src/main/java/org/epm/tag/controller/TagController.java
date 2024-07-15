package org.epm.tag.controller;

import lombok.RequiredArgsConstructor;
import org.epm.common.controller.AbstractRestController;
import org.epm.tag.model.TagDTO;
import org.epm.tag.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController extends AbstractRestController<TagDTO> {

    private final TagService tagService;

    @Override
    public TagService getEntityService() {
        return tagService;
    }

}
