package org.epm.tag.service;

import org.epm.common.service.AbstractService;
import org.epm.tag.model.TagDTO;
import org.epm.tag.model.TagEntity;
import org.epm.tag.model.TagMapper;
import org.epm.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService extends AbstractService<TagEntity, TagDTO> {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        super(tagMapper);
        this.tagRepository = tagRepository;
    }

    @Override
    public TagRepository getRepository() {
        return tagRepository;
    }

    @Override
    public String getEntityName() {
        return "Tag";
    }
}
