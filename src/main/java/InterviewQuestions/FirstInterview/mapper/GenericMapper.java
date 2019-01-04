package InterviewQuestions.FirstInterview.mapper;

public interface GenericMapper<T, U> {
    T map(U value);
}
