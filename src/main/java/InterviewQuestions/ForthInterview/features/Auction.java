package InterviewQuestions.ForthInterview.features;

import InterviewQuestions.ForthInterview.beans.Parameters;
import InterviewQuestions.ForthInterview.beans.Winner;
import InterviewQuestions.ForthInterview.exceptions.ParameterException;
import InterviewQuestions.ForthInterview.services.IAlgo;
import InterviewQuestions.ForthInterview.services.IChecker;

public class Auction {
    private IChecker checker;
    private IAlgo algo;

    public Auction(IChecker checker, IAlgo algo) {
        this.checker = checker;
        this.algo = algo;
    }

    public Winner doCalcul(Parameters parameters) {
        try {
            checker.check(parameters);
        } catch (ParameterException e) {
            System.out.print(e.getMessage());
        }
        return algo.calculate(parameters);
    }
}
