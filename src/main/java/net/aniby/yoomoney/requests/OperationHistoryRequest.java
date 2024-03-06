package net.aniby.yoomoney.requests;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OperationHistoryRequest extends MethodRequest {
    @BodyRequestField("type")
    private String type;
    @BodyRequestField("label")
    private String label;
    @BodyRequestField("from")
    private String from;
    @BodyRequestField("till")
    private String till;
    @BodyRequestField("start_record")
    private int startRecord;
    @BodyRequestField("records")
    private int records;
    @BodyRequestField("details")
    private boolean details;
}