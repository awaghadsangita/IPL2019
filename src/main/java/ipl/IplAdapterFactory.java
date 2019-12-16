package ipl;

public class IplAdapterFactory extends IplAnalyser {
    protected static IplAdapter getIplObject(IplAnalyser.playerTypes playerType) throws IplAnalyserException {
        if (playerType.equals(IplAnalyser.playerTypes.RUNS)) {
            return new IplMostRunsAdapter();
        }
        if (playerType.equals(IplAnalyser.playerTypes.WICKETS)) {
            return new IplMostWicketsAdapter();
        }
        throw new IplAnalyserException("Unkonwn player type", IplAnalyserException.ExceptionType.INCORRECT_PLAYER_TYPE);
    }
}
