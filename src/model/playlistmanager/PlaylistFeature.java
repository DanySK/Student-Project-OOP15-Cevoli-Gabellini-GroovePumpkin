package model.playlistmanager;

/**
 * This interface rappresent a generic contract for the feature of a playlist
 * 
 * The classes that implements this interface may be assembled like specified in
 * the pattern decorator, beacause to distinguish what level must execute the
 * methods i have to check just if the classes passes like parameter is equals
 * to my class
 * 
 * @author Matteo Gabellini
 *
 */
public interface PlaylistFeature<X> {

	/**
	 * Setter for the state of this feature
	 * 
	 * @param featureClass
	 *            The class that i want to set the state, this is used to
	 *            distinguish what feature i want to change the state in
	 *            hierarchy of features
	 * @param active
	 *            true if i want activate the feature otherwise false if i will
	 *            deactive the feature
	 */
	void setFeatureState(Class<?> featureClass,PlaylistManager<X> plManager,boolean active);
	
	/**
	 * Check the feature state
	 * @param featureClass 
	 * 			The class that i want to check the state, this is used to
	 *            distinguish what feature i want to check in
	 *            hierarchy of features
	 * @return
	 */
	boolean isFeatureActive(Class<?> featureClass);
}
