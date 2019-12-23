package ipl;

public class IplAdapterFactory extends IplAnalyser {
    protected static IplAdapter getIplObject(IplAnalyser.playerTypes playerType) throws IplAnalyserException {
        if (playerType.equals(IplAnalyser.playerTypes.BATSMAN)) {
            return new IplBatsmanAdapter();
        }
        if (playerType.equals(IplAnalyser.playerTypes.BOWLER)) {
            return new IplBowlerAdapter();
        }
        if(playerType.equals(playerTypes.MERGE))
            return new IplBatmanBowlerAdapter();
        throw new IplAnalyserException("Unknown player type", IplAnalyserException.ExceptionType.INCORRECT_PLAYER_TYPE);
    }
}
