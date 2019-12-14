package ipl;


public class IplAnalyserException extends Exception {
    enum ExceptionType {CSV_FILE_PROBLEM,HEADER_CAPTURING_ISSUE};

    ExceptionType type;

    public IplAnalyserException(String message, IplAnalyserException.ExceptionType type) {
        super(message);
        this.type = type;
    }
}
