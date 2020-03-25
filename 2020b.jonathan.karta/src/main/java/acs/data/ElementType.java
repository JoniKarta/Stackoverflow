package acs.data;

/**
 * 
 * @author 
 *
 */
public enum ElementType {

	BOTTLE_RECYCLE ("Bottle recycle"),
	PAPER_RECYCLE ("Paper recycle"),
	GLASS_RECYCLE ("Glass recycle"),
	BOX_RECYCLE ("Box recycle");
    
	private String name;

    public String getName() {
        return this.name;
    }

    ElementType(String name) {
        this.name = name;
    }
}