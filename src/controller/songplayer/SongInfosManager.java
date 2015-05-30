package controller.songplayer;

/**
 * This class manage the information of a song in reproduction
 * @author Matteo Gabellini
 *
 */
public interface SongInfosManager {
	/**
	 * This method is used for set the position in the song open in the clip
	 * @param time, the microsecond past from the begin of the song
	 * @throws IllegalArgumentException if the parameter time is out of the range of the song
	 */
	void setPosition(final int time) throws IllegalArgumentException;
	
	/**
	 * Obtain the length of the song, expressed in second
	 * @return the length of the track 
	 */
	double getDuration();
	
	/**
	 * This method return the elapsed time from the begin of the song
	 * @return the elapsed second from the song's begin
	 */
	double getElapsedTime();
}
