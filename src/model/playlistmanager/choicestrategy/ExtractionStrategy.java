package model.playlistmanager.choicestrategy;

/**
 * Objects that implements this interface define an algorithm to extract an element from a collection
 * @author Matteo Gabellini
 *
 * @param <X>
 */
public interface ExtractionStrategy<X> {
	X getElement();
}
