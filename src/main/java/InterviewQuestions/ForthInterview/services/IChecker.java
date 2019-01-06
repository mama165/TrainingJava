package InterviewQuestions.ForthInterview.services;

import InterviewQuestions.ForthInterview.beans.Parameters;
import InterviewQuestions.ForthInterview.exceptions.ParameterException;

public interface IChecker {
    void check(Parameters parameters) throws ParameterException;
}
