package controller.musicplayer;

import model.playlistmanager.FeaturesHandled;

/**
 * This interface rappresent an object that manages a shuffle mode
 * @author Matteo Gabellini
 *
 */
public interface PlaylistFeatureCommand {
	/**
	 * Set the shuffle mode
	 * @param value
	 */
	void setPlaylistFeature(final FeaturesHandled feature,final boolean value);
}
