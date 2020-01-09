package cc.sdspar.spar.item;

public class ItemShredderBlade extends ItemBase {
	
	private final String type;

	public ItemShredderBlade(String name) {
		super("shredder_blade_" + name);
		this.type = name;
		this.setMaxStackSize(1);
	}
	
	@Override
	public int getMaxDamage() {
		if (type == "aluminum") {
			return 27;
		} else if (type == "copper") {
			return 38;
		} else if (type == "diamond") {
			return 100;
		} else if (type == "gold") {
			return 20;
		} else if (type == "iron") {
			return 47;
		} else if (type == "tin") {
			return 30;
		} else if (type == "titanium") {
			return 65;
		} else {
			return 1;
		}
	}

}
