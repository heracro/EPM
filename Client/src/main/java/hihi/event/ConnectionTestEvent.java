package hihi.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ConnectionTestEvent {

    private final boolean connectionStatus;

}
