package hihi.log;

public enum IdType {

    LOG_TRACE_ID("log-trace-id");

    private final String headerKey;

    IdType(final String headerKey) {
        this.headerKey = headerKey;
    }

    public String getValue() {
        return headerKey;
    }
}
