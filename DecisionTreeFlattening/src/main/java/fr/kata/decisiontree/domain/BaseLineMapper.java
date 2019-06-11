package fr.kata.decisiontree.domain;

import fr.kata.decisiontree.domain.models.BaseLine;
import fr.kata.decisiontree.domain.models.BaseLineFactory;
import fr.kata.decisiontree.domain.models.BaseType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseLineMapper {
    private static final String regexParent = "([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)";
    private static final String regexLeaf = "([0-9]+):(leaf)=([+-]?[1-9][0-9]*|0[.,][0-9]+)";
    private static Pattern patternParent = Pattern.compile(regexParent);
    private static Pattern patternLeaf = Pattern.compile(regexLeaf);

    public static BaseLine toBaseLine(String line) {
        Matcher matcherParent = patternParent.matcher(line);
        Matcher matcherLeaf = patternLeaf.matcher(line);
        BaseLine baseLine = null;

        if (matcherParent.find()) {
            baseLine = BaseLineFactory.getBaseLine(BaseType.PARENT, matcherParent);
        }

        if (matcherLeaf.find()) {
            baseLine = BaseLineFactory.getBaseLine(BaseType.LEAF, matcherLeaf);
        }

//        if(baseLine == null) throw new InvalidFileTreeFormat(line);

        return baseLine;
    }
}
