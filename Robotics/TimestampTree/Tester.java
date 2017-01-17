class AverageableDouble implements Averageable<Double> {
    public Double value = 0.0;
    public AverageableDouble(Double v) {
        value = v;
    }
    
    public Double get() {
        return value;
    }
    
    public Averageable<Double> average(Number xValue, Number highXValue, Averageable<Double> highYValue, Number searchedForValue) {
        AverageableDouble hy = (AverageableDouble) highYValue;
        Double dy = hy.get() - get();
        Double dx = highXValue.doubleValue() - xValue.doubleValue();
        
        Double searchY = dy/dx * (searchedForValue.doubleValue()-xValue.doubleValue())+ get();
        
        return new AverageableDouble(searchY);
    }
    
    public void set(Double t) {
        value = t;
    }
}

public class Tester {
    public static void main(String[] args) {
        InterpolatingTreeMap<Double, AverageableDouble> tree = new InterpolatingTreeMap<Double, AverageableDouble>(3);
        tree.put(1.0, new AverageableDouble(2.0));
        tree.put(3.0, new AverageableDouble(4.0));
        tree.put(4.0, new AverageableDouble(5.0));
        tree.put(6.0, new AverageableDouble(5.0));
        System.out.println(tree.getInterpolated(4.0).get());
        System.out.println(tree.getInterpolated(3.9).get());
        System.out.println(tree.getInterpolated(1.0));
    }
}