package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.LeafNode;
import InterviewQuestions.FirstInterview.models.ParentNode;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertTrue;


public class ValidateFileBuilder {
    ParentNode root;

    @Before
    public void setup() {
        root = buildTree();
    }

    @Test
    public void fileShouldBeBuiltWithNode() throws IOException {
        File expected = FileUtils.getFile("samples/outputs/tree_1.txt");
        File output = new Write().buildFile(root);

        boolean identical = FileUtils.contentEquals(expected, output);

        assertTrue(identical);
    }

    public ParentNode buildTree() {
        root = new ParentNode.Builder()
                .addData(0) //0
                .withFeature("device_type=pc||or||browser=7")
                .build();

        root.addYes(new ParentNode.Builder()
                .addData(2) //2
                .withFeature("os_family=5")
                .build().
                        addYes(new ParentNode.Builder()
                                .addData(6) //6
                                .withFeature("browser=8")
                                .build().
                                        addYes(new ParentNode.Builder()
                                                .addData(12) //12
                                                .withFeature("language=2")
                                                .build().
                                                        addYes(new LeafNode(0.000559453)).addNo(new LeafNode(0.000594593)) // 20 // 19
                                        ).
                                        addNo(new ParentNode.Builder()
                                                .addData(11) //11
                                                .withFeature("size=300x600")
                                                .build().
                                                        addYes(new LeafNode(0.000597397)).addYes(new LeafNode(0.00063461)) // 18 //17
                                        )
                        ).
                        addNo(new ParentNode.Builder()
                                .addData(5)
                                .withFeature("browser=8||or||browser=5")
                                .build()
                                .addYes(new LeafNode(0.000625534)) //10
                                .addNo(new ParentNode.Builder()
                                        .build()
                                        .addNo(new ParentNode.Builder()
                                                .addData(9)
                                                .withFeature("position=2")
                                                .build()
                                                .addYes(new LeafNode(0.00066727)).addNo(new LeafNode(0.000708484)) //16 //15
                                        )
                                )
                        )
        );

        root.addNo(new ParentNode.Builder()
                .addData(1)
                .withFeature("")
                .build()
                .addYes(new LeafNode(0.000881108)) //4
                .addNo(new ParentNode.Builder()
                        .addData(3)
                        .withFeature("os_family=5")
                        .build()
                        .addYes(new LeafNode(0.000842268)) //8
                        .addNo(new ParentNode.Builder()
                                .addData(7)
                                .withFeature("region=FR:A5")
                                .build()
                                .addYes(new LeafNode(0.000939982)) // 14
                                .addNo(new LeafNode(0.000999001)) //13
                        )
                )
        );

        return root;
    }
}
