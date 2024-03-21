package storage;

public abstract class NormalEnemies {
	private int attackPower, maxHP, value;
	private String name, type, trait, objective;
	
	public int getAttackPower() {
		return attackPower;
	}
	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrait() {
		return trait;
	}
	public void setTrait(String trait) {
		this.trait = trait;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
}

class Goblin extends NormalEnemies{
	public Goblin() {
		setName("Gobbo");
		setAttackPower(6);
		setMaxHP(20);
		setValue(10);
		setType("Normal");
		setTrait("Shriek");
		setObjective("Dexterity");
	}
}

class Rat extends NormalEnemies{
	public Rat() {
		setName("Marlin");
		setAttackPower(7);
		setMaxHP(15);
		setValue(8);
		setType("Normal");
		setTrait("Bite");
		setObjective("Vigor");
	}
}

class Skeleton extends NormalEnemies{
	public Skeleton() {
		setName("Ben");
		setAttackPower(6);
		setMaxHP(20);
		setValue(10);
		setType("Normal");
		setTrait("Growth");
		setObjective("Strength");
	}
}