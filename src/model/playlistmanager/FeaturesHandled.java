package model.playlistmanager;

public enum FeaturesHandled {
	SHUFFLE(ShuffablePlaylistFeature.class);
	
	private final Class<?> featureClass;
	
	private FeaturesHandled(final Class<?> featureClass){
		this.featureClass = featureClass;
	}
	
	public Class<?> getFeatureClass(){
		return this.featureClass;
	}
}
