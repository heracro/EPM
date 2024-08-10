package hihi.event;

import hihi.content.common.dataModel.AbstractContent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ContentSavedEvent<T extends AbstractContent> extends ApplicationEvent {

    private final T content;

    public ContentSavedEvent(Object source, T content) {
        super(source);
        this.content = content;
    }
}
