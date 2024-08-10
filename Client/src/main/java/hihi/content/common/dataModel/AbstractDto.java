package hihi.content.common.dataModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public abstract class AbstractDto {

    protected Integer uid;
    protected String action;

}
