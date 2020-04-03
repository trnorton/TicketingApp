/**
 * Filter which removes movies above a specific age rating
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public class MatureContentFilter extends Filter {
    // Minimum age rating of a movie
    private static final int MIN_AGE = 1;

    private int ageRating;

    /**
     * Parametrized constructor for MatureContentFilter
     * @param ageRating maximum age rating that can be displayed
     */
    public MatureContentFilter(int ageRating)
    {
        setAgeRating(ageRating);
    }

    /**
     * Default getter for ageRating
     * @return the current maximum allowed age rating
     */
    public int getAgeRating() {
        return this.ageRating;
    }

    /**
     * Default setter for ageRating
     * Age cannot be lower than a certain minimum age
     * @param ageRating new maximum age rating
     */
    public void setAgeRating(int ageRating) {
        if (ageRating < MIN_AGE)
            ageRating = MIN_AGE;
        this.ageRating = ageRating;
    }

    /**
     * Description of current MatureContentFilter
     * @return the maximum age rating of movies that can be displayed
     */
    public String toString() {
        if (ageRating == MIN_AGE) {
            return "Only Rated G movies are visible.";
        }
        else {
            return "Movies for audiences age " + ageRating + " and below are visible.";
        }
    }
}
