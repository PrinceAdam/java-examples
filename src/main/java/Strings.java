public class Strings {
    public void stringConcatinationExample(){

        String s1 = "AB";
        String s2 = "CD";

        String s3 = s1;
        System.out.println(s1 == s3); //Same object?

        s3 = s1 + s2;
        System.out.println(s1 == s3); // Still same?
        System.out.println(s1);
        System.out.println(s3);
    }
}
