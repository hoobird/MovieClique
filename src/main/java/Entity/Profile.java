package Entity;


public class Profile {

    boolean active;
    String firstName;
    String lastName;
    String gender; // Assuming only male & female
    int age;
    String phoneNo;
    String email;
    //Profile userProfile; typo?

    public Profile(String firstName, String lastName, String gender, int age, String phoneNo, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    // For jackson
    public Profile() {
        super();
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    // public Profile getUserProfile() {
    //     return userProfile;
    // }

    // public void setUserProfile(Profile userProfile) {
    //     this.userProfile = userProfile;
    // }
    

    @Override
    public String toString() {
        return "Profile [active=" + active + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
                + gender + ", age=" + age + ", phoneNo=" + phoneNo + ", email=" + email /*+ ", userProfile=" + userProfile*/
                + "]";
    }
    
}