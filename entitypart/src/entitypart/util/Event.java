package entitypart.util;

public interface Event<T> {
	
	public void notify(final T listener);
	
}
