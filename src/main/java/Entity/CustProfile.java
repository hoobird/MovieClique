package Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustProfile extends Profile {

	public static String[] genreList = { "Action", "Adventure", "Comedy", "Crime", "Drama", "Fantasy", "Historical",
			"Horror", "Romance" };
	String genre = "";
	int loyaltyPoints;

	@JsonCreator
	public CustProfile(
		@JsonProperty("firstName") String firstName,
		@JsonProperty("lastName") String lastName,
		@JsonProperty("gender") String gender,
		@JsonProperty("age") int age,
		@JsonProperty("phoneNo") String phoneNo,
		@JsonProperty("email") String email,
		@JsonProperty("genre") String genre) {
		super(firstName, lastName, gender, age, phoneNo, email);
		this.genre = genre;
		this.loyaltyPoints = 0;  

	}

	public static String[] getGenreList() {
		return genreList;
	}

	public static void setGenreList(String[] genreList) {
		CustProfile.genreList = genreList;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return super.toString() + "\nCustProfile [genre=" + genre + "]";
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	public void useLoyaltyPoints() {
		this.loyaltyPoints -= 10;
	}
	
}
