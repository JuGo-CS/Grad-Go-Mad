package finalproject;

public abstract class RandWords{
    String[] Words;
    String getRandword(int randInt){
        return Words[randInt];
    }
}

class RandWordsFactory{
    public static RandWords createRandWords(int yearLevel){
        switch (yearLevel) {
            case 1:     return new RandwordsYear1();
            case 2:     return new RandwordsYear2();
            case 3:     return new RandwordsYear3();
            case 4:     return new RandwordsYear4();
            default:    return null;
        }
    }
}

class RandwordsYear1 extends RandWords{
    String[] Words = new String[]{"if", "or", "and", "not",
                                  "var", "int", "for", "new",
                                  "let", "try", "is", "do",
                                  "in", "by", "of"};
}

class RandwordsYear2 extends RandWords{
    String[] Words = new String[]{"loop", "code", "data", "file",
                                  "test", "user", "form", "link",
                                  "read", "save", "edit", "main",
                                  "void", "type", "args"};
}

class RandwordsYear3 extends RandWords{
    String[] Words = new String[]{"function", "pointer", "syntax",
                                  "runtime", "variable", "object",
                                  "string", "method", "array", "boolean",
                                  "package", "module", "server", "browser", "client"};
}

class RandwordsYear4 extends RandWords{
    String[] Words = new String[]{"inheritance", "polymorphism", "encapsulation",
                                  "abstraction", "declaration", "exceptional",
                                  "multithreading", "interpreter", "compilation",
                                  "serialization", "implementation", "configuration",
                                  "authentication", "authorization", "virtualization"};
}