package InterviewQuestions.FirstInterview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BaseCondition {
    ArrayList<String> conditions = new ArrayList();

    public BaseCondition() {
    }

    public void addCondition(String condition) {
        conditions.add(condition);
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<String> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int length = conditions.size();
        IntStream.range(0, length - 1).forEach(index -> {
            if (index == length - 1) {
                sb.append(conditions.get(index));
            } else {
                sb.append(conditions.get(index)).append(" & ");
            }
        });

        // We return the BaseCondition rewritten
        return sb.toString();
    }
}
