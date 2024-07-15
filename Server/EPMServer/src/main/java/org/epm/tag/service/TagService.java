package org.epm.tag.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractService;
import org.epm.tag.model.TagDTO;
import org.epm.tag.model.TagEntity;
import org.epm.tag.model.TagMapper;
import org.epm.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService extends AbstractService<TagEntity, TagDTO> {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagMapper getMapper() {
        return tagMapper;
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
