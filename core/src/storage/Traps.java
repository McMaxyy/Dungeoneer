package storage;

public abstract class Traps {
	private int attackPower, value;
	private String name, objective, trait;
	
	public int getAttackPower() {
		return attackPower;
	}
	public void setAttackPower(int damage) {
		this.attackPower = damage;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getTrait() {
		return trait;
	}
	public void setTrait(String trait) {
		this.trait = trait;
	}
}

class SpikeTrap extends Traps{
	public SpikeTrap() {
		setName("Spike Trap");
		setAttackPower(5);
		setValue(9);
		setObjective("Wisdom");
		setTrait("Damage");
	}
}

