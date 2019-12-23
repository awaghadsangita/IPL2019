package ipl;

public class IplAdapterFactory extends IplAnalyser {
    protected static IplAdapter getIplObject(IplAnalyser.playerTypes playerType) throws IplAnalyserException {
        if (playerType.equals(IplAnalyser.playerTypes.RUNS)) {
            return new IplBatsmanAdapter();
        }
        if (playerType.equals(IplAnalyser.playerTypes.WICKETS)) {
            return new IplBowlerAdapter();
        }
        if(playerType.equals(playerTypes.MERGE))
            return new IplBatmanBowlerAdapter();
        throw new IplAnalyserException("Unknown player type", IplAnalyserException.ExceptionType.INCORRECT_PLAYER_TYPE);
    }
}
