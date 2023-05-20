/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constructors;

/**
 *
 * @author kobe0
 */
public class Voters {
    private int id;
        public String name;
        public int age;
        public String street;
        public String barangay;
        public String municipality;
        public String city;
        public String country;
        public int zip_code;
        public String idType;
        public String idNumber;
        public Voters(int id, String name, int age, String street, String barangay, String municipality, String city, String country, int zip_code, String idType, String idNumber) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.street = street;
            this.barangay = barangay;
            this.municipality = municipality;
            this.city = city;
            this.country = country;
            this.zip_code = zip_code;
            this.idType = idType;
            this.idNumber = idNumber;
        }
}
