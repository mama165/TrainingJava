package InterviewQuestions.FirstInterview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BaseCondition {
    ArrayList<Condition> conditions = new ArrayList();
    ArrayList<String> list = new ArrayList();

    public BaseCondition() {
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        IntStream.range(0, list.size() - 1)
                .forEach(index -> {
                    if (index == list.size()) {
                        sb.append(list.get(index));
                    } else {
                        sb.append(list.get(index)).append(" & ");
                    }
                });


        // We return the BaseCondition rewritten
        return sb.toString();
    }
}
