package InterviewQuestions.model;

public class ToDeleteClass
{
    public abstract class Node
    {
        protected int nodeNumber;

        public Node(int nodeNumber)
        {
            this.nodeNumber = nodeNumber;
        }

        public abstract String ToString();
            // Node root = this;
            // if(root != null) {
            //     if(root instanceof ParentNode) {
            
            //     root.toString();
            //     }
            // }
        // }
    }

    public class ParentNode extends Node
    {
        private Node yes;
        private Node no;
        private String feature;

        public ParentNode(Node yes, Node no, String feature, int nodeNumber) {
            super(nodeNumber);
            this.yes = yes;
            this.no = no;
            this.feature = feature;
        }

        public String ToString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nodeNumber + ":" + this.feature + " " + "yes=" + this.yes.nodeNumber + "," + "no=" + this.no.nodeNumber + "\n");
            sb.append(this.yes.toString());
            sb.append(this.no.toString());
            return sb.toString();
        }
    }

    public class LeafNode extends Node
    {
        private double value;

        public LeafNode(Double value, int nodeNumber) {
            super(nodeNumber);
            this.value = value;
        }

        public String ToString() {
    
            return this.nodeNumber + ":leaf=" + this.value;
        }
    }

    public void DoSomething()
    {
        // Node nodeMaster = new Node(null);

        // Node parentNode = new ParentNode(new ParentNode(null,null), new LeafNode());
        // Node leafNode = new LeafNode();

        Node root = new ParentNode(
            new ParentNode(
                new LeafNode(0.000939982, 3),
                new LeafNode(0.000934582, 4),
                "[os_family=5]",
                1),
            new LeafNode(0.0009356982, 2),
            "[device_type=pc||or||browser=7]",
            0);
        System.out.println(root.ToString());
    }

    /*
0:[device_type=pc||or||browser=7] yes=2,no=1
	1:[os_family=5] yes=3,no=4
		3:leaf=0.000939982
        4:leaf=0.000934582
    2:leaf=0.0009356982
    */
} 