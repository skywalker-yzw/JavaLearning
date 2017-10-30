/**
 * Created by yizhiw on 4/30/2017.
 */
public class HashCode {
    private static class Employee {
        String firstName;
        String lastName;
        int id;

        public Employee(String firstName, String lastName, int id){
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
        }


        @Override
        public boolean equals(Object obj) {
            Employee anotherObj = (Employee)obj;
            System.out.println("override equals method is called");
            System.out.println(this.firstName + " " + anotherObj.firstName);
            System.out.println(this.lastName + " " + anotherObj.lastName);

            if ((this.firstName.equals(anotherObj.firstName)) && (this.lastName.equals(anotherObj.lastName))) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode(){
            int hash = 31;
            // this just a simple way to calculate the hash code for the object
            hash = 7 * hash + this.firstName.hashCode();
            hash = 7 * hash + this.lastName.hashCode();
            return hash;
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(new String("Robin"), new String("Wu"), 1);
        Employee e2 = new Employee(new String("Robin"), new String("Wu"), 2);

        System.out.println(e1.hashCode());
        System.out.println(e2.hashCode());

        System.out.println(e1.equals(e2));
/*
        String s1 = "Hello";
        String s2 = "World";
        String s3 = "Hello";

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());

        System.out.println(s1.equals(s3));
    }
*/
    }
}
