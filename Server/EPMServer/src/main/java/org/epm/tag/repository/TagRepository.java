package org.epm.tag.repository;

import org.epm.common.repository.IRepository;
import org.epm.tag.model.TagEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends IRepository<TagEntity> {
}
