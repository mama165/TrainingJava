package InterviewQuestions.FirstInterview.features;

import InterviewQuestions.FirstInterview.models.Node;
import InterviewQuestions.FirstInterview.models.Rows;

public interface IMapper {
    Node build(Rows rows);
}