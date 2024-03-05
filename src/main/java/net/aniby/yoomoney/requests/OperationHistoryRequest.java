package net.aniby.yoomoney.requests;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OperationHistoryRequest extends MethodRequest {
    @BodyRequestField(name = "type")
    private String type;
    @BodyRequestField(name = "label")
    private String label;
    @BodyRequestField(name = "from")
    private String from;
    @BodyRequestField(name = "till")
    private String till;
    @BodyRequestField(name = "start_record")
    private int startRecord;
    @BodyRequestField(name = "records")
    private int records;
    @BodyRequestField(name = "details")
    private boolean details;
}