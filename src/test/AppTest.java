import CrackingTheCodingInterview.LinkedList.Node;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/*
Test cases :
 * 
 * INPUT : 4 5 6 7 5 2 6 4 5
 * EXPECTED OUTPUT : 4 5 6 7 2
 * 
 * string input = displayNodes(list);
 * print :  4 5 6 7 5 2 6 4 5
 * 
 * method list.RemoveDips
 * 
 * string output = displayNodes(list);
 * 
 * ASSERT
 * est ce que output == expectedOutput
*/

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * 
     * https://stackoverflow.com/questions/4554128/is-there-a-junit-equivalent-to-nunits-testcase-attribute*
     *   */
    public AppTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
}
