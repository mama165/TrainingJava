package InterviewQuestions.FirstInterview;

import java.util.List;

public class Condition {
    List<Condition> conditions;

    class ConditionBuilder {
        public Condition build(String feature, boolean state) {
            // We get one condition from the feature
            return new Condition();
        }
    }
}
