import java.util.ArrayList;

/**
 * Filter which removes movies from view based on an list of selected genres
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public class GenreFilter extends Filter {

    private ArrayList<String> genreList;

    /**
     * Default constructor for GenreFilter
     */
    public GenreFilter() {
        genreList = new ArrayList<String>();
    }

    /**
     * Adds a genre to the genreList
     * Will not add if genre is already in the list
     * @param genre genre to be added
     */
    public void addGenre(String genre) {
        if (!genreList.contains(genre)) {
            System.out.println(genre + " has been added to your genre filter.");
            genreList.add(genre);
        }
        else {
            System.out.println(genre + " is already being filtered out!");
        }
    }

    /**
     * Removes a genre from the genreList
     * Displays variable message depending on whether removal was successful
     * @param genre name of the genre to be removed
     */
    public void removeGenre(String genre) {
        if (genreList.contains(genre)) {
            System.out.println(genre + " is no longer being filtered out.");
            genreList.remove(genre);
        }
        else {
            System.out.println(genre + " is currently not being filtered out.");
        }
    }

    /**
     * Shows a list of the genres that have been filtered out
     * @return the current genreList
     */
    public ArrayList<String> showGenres() {
        return genreList;
    }

    /**
     * toString method
     * @return the list of genres being filtered out
     */
    public String toString() {
        StringBuilder genreString = new StringBuilder();
        for (String genre : genreList) {
            genreString.append(genre).append(" ");
        }

        return "Filtered Genres: " + genreString;
    }
}
