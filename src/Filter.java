/**
 * Abstract representation of a content filter
 * Has a name
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public abstract class Filter {

    private String name;

    /**
     * Default getter for name
     * @return name of the filter
     */
    public String getName() {
        return this.name;
    }

    /**
     * Default setter for name
     * @param name new name of the filter
     */
    public void setName(String name) {
        this.name = name;
    }
}
