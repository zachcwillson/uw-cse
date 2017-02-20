// Zach Willson
// CSE 373 - WI17
// Client to test TextAssociator class

public class MyClient {

    public static void main(String[] args) {
        System.out.println("Client Tests");
        TextAssociator test = new TextAssociator();
        testMethod1(test);
        test.remove("test");
        test.prettyPrint();
        testMethod2(test);
        System.out.println("Client Tests Passed.");

    }

    // Tests addNewWord and addAssociation
    public static void testMethod1(TextAssociator test) {
        test.addNewWord("test");
        test.addAssociation("test", "exam");
        test.prettyPrint();
        if (test.addNewWord("test") == false) {
            System.out.println("Passed Test");
        }
        test.prettyPrint();
    }

    // Tests addNewWord, addAssociation, and getAssociation
    public static void testMethod2(TextAssociator test) {
        test.addNewWord("computer");
        test.addAssociation("computer", "mac");
        test.addAssociation("computer", "pc");
        test.prettyPrint();
        System.out.println(test.getAssociations("computer"));
        test.addNewWord("language");
        test.addAssociation("language", "C#");
        test.addAssociation("language", "Java");
        test.addAssociation("language", "C++");
        test.prettyPrint();
    }
}