package InterviewQuestions.mapper;

import InterviewQuestions.model.Row;

public class RowMapper implements GenericMapper<Row, String> {
    @Override
    public Row map(String line) {
        return new Row(line);
    }
}
