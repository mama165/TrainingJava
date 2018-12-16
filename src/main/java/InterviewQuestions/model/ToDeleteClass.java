package InterviewQuestions.model;

public class ToDeleteClass {

    public void DoSomething() {
        Node root = new ParentNode(
                new ParentNode(
                        new LeafNode(0.000939982, 3),
                        new LeafNode(0.000934582, 4),
                        "[os_family=5]",
                        1),
                new LeafNode(0.0009356982, 2),
                "[device_type=pc||or||browser=7]",
                0);
        System.out.println(root.toString());
    }

    /*
0:[device_type=pc||or||browser=7] yes=2,no=1
	1:[os_family=5] yes=3,no=4
		3:leaf=0.000939982
        4:leaf=0.000934582
    2:leaf=0.0009356982
    */
}