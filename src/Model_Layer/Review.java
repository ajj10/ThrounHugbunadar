package Model_Layer;

//import java.sql.Date;
import java.util.Date;

public class Review {
	
	private String author;
	private String title;
	private Date date;
	private String reviewText;
	private int rating;
	
	public Review(String reviewAuthor, String reviewTitle, Date reviewDate, String ReviewText, int reviewRating) 
	{
		author = reviewAuthor;
		title = reviewTitle;
		date = reviewDate;
		reviewText = ReviewText;
		rating = reviewRating;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getReviewText() {
		return reviewText;
	}
	
	public int getRating() {
		return rating;
	}

}
