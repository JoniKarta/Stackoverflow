package acs.data;


/**
 * 
 * @author 
 *
 */
public enum ElementType {
	BOTTLE_RECYCLE ("Bottle recycle item"),
	PAPER_RECYCLE ("Paper recycle item"),
	GLASS_RECYCLE ("Glass recycle item"),
	BOX_RECYCLE ("Box recycle item");
    
	private String name;

    public String getName() {
        return this.name;
    }

    ElementType(String name) {
        this.name = name;
    }
}