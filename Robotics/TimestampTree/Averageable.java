public interface Averageable<T> {
    //Assuming the object average is being called on is the LOWER BOUND
    public Averageable<T> average(Number xValue, Number highXValue, Averageable<T> highYValue, Number searchedForValue);
}