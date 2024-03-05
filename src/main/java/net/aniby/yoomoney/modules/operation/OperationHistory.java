package net.aniby.yoomoney.modules.operation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OperationHistory {
    @SerializedName("error")
    private String error;
    @SerializedName("next_record")
    private String nextRecord;
    @SerializedName("operations")
    private JsonArray operations;

    public List<OperationDetails> getDetailedOperations() {
        List<OperationDetails> operations = new ArrayList<>();
        for (JsonElement operation : this.operations) {
            JsonObject object = operation.getAsJsonObject();
            operations.add(new Gson().fromJson(object, OperationDetails.class));
        }
        return operations;
    }

    public List<Operation> getOperations() {
        List<Operation> operations = new ArrayList<>();
        for (JsonElement operation : this.operations) {
            JsonObject object = operation.getAsJsonObject();
            operations.add(new Gson().fromJson(object, Operation.class));
        }
        return operations;
    }

    @Override
    public String toString() {
        return "OperationHistoryObject{" +
                "operations=" + operations +
                '}';
    }
}
