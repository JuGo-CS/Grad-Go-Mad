package codefiles;


public abstract class RandWords{
    String[] words;
    String getRandword(int randInt){
        return words[randInt];
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
    public RandwordsYear1(){
        this.words = new String[]{"if", "or", "and", "not",
                             "var", "int", "for", "new",
                             "let", "try", "is", "do",
                             "in", "by", "of", "null", "get", 
                             "set", "gui","ide"};
    }
}




class RandwordsYear2 extends RandWords{
    public RandwordsYear2(){
        words = new String[]{"loop", "code", "data", "file",
                             "test", "user", "form", "link",
                             "read", "save", "edit", "main",
                             "void", "type", "args", "java",
                             "this", "super", "else", "catch"};
    }
}




class RandwordsYear3 extends RandWords{
    public RandwordsYear3(){
        words = new String[]{"function", "pointer", "syntax",
                             "runtime", "variable", "object",
                             "string", "method", "array", "boolean",
                             "package", "module", "server", "browser",
                             "client", "string", "extends", "implement",
                             "import", "compile"};
        }
}




class RandwordsYear4 extends RandWords{
    public RandwordsYear4(){
        words = new String[]{"inheritance", "polymorphism", "encapsulation",
                             "abstraction", "declaration", "exception",
                             "multithreading", "interpreter", "compilation",
                             "serialization", "implementation", "configuration",
                             "authentication", "authorization", "virtualization","constructor",
                              "programming", "instantiation", "interface", "keyword"};
    }
}







