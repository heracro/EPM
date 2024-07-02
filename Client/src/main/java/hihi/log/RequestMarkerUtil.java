package hihi.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

@Slf4j
public class RequestMarkerUtil {

    /**
     * Adds generated id to mdc map for future tracing
     * @param id id to trace
     * @param idType id type
     */
    public static void setId(final String id, final IdType idType) {
        MDC.put(idType.getValue(), id);
        log.debug("Set: {}: {}", idType, getId(idType));
    }

    /**
     * Generates if of specified type and adds it to mdc map
     * @param idType id type
     */
    public static void generateAndSet(final IdType idType) {
        final var id = UUID.randomUUID().toString().toUpperCase().replace("-","");
        setId(id, idType);
    }

    /**
     * Gets traced id of specified type from MDC map
     * @param idType type of ID to get
     * @return traced id of specified type
     */
    public static String getId(final IdType idType) {
        return MDC.get(idType.getValue());
    }

    /**
     * Removes id of specified type from MDC map
     * @param idType type of id to remove
     */
    public static void removeId(final IdType idType) {
        log.debug("remove {}: {}", idType, getId(idType));
        MDC.remove(idType.getValue());
    }

    public static void removeAllIds() {
        for (final IdType idType : IdType.values()) {
            removeId(idType);
        }
    }

/*
public static String getTraceId(final Klasa obiekt) {
    return jakis_string_z_obiektu_ktory_moze_robic_za_trace_id;
}

public static void addSuffixToId(final String originId, final String suffix, final IdType idType) {
    RequestMarkerUtil.setId(originId + "-" + suffix, idType);
}
 */
}
