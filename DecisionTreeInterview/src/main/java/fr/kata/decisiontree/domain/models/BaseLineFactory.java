package fr.kata.decisiontree.domain.models;

import java.util.regex.Matcher;

public class BaseLineFactory {
    public static BaseLine getBaseLine(BaseType baseType, Matcher matcher) {
        switch (baseType) {
            case PARENT:
                return buildParentLine(matcher);
            case LEAF:
                return buildLeafLine(matcher);
            default:
                return null;
        }
    }

    private static BaseLine buildParentLine(Matcher matcher) {
        Integer parentData = Integer.parseInt(matcher.group(1));
        Integer yes = Integer.parseInt(matcher.group(4));
        Integer no = Integer.parseInt(matcher.group(6));
        String feature = String.valueOf(matcher.group(2));

        return new ParentLine(parentData, yes, no, feature);
    }

    private static BaseLine buildLeafLine(Matcher matcher) {
        Integer leafData = Integer.parseInt(matcher.group(1));
        double element = Double.parseDouble(matcher.group(3));

        return new LeafLine(leafData, element);
    }
}
