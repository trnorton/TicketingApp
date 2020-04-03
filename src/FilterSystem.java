import java.util.ArrayList;

/**
 * The collection of filters in place for the current user.
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public class FilterSystem {
    // Default age rating for unregistered users
    private static final int DEF_AGE = 16;

    private ArrayList<Filter> filters;

    /**
     * Default constructor for the filter system
     * Includes a default age rating for guest users
     */
    public FilterSystem() {
        filters = new ArrayList<Filter>();
        filters.add(new MatureContentFilter(DEF_AGE));
    }

    /**
     * Method which adds a filter to the array list
     * @param filter filter to be added to the list
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    /**
     * Method which removes a filter from the array list
     * @param filter filter to be removed from the list
     */
    public void removeFilter(Filter filter) {
        filters.remove(filter);
    }

    /**
     * Method which displays the current filter list
     * @return the list of currently applied filters
     */
    public ArrayList<Filter> showFilters() {
        return filters;
    }
}
