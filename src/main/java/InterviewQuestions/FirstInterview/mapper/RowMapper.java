package InterviewQuestions.FirstInterview.mapper;

import InterviewQuestions.FirstInterview.model.Row;

public class RowMapper implements GenericMapper<Row, String> {
    @Override
    public Row map(String line) {
        return new Row(line);
    }
}
