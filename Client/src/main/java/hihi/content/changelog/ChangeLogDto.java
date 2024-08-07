package hihi.content.changelog;

import hihi.content.common.AbstractDto;
import hihi.content.project.ProjectDto;
import hihi.content.enums.ChangeLogSource;
import hihi.content.enums.ChangeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChangeLogDto extends AbstractDto {

    private ProjectDto project;
    private LocalDateTime timestamp;
    private ChangeLogSource source;
    private ChangeType changeType;
    private String description;
    private String author;

}
