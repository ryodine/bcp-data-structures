import java.util.ArrayList;
import java.util.Hashtable;
import java.lang.reflect.*;

class InheritanceLab_Johnson {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new BellStudent("Ryan", "Johnson", 11));
        people.add(new BellStudent("Brandon", "Wui", 12));
        people.add(new BellStudent("Mario", "Ruiz", 10));
        people.add(new BellTeacher("Brad", "Lindemann", 6));
        people.add(new BellStudent("Vidur", "Maheshwari", 11));
        people.add(new SFStudent("Kian", "Lechner", 11));
        people.add(new SFTeacher("Joe", "Schmoe", 2));
        people.add(new Teacher("New", "Hire", 0));
        Person clone = people.get(7).cloneObject();
        people.add(clone);

        //Do for current and year+1 year
        for (Person p : people) {
            System.out.println(p + ": " + p.getFQN());
        }
        
        //Print number of BellStudents
        System.out.println("Bell Students: " + BellStudent.specificCount());
    }
}

abstract class Person implements Comparable<Person>{
    private static int uid_increment = 0;
    public static Hashtable<String, Integer> counts = new Hashtable<String, Integer>();
    public int uid;
    public String name;
    public String surname;
    
    protected String greeting;
    
    public int compareTo(Person other) {
        if (uid == other.uid){
            return 0;
        } else if (other instanceof Teacher) {
            return 1;
        } else if (this instanceof Teacher) {
            return -1;
        }
        return (surname + " " + name).compareTo((other.surname + " " + other.name));
    }
    
    public String getFQN(){
        return this.getClass().getName() + "." + this.hashCode() + "." + uid;
    }

    public String toString() {
        return "id# " + uid + " of size: " + specificCount(this.getClass().getName()) + ": " + name + " " + surname;
    }
    
    public Person(String name, String surname) {
        uid = uid_increment;
        uid_increment++;
        this.name = name;
        this.surname = surname;
        greeting = name + " " + surname + " is a human being";
        //System.out.println(name + " " + surname + " Born with UUID: " + this.uid);
        if (counts.get(this.getClass().getName()) != null) {
            counts.put(this.getClass().getName(), counts.get(this.getClass().getName())+1);
        } else {
            counts.put(this.getClass().getName(), 1);
        }
    }
    
    public void evaluateGreeting() {
        greeting = name + " " + surname + " is a human being";
    }
    
    public Person greet() {
        System.out.println(greeting);
        return this;
    } 
    
    public static int familyCount() {
        return uid_increment;
    }
    
    protected static int specificCount(String c) {
        try {
            return counts.get(Class.forName(c).getName());
        } catch (ClassNotFoundException e) {
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }
    
    public static int specificCount() {
        return specificCount("Person");
    }
    
    public Person cloneObject(){
        Hashtable<Class<?>, Object> defaultValues = new Hashtable<Class<?>, Object>();
            defaultValues.put(String.class, "");
            defaultValues.put(Integer.class, 0);
            defaultValues.put(int.class, 0);
            defaultValues.put(Long.class, 0L);
            defaultValues.put(long.class, 0L);
            defaultValues.put(Character.class, '\0');
            defaultValues.put(char.class, '\0');
            
        try{
            Constructor[] ctors = this.getClass().getDeclaredConstructors();
            
        	Constructor ctor = null;
        	    ctor = ctors[0];
        	    Type[] types = ctor.getGenericParameterTypes();
        	    Object[] defaultVals = new Object[types.length];
        	    for (int j = 0; j < types.length; j++) {
        	        defaultVals[j] = defaultValues.get(types[j]);
        	    }
        	
        	Person clone = (Person) ctor.newInstance(defaultVals);
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(clone, field.get(this));
            }
            
            clone.name = this.name;
            clone.surname = this.surname;
            
            clone.evaluateGreeting();
            
            return clone;
            
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    
    //What happens when a school year ends
    public abstract Person nextYear();
}

class Student extends Person {
    
    public int year;
    static private int count = 0;
    public String[] years = {"Freshman", "Sophomore", "Junior", "Senior", "Alumni"};
    
    public Student(String name, String surname, int year) {
        super(name, surname);
        this.year = year;
        count++;
        evaluateGreeting();
    }
    
    public void evaluateGreeting() {
        this.greeting = name + " " + surname + " is a " + years[(year >= 9 && year <= 13)? year-9: 0] + ". ";
    }
    
    public static int specificCount() {
        return specificCount("Student");
    }

    
    public static int familyCount() {
        return count;
    }
    
    public Person nextYear(){
        if (year >= 12) {
            year = 13;
        } else {
            year++;
        }
        evaluateGreeting();
        return this;
    }
}

class Teacher extends Person {
    public int yearstaught;
    public String taught;
    static private int count = 0;
    
    public Teacher(String name, String surname, int year) {
        super(name, surname);
        this.yearstaught = year;
        count++;
        evaluateGreeting();
    }
    
    
    public void evaluateGreeting() {
        this.greeting = name + " " + surname + " has taught " + yearstaught + " years. ";
    }
    
    public static int familyCount() {
        return count;
    }
    
    public Person nextYear(){
        yearstaught++;
        evaluateGreeting();
        return this;
    }
    
    public static int specificCount() {
        return specificCount("Teacher");
    }
}

class BellStudent extends Student{
    static private int count = 0;
    public BellStudent (String name, String surname, int year) {
        super(name, surname, year);
        count++;
        evaluateGreeting();
    }
    
    public static int familyCount() {
        return count;
    }
    
    public void evaluateGreeting() {
        this.greeting = name + " " + surname + " is a " + years[(year >= 9 && year <= 13)? year-9: 0] + " at Bellarmine. ";
    }
    
    public static int specificCount() {
        return specificCount("BellStudent");
    }
}

class BellTeacher extends Teacher{
    static private int count = 0;
    public BellTeacher (String name, String surname, int year) {
        super(name, surname, year);
        count++;
        evaluateGreeting();
    }  
    
    public static int familyCount() {
        return count;
    }
    
    public void evaluateGreeting() {
        this.greeting = name + " " + surname + " has taught at Bellarmine for " + yearstaught + " years. ";
    }
    
    public static int specificCount() {
        return specificCount("BellTeacher");
    }
    
}

class SFStudent extends Student{
    static private int count = 0;
    public SFStudent (String name, String surname, int year) {
        super(name, surname, year);
        count++;
        evaluateGreeting();
    }
    
    public static int familyCount() {
        return count;
    }
    
    public void evaluateGreeting() {
        this.greeting = name + " " + surname + " is a " + years[(year >= 9 && year <= 13)? year-9: 0] + " at Saint Francis. ";
    }
    
    public static int specificCount() {
        return specificCount("SFStudent");
    }
}

class SFTeacher extends Teacher{
    static private int count = 0;
    public SFTeacher (String name, String surname, int year) {
        super(name, surname, year);
        count++;
        evaluateGreeting();
    }

    
    public static int familyCount() {
        return count;
    }
    
    public void evaluateGreeting() {
        this.greeting = name + " " + surname + " has taught at Saint Francis for " + yearstaught + " years. ";
    }
    
    public static int specificCount() {
        return specificCount("SFTeacher");
    }
}